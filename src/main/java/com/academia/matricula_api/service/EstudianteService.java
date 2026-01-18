package com.academia.matricula_api.service;
import com.academia.matricula_api.model.Estudiante;
import com.academia.matricula_api.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    private final EstudianteRepository repository;

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    public Estudiante guardar(Estudiante estudiante) {

        repository.findByDni(estudiante.getDni())
                .ifPresent(e -> {
                    throw new RuntimeException("Ya existe un estudiante con DNI: "
                            + estudiante.getDni());
                });
        return repository.save(estudiante);
    }


    public List<Estudiante> listarOrdenadoPorNombre() {
        return repository.findAll()
                .stream()
                .sorted((a, b) -> a.getNombres()
                        .compareToIgnoreCase(b.getNombres()))
                .collect(Collectors.toList());
    }

    public Estudiante actualizar(Integer id, Estudiante datos) {
        Estudiante existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        existente.setNombres(datos.getNombres());
        existente.setApellidos(datos.getApellidos());
        existente.setDni(datos.getDni());
        existente.setEdad(datos.getEdad());

        return repository.save(existente);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    public List<Estudiante> listarOrdenadoPorEdadDesc() {
        return repository.findAll()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getEdad(), a.getEdad()))
                .toList();
    }
}
