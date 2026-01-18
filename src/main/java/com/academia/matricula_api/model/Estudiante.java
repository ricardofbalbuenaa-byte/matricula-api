package com.academia.matricula_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @NotBlank
    @Column(unique = true)
    private String dni;

    @Min(1)
    private int edad;
}
