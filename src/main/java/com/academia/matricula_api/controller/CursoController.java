package com.academia.matricula_api.controller;

import com.academia.matricula_api.model.Curso;
import com.academia.matricula_api.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @PostMapping
    public Curso registrar(@Valid @RequestBody Curso c) {
        return service.guardar(c);
    }

    @GetMapping
    public List<Curso> listar() {
        return service.listarOrdenado();
    }

    @PutMapping("/{id}")
    public Curso actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Curso c) {

        return service.actualizar(id, c);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "Curso eliminado";
    }
}


