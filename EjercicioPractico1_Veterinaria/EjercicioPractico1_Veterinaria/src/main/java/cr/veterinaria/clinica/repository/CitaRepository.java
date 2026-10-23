package cr.veterinaria.clinica.repository;

import cr.veterinaria.clinica.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
    @Query("select c from Cita c join fetch c.servicio s join fetch s.tipoServicio order by c.fecha")
    List<Cita> listarConServicio();
    boolean existsByServicioId(Integer servicioId);
}
