package cr.veterinaria.clinica.service.impl;

import cr.veterinaria.clinica.domain.Servicio;
import cr.veterinaria.clinica.repository.ServicioRepository;
import cr.veterinaria.clinica.repository.CitaRepository;
import cr.veterinaria.clinica.service.ServicioService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {
    private final ServicioRepository servicioRepository;
    private final CitaRepository citaRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository, CitaRepository citaRepository) {
        this.servicioRepository = servicioRepository;
        this.citaRepository = citaRepository;
    }

    @Override public List<Servicio> listar() { return servicioRepository.findAll(); }
    @Override public Optional<Servicio> buscarPorId(Integer id) { return servicioRepository.findById(id); }
    @Override public Servicio guardar(Servicio servicio) { return servicioRepository.save(servicio); }
    @Override public void eliminar(Integer id) {
        if (citaRepository.existsByServicioId(id)) {
            throw new IllegalStateException("No se puede eliminar este servicio porque tiene citas asociadas.");
        }
        servicioRepository.deleteById(id);
    }
}
