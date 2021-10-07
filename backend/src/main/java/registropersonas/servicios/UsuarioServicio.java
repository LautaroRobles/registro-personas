package registropersonas.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registropersonas.modelo.Persona;
import registropersonas.modelo.Rol;
import registropersonas.modelo.Usuario;
import registropersonas.modelo.buscadorPersonas.BuscadorPersonas;
import registropersonas.modelo.buscadorPersonas.PersonaExistente;
import registropersonas.repositorios.PersonaRepositorio;
import registropersonas.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private PersonaRepositorio personaRepositorio;

    private final BuscadorPersonas buscadorPersonas = BuscadorPersonas.getInstancia();

    public void registrarUsuario(String username, String password, String dni) {
        PersonaExistente personaExistente = buscadorPersonas.buscarPersonaExistente(dni);

        if(personaExistente != null) {
            Usuario usuario = new Usuario();

            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setRol(Rol.Estandar);

            usuario = usuarioRepositorio.save(usuario);

            Persona persona = new Persona();

            persona.setNombre(personaExistente.getNombre());
            persona.setApellido(personaExistente.getApellido());
            persona.setDni(personaExistente.getDni());
            persona.setUsuario(usuario);

            personaRepositorio.save(persona);
        }


    }
}
