package registropersonas.servicios;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import registropersonas.modelo.Persona;
import registropersonas.modelo.Rol;
import registropersonas.modelo.Usuario;
import registropersonas.modelo.buscadorPersonas.BuscadorPersonas;
import registropersonas.modelo.buscadorPersonas.PersonaExistente;
import registropersonas.repositorios.PersonaRepositorio;
import registropersonas.repositorios.UsuarioRepositorio;
import registropersonas.seguridad.jwt.TokenProvider;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private PersonaRepositorio personaRepositorio;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final BuscadorPersonas buscadorPersonas = BuscadorPersonas.getInstancia();

    public Usuario registrarUsuario(String username, String password, String dni) throws NotFoundException {
        PersonaExistente personaExistente = buscadorPersonas.buscarPersonaExistente(dni);

        if(personaExistente != null) {
            Usuario usuario = new Usuario();

            usuario.setUsername(username);
            usuario.setPassword(passwordEncoder.encode(password));
            usuario.setRol(Rol.ESTANDAR);

            usuario = usuarioRepositorio.save(usuario);

            Persona persona = new Persona();

            persona.setNombre(personaExistente.getNombre());
            persona.setApellido(personaExistente.getApellido());
            persona.setDni(personaExistente.getDni());
            persona.setUsuario(usuario);

            personaRepositorio.save(persona);

            return usuario;
        }
        else {
            throw new NotFoundException("No se encuentra una persona con ese dni");
        }
    }

    public String login(String username, String password) throws UsernameNotFoundException {
        Usuario usuario = loadUserByUsername(username);

        // Check if password is correct
        if(!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new UsernameNotFoundException("Usuario o contraseña incorrecta");
        }

        return TokenProvider.generateToken(usuario);
    }

    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Usuario o contraseña incorrecta")
        );

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString()));

        usuario.setAuthorities(authorities);
        return usuario;
    }
}
