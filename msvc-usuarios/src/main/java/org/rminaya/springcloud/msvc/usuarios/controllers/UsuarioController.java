package org.rminaya.springcloud.msvc.usuarios.controllers;

import org.rminaya.springcloud.msvc.usuarios.models.entities.Usuario;
import org.rminaya.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable(name = "id") Long usuarioId) {
        return ResponseEntity.ok(usuarioService.porId(usuarioId));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, @PathVariable Long id) {

        Usuario usuarioToUpdate = usuarioService.editar(usuario, id);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
