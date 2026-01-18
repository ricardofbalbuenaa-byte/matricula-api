package com.academia.matricula_api.service;

import com.academia.matricula_api.dto.CursoConEstudiantesDTO;
import com.academia.matricula_api.model.Matricula;
import com.academia.matricula_api.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private final MatriculaRepository matriculaRepository;

    public ReporteService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    public List<CursoConEstudiantesDTO> listarCursosConEstudiantes() {

        List<Matricula> matriculas = matriculaRepository.findAll();

        return matriculas.stream()

                .flatMap(m -> m.getDetalleMatricula().stream())

                .collect(Collectors.groupingBy(
                        d -> d.getCurso()
                ))

                .entrySet()
                .stream()

                .map(entry -> {

                    var curso = entry.getKey();

                    var estudiantes =
                            entry.getValue()
                                    .stream()
                                    .map(d ->
                                            d.getMatricula().getEstudiante().getNombres()
                                                    + " "
                                                    + d.getMatricula().getEstudiante().getApellidos()
                                    )
                                    .sorted()
                                    .toList();

                    return new CursoConEstudiantesDTO(
                            curso.getNombre(),
                            curso.getSiglas(),
                            estudiantes
                    );
                })

                .sorted((a, b) ->
                        a.getNombreCurso()
                                .compareToIgnoreCase(b.getNombreCurso())
                )

                .toList();
    }
}
