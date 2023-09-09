package org.rminaya.springcloud.msvc.cursos.repositories;

import org.rminaya.springcloud.msvc.cursos.entities.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
}
