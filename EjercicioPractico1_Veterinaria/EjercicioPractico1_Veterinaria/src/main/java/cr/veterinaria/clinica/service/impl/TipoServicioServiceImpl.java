package cr.veterinaria.clinica.service.impl;

import cr.veterinaria.clinica.domain.TipoServicio;
import cr.veterinaria.clinica.repository.TipoServicioRepository;
import cr.veterinaria.clinica.repository.ServicioRepository;
import cr.veterinaria.clinica.service.TipoServicioService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TipoServicioServiceImpl implements TipoServicioService {
    private final TipoServicioRepository tipoRepository;
    private final ServicioRepository servicioRepository;

    public TipoServicioServiceImpl(TipoServicioRepository tipoRepository, ServicioRepository servicioRepository) {
        this.tipoRepository = tipoRepository;
        this.servicioRepository = servicioRepository;
    }

    @Override public List<TipoServicio> listar() { return tipoRepository.findAll(); }
    @Override public Optional<TipoServicio> buscarPorId(Integer id) { return tipoRepository.findById(id); }
    @Override public TipoServicio guardar(TipoServicio tipo) { return tipoRepository.save(tipo); }
    @Override public void eliminar(Integer id) {
        if (servicioRepository.existsByTipoServicioId(id)) {
            throw new IllegalStateException("No se puede eliminar este tipo porque tiene servicios asociados.");
        }
        tipoRepository.deleteById(id);
    }
    @Override public boolean tieneServicios(Integer id) { return servicioRepository.existsByTipoServicioId(id); }
}
