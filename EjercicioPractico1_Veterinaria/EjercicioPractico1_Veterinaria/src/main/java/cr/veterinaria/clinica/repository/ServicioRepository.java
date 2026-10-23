package cr.veterinaria.clinica.repository;

import cr.veterinaria.clinica.domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    boolean existsByTipoServicioId(Integer tipoId);
}
