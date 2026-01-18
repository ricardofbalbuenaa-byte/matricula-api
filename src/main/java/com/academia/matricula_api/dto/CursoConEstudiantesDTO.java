package com.academia.matricula_api.dto;

import java.util.List;

public class CursoConEstudiantesDTO {

    private String nombreCurso;
    private String siglas;
    private List<String> estudiantes;

    public CursoConEstudiantesDTO(String nombreCurso,
                                  String siglas,
                                  List<String> estudiantes) {
        this.nombreCurso = nombreCurso;
        this.siglas = siglas;
        this.estudiantes = estudiantes;
    }

    public String getNombreCurso() { return nombreCurso; }
    public String getSiglas() { return siglas; }
    public List<String> getEstudiantes() { return estudiantes; }
}
