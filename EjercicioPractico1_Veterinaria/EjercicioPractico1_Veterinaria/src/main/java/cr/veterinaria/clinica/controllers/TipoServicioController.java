package cr.veterinaria.clinica.controllers;

import cr.veterinaria.clinica.domain.TipoServicio;
import cr.veterinaria.clinica.service.TipoServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipos")
public class TipoServicioController {
    private final TipoServicioService tipoService;
    public TipoServicioController(TipoServicioService tipoService) { this.tipoService = tipoService; }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tipos", tipoService.listar());
        return "tipos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("tipoServicio", new TipoServicio());
        model.addAttribute("tituloFormulario", "Agregar tipo de servicio");
        return "tipos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        return tipoService.buscarPorId(id).map(tipo -> {
            model.addAttribute("tipoServicio", tipo);
            model.addAttribute("tituloFormulario", "Editar tipo de servicio");
            return "tipos/formulario";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "El tipo de servicio no existe.");
            return "redirect:/tipos";
        });
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("tipoServicio") TipoServicio tipo,
                          BindingResult result, RedirectAttributes ra, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tituloFormulario", tipo.getId() == null ? "Agregar tipo de servicio" : "Editar tipo de servicio");
            return "tipos/formulario";
        }
        tipoService.guardar(tipo);
        ra.addFlashAttribute("exito", "Tipo de servicio guardado correctamente.");
        return "redirect:/tipos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            tipoService.eliminar(id);
            ra.addFlashAttribute("exito", "Tipo de servicio eliminado correctamente.");
        } catch (IllegalStateException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/tipos";
    }
}
