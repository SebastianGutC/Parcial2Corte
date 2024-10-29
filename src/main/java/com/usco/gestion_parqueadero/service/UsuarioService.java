package com.usco.gestion_parqueadero.service;


import com.usco.gestion_parqueadero.dto.UsuarioRegistroDTO;
import com.usco.gestion_parqueadero.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService extends UserDetailsService {
    public Usuario guardar(UsuarioRegistroDTO registroDTO) throws UsuarioExistenteException;

    public List<Usuario> listarUsuarios();

    Usuario findByUsername(String username);

    public List<Usuario> getAllUsuarios();

    public List<Usuario> findAllByRole(String role);
}
