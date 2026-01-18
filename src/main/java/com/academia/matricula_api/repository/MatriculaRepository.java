package com.academia.matricula_api.repository;

import com.academia.matricula_api.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
}
