package org.rminaya.springcloud.msvc.cursos.controllers;

import org.rminaya.springcloud.msvc.cursos.entities.Curso;
import org.rminaya.springcloud.msvc.cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable(name = "id") Long cursoId) {

        Optional<Curso> cursoOptional = cursoService.porId(cursoId);

        return cursoOptional.isPresent()
                ? ResponseEntity.ok(cursoOptional.get())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<Curso> cursoOptional = cursoService.editar(curso, id);

        return cursoOptional.isPresent()
                ? ResponseEntity.status(HttpStatus.CREATED).body(cursoOptional.get())
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        Optional<Curso> cursoOptional = cursoService.porId(id);

        if (cursoOptional.isPresent()) {
            cursoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> {
            errores.put(fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }


}
