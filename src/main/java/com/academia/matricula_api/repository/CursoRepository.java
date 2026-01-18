package com.academia.matricula_api.repository;

import com.academia.matricula_api.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
