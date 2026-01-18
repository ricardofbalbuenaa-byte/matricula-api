package com.academia.matricula_api.controller;

import com.academia.matricula_api.dto.MatriculaRequest;
import com.academia.matricula_api.model.Matricula;
import com.academia.matricula_api.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService service;

    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    @PostMapping
    public Matricula registrar(@Valid @RequestBody MatriculaRequest req) {
        return service.registrar(req);
    }

    @GetMapping
    public List<Matricula> listar() {
        return service.listarOrdenado();
    }
}
