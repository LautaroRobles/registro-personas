package registropersonas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import registropersonas.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
