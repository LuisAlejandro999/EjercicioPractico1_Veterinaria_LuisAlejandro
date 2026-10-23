package cr.veterinaria.clinica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InicioController {
    @GetMapping("/")
    public String inicio() { return "inicio"; }

    @PostMapping("/consultas")
    public String enviarConsulta(@RequestParam String nombre,
                                 @RequestParam String correo,
                                 @RequestParam String mascota,
                                 @RequestParam String mensaje,
                                 RedirectAttributes redirectAttributes) {
        if (nombre.isBlank() || correo.isBlank() || mascota.isBlank() || mensaje.isBlank()) {
            redirectAttributes.addFlashAttribute("errorConsulta", "Complete todos los campos para enviar la consulta.");
        } else {
            redirectAttributes.addFlashAttribute("mensajeConsulta",
                    "Gracias, " + nombre + ". Recibimos su consulta sobre " + mascota + ".");
        }
        return "redirect:/#contacto";
    }
}
