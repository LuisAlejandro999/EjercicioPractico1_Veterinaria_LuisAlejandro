package cr.veterinaria.clinica.controllers;

import cr.veterinaria.clinica.domain.Servicio;
import cr.veterinaria.clinica.service.ServicioService;
import cr.veterinaria.clinica.service.TipoServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicios")
public class ServicioController {
    private final ServicioService servicioService;
    private final TipoServicioService tipoService;

    public ServicioController(ServicioService servicioService, TipoServicioService tipoService) {
        this.servicioService = servicioService;
        this.tipoService = tipoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("servicios", servicioService.listar());
        return "servicios/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("tipos", tipoService.listar());
        model.addAttribute("tituloFormulario", "Agregar servicio");
        return "servicios/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        return servicioService.buscarPorId(id).map(servicio -> {
            model.addAttribute("servicio", servicio);
            model.addAttribute("tipos", tipoService.listar());
            model.addAttribute("tituloFormulario", "Editar servicio");
            return "servicios/formulario";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "El servicio no existe.");
            return "redirect:/servicios";
        });
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("servicio") Servicio servicio,
                          BindingResult result, @RequestParam("tipoId") Integer tipoId,
                          Model model, RedirectAttributes ra) {
        var tipo = tipoService.buscarPorId(tipoId);
        if (tipo.isEmpty()) {
            result.rejectValue("tipoServicio", "tipo.invalido", "Seleccione un tipo de servicio válido.");
        } else {
            servicio.setTipoServicio(tipo.get());
        }
        if (result.hasErrors()) {
            model.addAttribute("tipos", tipoService.listar());
            model.addAttribute("tituloFormulario", servicio.getId() == null ? "Agregar servicio" : "Editar servicio");
            return "servicios/formulario";
        }
        servicioService.guardar(servicio);
        ra.addFlashAttribute("exito", "Servicio guardado correctamente.");
        return "redirect:/servicios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            servicioService.eliminar(id);
            ra.addFlashAttribute("exito", "Servicio eliminado correctamente.");
        } catch (IllegalStateException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/servicios";
    }
}
