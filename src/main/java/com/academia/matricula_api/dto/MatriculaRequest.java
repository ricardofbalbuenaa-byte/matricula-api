package com.academia.matricula_api.dto;

import lombok.Data;
import java.util.List;

@Data
public class MatriculaRequest {

    private Integer estudianteId;

    private List<DetalleRequest> detalle;

    @Data
    public static class DetalleRequest {
        private Integer cursoId;
        private String aula;
    }
}
