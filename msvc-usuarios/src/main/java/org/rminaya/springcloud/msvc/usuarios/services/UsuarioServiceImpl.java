package org.rminaya.springcloud.msvc.usuarios.services;

import org.rminaya.springcloud.msvc.usuarios.models.entities.Usuario;
import org.rminaya.springcloud.msvc.usuarios.models.exceptions.EmailFoundException;
import org.rminaya.springcloud.msvc.usuarios.models.exceptions.IdNotFoundException;
import org.rminaya.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario porId(Long id) {
        Usuario usuarioFromDB = usuarioRepository.findById(id).orElseThrow(() -> new IdNotFoundException("usuarios"));
        return usuarioFromDB;
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        boolean existeEmail = usuarioRepository.existsByEmail(usuario.getEmail());

        if (existeEmail) throw new EmailFoundException();

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario editar(Usuario usuario, Long id) {

        Usuario usuarioFromDB = usuarioRepository.findById(id).orElseThrow(() -> new IdNotFoundException("usuarios"));

        Optional<Usuario> usuarioEmailFromDB = usuarioRepository.porUsuarioEmail(id, usuario.getEmail());
        if (usuarioEmailFromDB.isPresent()) {
            throw new EmailFoundException();
        }

        usuarioFromDB.setEmail(usuario.getEmail());
        usuarioFromDB.setNombre(usuario.getNombre());
        usuarioFromDB.setPassword(usuario.getPassword());
        return usuarioRepository.save(usuarioFromDB);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Usuario usuarioFromDB = usuarioRepository.findById(id).orElseThrow(() -> new IdNotFoundException("usuarios"));
        usuarioRepository.deleteById(usuarioFromDB.getId());
    }
}
