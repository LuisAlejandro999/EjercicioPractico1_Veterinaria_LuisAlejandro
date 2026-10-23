package cr.veterinaria.clinica.controllers;

import cr.veterinaria.clinica.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/citas")
public class CitaController {
    private final CitaService citaService;
    public CitaController(CitaService citaService) { this.citaService = citaService; }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("citas", citaService.listar());
        return "citas/lista";
    }
}
