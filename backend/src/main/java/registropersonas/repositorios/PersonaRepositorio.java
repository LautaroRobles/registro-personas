package registropersonas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import registropersonas.modelo.Persona;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
}
