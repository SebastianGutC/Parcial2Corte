package com.usco.gestion_parqueadero.controller;

import com.usco.gestion_parqueadero.dto.GestionDTO;
import com.usco.gestion_parqueadero.model.Gestion;
import com.usco.gestion_parqueadero.model.TipoVehiculo;
import com.usco.gestion_parqueadero.model.Usuario;
import com.usco.gestion_parqueadero.service.GestionService;
import com.usco.gestion_parqueadero.service.TipoVehiculoService;
import com.usco.gestion_parqueadero.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class GestionController {

    @Autowired
    private GestionService gestionService;

    @Autowired
    private TipoVehiculoService tipoVehiculoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String showIndex(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioService.findByUsername(username);
        model.addAttribute("usuario", usuario);
        List<Gestion> gestiones = gestionService.getAllGestiones();
        model.addAttribute("gestion", gestiones);
        return "index";
    }

    @GetMapping("/nueva")
    public String showNewGestionForm(Model model) {
        GestionDTO gestionDTO = new GestionDTO();
        model.addAttribute("gestionDTO", gestionDTO);

        List<TipoVehiculo> modelos = tipoVehiculoService.findAll();
        model.addAttribute("tipoVehiculos", modelos);
        return "creargestion";
    }

    @PostMapping("/guardar")
    public String saveGestion(@ModelAttribute GestionDTO gestionDTO, Model model) {
        try {
            gestionService.saveGestion(gestionDTO);
            model.addAttribute("successMessage", "El nuevo ingreso se creó correctamente");
            gestionDTO = new GestionDTO();
        } catch (Exception e) {
            System.out.println("Error al guardar el Nuevo Ingreso: " + e.getMessage());
            model.addAttribute("errorMessage", "Error al guardar el Nuevo Ingreso");
        }
        model.addAttribute("gestionDTO", gestionDTO);
        return "creargestion";
    }

    @GetMapping("/actualizar")
    public String showUpdateForm(@RequestParam Long id, ModelMap model) {
        Gestion gestion = gestionService.getGestionById(id);
        List<TipoVehiculo> tipoVehiculos = tipoVehiculoService.findAll();
        model.addAttribute("tipoVehiculos", tipoVehiculos);
        model.put("gestion", gestion);
        return "actualizargestion";
    }

    @PostMapping("/actualizar")
    public String updateGestion(ModelMap model, @Valid Gestion gestion, BindingResult result) {
        if (result.hasErrors()) {
            return "actualizargestion";
        }
        gestionService.updateGestion(gestion);
        return "redirect:/home";
    }

    @PostMapping("/eliminar")
    public String deleteGestion(ModelMap model, @RequestParam Long id) {
        Gestion gestion = gestionService.getGestionById(id);
        if (gestion == null) {
            model.addAttribute("error", "Ingreso no encontrado");
            return "error";
        }
        gestionService.deleteGestion(gestion);
        return "redirect:/home";
    }
}
