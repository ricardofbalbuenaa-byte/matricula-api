package com.academia.matricula_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nombre;

    @NotBlank
    @Size(min = 2, max = 10)
    private String siglas;

    @NotNull
    private boolean estado;
}
