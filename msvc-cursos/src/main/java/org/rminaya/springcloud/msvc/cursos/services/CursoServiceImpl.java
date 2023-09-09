package org.rminaya.springcloud.msvc.cursos.services;

import org.rminaya.springcloud.msvc.cursos.entities.Curso;
import org.rminaya.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Optional<Curso> editar(Curso curso, Long id) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isPresent()) {

            Curso cursoFromDB = cursoOptional.get();
            cursoFromDB.setNombre(curso.getNombre());
            return Optional.of(cursoRepository.save(cursoFromDB));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }
}
