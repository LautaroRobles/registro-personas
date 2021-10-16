package registropersonas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import registropersonas.modelo.Rol;
import registropersonas.modelo.Usuario;
import registropersonas.repositorios.UsuarioRepositorio;

@Component
public class CrearAdmin implements CommandLineRunner {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String...args) throws Exception {
        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRol(Rol.ADMIN);
        usuarioRepositorio.save(admin);
    }
}
