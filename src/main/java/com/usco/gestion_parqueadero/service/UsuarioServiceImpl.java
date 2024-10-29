package com.usco.gestion_parqueadero.service;

import com.usco.gestion_parqueadero.dto.UsuarioRegistroDTO;
import com.usco.gestion_parqueadero.model.Rol;
import com.usco.gestion_parqueadero.model.Usuario;
import com.usco.gestion_parqueadero.repository.RolRepository;
import com.usco.gestion_parqueadero.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @Autowired
    private RolRepository rolRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepositorio, RolRepository rolRepositorio) {
        super();
        this.usuarioRepositorio = usuarioRepositorio;
        this.rolRepositorio = rolRepositorio;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario guardar(UsuarioRegistroDTO usuarioRegistroDTO) throws UsuarioExistenteException {
        if(usuarioRepositorio.findByUsername(usuarioRegistroDTO.getUsername()) != null) {
            throw new UsuarioExistenteException("El usuario ya existe");
        }
        Rol userRole = rolRepositorio.findByNombre("ROLE_CLIENTE");
        if (userRole == null) {
            throw new RuntimeException("El rol del Usuario no se encuentra en la base de datos");
        }
        Usuario usuario = new Usuario(usuarioRegistroDTO.getUsername(),
                passwordEncoder.encode(usuarioRegistroDTO.getPassword()),
                Arrays.asList(userRole));
        try {
            Usuario savedUser = usuarioRepositorio.save(usuario);
            System.out.println("Usuario guardado: " + savedUser);
            return savedUser;
        } catch (Exception e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepositorio.findByUsername(username);
    }

    public List<Usuario> findAllByRole(String role) {
        return usuarioRepositorio.findAllByRole(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new User(usuario.getUsername(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .collect(Collectors.toList());
    }

}
