package registropersonas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import registropersonas.modelo.Delegacion;

public interface DelegacionRepositorio extends JpaRepository<Delegacion, Long> {
}
