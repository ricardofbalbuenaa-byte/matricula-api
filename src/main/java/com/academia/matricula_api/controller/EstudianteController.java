package com.academia.matricula_api.controller;

import com.academia.matricula_api.model.Estudiante;
import com.academia.matricula_api.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @PostMapping
    public Estudiante registrar(@Valid @RequestBody Estudiante estudiante) {
        return service.guardar(estudiante);
    }

    @GetMapping
    public List<Estudiante> listar() {
        return service.listarOrdenadoPorNombre();
    }
    @GetMapping("/ordenados-edad")
    public List<Estudiante> listarPorEdadDesc() {
        return service.listarOrdenadoPorEdadDesc();
    }
    @PutMapping("/{id}")
    public Estudiante actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Estudiante estudiante) {

        return service.actualizar(id, estudiante);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "Estudiante eliminado";
    }
}

