package org.rminaya.springcloud.msvc.usuarios.services;

import org.rminaya.springcloud.msvc.usuarios.models.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar();
    Usuario porId(Long id);
    Usuario guardar(Usuario usuario);
    Usuario editar(Usuario usuario, Long id);
    void eliminar(Long id);
}
