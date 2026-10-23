package cr.veterinaria.clinica.service.impl;

import cr.veterinaria.clinica.domain.Cita;
import cr.veterinaria.clinica.repository.CitaRepository;
import cr.veterinaria.clinica.service.CitaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {
    private final CitaRepository citaRepository;
    public CitaServiceImpl(CitaRepository citaRepository) { this.citaRepository = citaRepository; }
    @Override public List<Cita> listar() { return citaRepository.listarConServicio(); }
}
