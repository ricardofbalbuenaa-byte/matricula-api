package com.academia.matricula_api.service;
import com.academia.matricula_api.dto.MatriculaRequest;
import com.academia.matricula_api.model.Curso;
import com.academia.matricula_api.model.DetalleMatricula;
import com.academia.matricula_api.model.Estudiante;
import com.academia.matricula_api.model.Matricula;
import com.academia.matricula_api.repository.CursoRepository;
import com.academia.matricula_api.repository.DetalleMatriculaRepository;
import com.academia.matricula_api.repository.EstudianteRepository;
import com.academia.matricula_api.repository.MatriculaRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Comparator;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepo;
    private final EstudianteRepository estudianteRepo;
    private final CursoRepository cursoRepo;
    private final DetalleMatriculaRepository detalleMatriculaRepo;

    public MatriculaService(
            MatriculaRepository matriculaRepo,
            EstudianteRepository estudianteRepo,
            DetalleMatriculaRepository detalleMatriculaRepo,
            CursoRepository cursoRepo) {

        this.matriculaRepo = matriculaRepo;
        this.estudianteRepo = estudianteRepo;
        this.cursoRepo = cursoRepo;
        this.detalleMatriculaRepo = detalleMatriculaRepo;
    }

    public Matricula registrar(MatriculaRequest req) {

        Estudiante est = estudianteRepo.findById(req.getEstudianteId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Estudiante no existe"
                        )
                );

        Matricula m = new Matricula();
        m.setEstudiante(est);

        List<DetalleMatricula> detalles =
                req.getDetalle()
                        .stream()
                        .map(d -> {

                            Curso c = cursoRepo.findById(d.getCursoId())
                                    .orElseThrow(() ->
                                            new ResponseStatusException(
                                                    HttpStatus.BAD_REQUEST,
                                                    "Curso no existe"
                                            )
                                    );

                            boolean yaExiste =
                                    detalleMatriculaRepo
                                            .existsByMatriculaEstudianteIdAndCursoId(
                                                    est.getId(),
                                                    d.getCursoId()
                                            );

                            if (yaExiste) {
                                throw new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "El estudiante ya está matriculado en el curso: "
                                                + c.getNombre()
                                );
                            }

                            DetalleMatricula dm = new DetalleMatricula();
                            dm.setCurso(c);
                            dm.setAula(d.getAula());
                            dm.setMatricula(m);

                            return dm;

                        })
                        .collect(Collectors.toList());

        m.setDetalleMatricula(detalles);

        return matriculaRepo.save(m);
    }

    public List<Matricula> listarOrdenado() {
        return matriculaRepo.findAll()
                .stream()
                .sorted((a,b) ->
                        a.getFechaMatricula()
                                .compareTo(b.getFechaMatricula()))
                .collect(Collectors.toList());
    }
}


