package org.rminaya.springcloud.msvc.cursos.services;

import org.rminaya.springcloud.msvc.cursos.entities.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    Optional<Curso> editar(Curso curso, Long id);
    void eliminar(Long id);
}
