package cr.veterinaria.clinica.service;

import cr.veterinaria.clinica.domain.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioService {
    List<Servicio> listar();
    Optional<Servicio> buscarPorId(Integer id);
    Servicio guardar(Servicio servicio);
    void eliminar(Integer id);
}
