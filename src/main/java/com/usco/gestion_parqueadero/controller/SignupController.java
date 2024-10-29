package com.usco.gestion_parqueadero.controller;

import com.usco.gestion_parqueadero.dto.UsuarioRegistroDTO;
import com.usco.gestion_parqueadero.service.UsuarioExistenteException;
import com.usco.gestion_parqueadero.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private UsuarioService usuarioService;

    public SignupController(UsuarioService usuarioService) {
        super();
        this.usuarioService = usuarioService;
    }

    @ModelAttribute("username")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping
    public String mostrarFormularioDeRegistro() {
        return "signup";
    }

    @PostMapping
    public String registrarNuevoUsuario(@ModelAttribute("username") UsuarioRegistroDTO usuarioRegistroDTO,
                                        Model model) {
        try {
            usuarioService.guardar(usuarioRegistroDTO);
            model.addAttribute("successMessage", "El usuario se creó correctamente");
        } catch (UsuarioExistenteException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("usuario", usuarioRegistroDTO);
        } catch (Exception e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
            model.addAttribute("errorMessage", "Error al registrar el usuario");
        }
        return "signup";
    }
}