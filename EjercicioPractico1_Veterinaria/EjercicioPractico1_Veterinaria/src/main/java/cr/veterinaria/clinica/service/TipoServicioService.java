package cr.veterinaria.clinica.service;

import cr.veterinaria.clinica.domain.TipoServicio;
import java.util.List;
import java.util.Optional;

public interface TipoServicioService {
    List<TipoServicio> listar();
    Optional<TipoServicio> buscarPorId(Integer id);
    TipoServicio guardar(TipoServicio tipo);
    void eliminar(Integer id);
    boolean tieneServicios(Integer id);
}
