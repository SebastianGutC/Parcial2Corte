package com.usco.gestion_parqueadero.repository;

import com.usco.gestion_parqueadero.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsuarioId(Long usuarioId);

    Usuario findByUsername(String username);

    @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE r.nombre = :role")
    List<Usuario> findAllByRole(String role);
}
