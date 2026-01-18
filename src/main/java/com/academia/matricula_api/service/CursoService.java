package com.academia.matricula_api.service;

import com.academia.matricula_api.model.Curso;
import com.academia.matricula_api.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;


@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public Curso guardar(Curso c) {
        return repository.save(c);
    }

    public List<Curso> listarOrdenado() {

        return repository.findAll()
                .stream()
                .sorted((a, b) ->
                        a.getNombre().compareToIgnoreCase(b.getNombre()))
                .toList();
    }

    public Curso actualizar(Integer id, Curso nuevo) {

        Curso c = repository.findById(id).orElseThrow();

        c.setNombre(nuevo.getNombre());
        c.setSiglas(nuevo.getSiglas());
        c.setEstado(nuevo.isEstado());

        return repository.save(c);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}


