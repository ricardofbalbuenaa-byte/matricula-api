package com.academia.matricula_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DetalleMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private String aula;

    @ManyToOne
    @JoinColumn(name = "matricula_id")
    @JsonBackReference
    private Matricula matricula;
}
