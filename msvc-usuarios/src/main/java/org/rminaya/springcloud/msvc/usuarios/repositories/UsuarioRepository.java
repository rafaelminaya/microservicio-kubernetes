package org.rminaya.springcloud.msvc.usuarios.repositories;

import org.rminaya.springcloud.msvc.usuarios.models.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    @Query("SELECT u FROM Usuario AS u WHERE u.id <> ?1 AND u.email = ?2")
    Optional<Usuario> porUsuarioEmail(Long id, String email);
    // Query Method que devuelve true/false
    boolean existsByEmail(String email);

}
