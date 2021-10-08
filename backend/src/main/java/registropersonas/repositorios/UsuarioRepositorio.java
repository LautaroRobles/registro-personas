package registropersonas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import registropersonas.modelo.Usuario;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByUsername(String username);
}
