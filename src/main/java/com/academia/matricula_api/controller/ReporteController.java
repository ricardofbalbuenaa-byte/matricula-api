package com.academia.matricula_api.controller;

import com.academia.matricula_api.dto.CursoConEstudiantesDTO;
import com.academia.matricula_api.service.ReporteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService service;

    public ReporteController(ReporteService service) {
        this.service = service;
    }

    @GetMapping("/cursos-estudiantes")
    public List<CursoConEstudiantesDTO> listar() {
        return service.listarCursosConEstudiantes();
    }
}
