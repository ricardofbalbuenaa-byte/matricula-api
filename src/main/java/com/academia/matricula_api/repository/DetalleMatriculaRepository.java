package com.academia.matricula_api.repository;

import com.academia.matricula_api.model.DetalleMatricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleMatriculaRepository
        extends JpaRepository<DetalleMatricula, Integer> {

    boolean existsByMatriculaEstudianteIdAndCursoId(
            Integer estudianteId,
            Integer cursoId
    );
}
