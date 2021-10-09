package registropersonas.controladores;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import registropersonas.dto.RegistrarUsuario;
import registropersonas.dto.LoginUsuario;
import registropersonas.modelo.Usuario;
import registropersonas.servicios.UsuarioServicio;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioControl {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUsuario loginUsuario) {

        try {
            String tokenUsuario = usuarioServicio.login(loginUsuario.getUsername(), loginUsuario.getPassword());
            return new ResponseEntity<>(tokenUsuario, HttpStatus.OK);
        }
        catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrarse(@RequestBody RegistrarUsuario registrarUsuario) {
        try {
            Usuario usuario = usuarioServicio.registrarUsuario(registrarUsuario.getUsername(), registrarUsuario.getPassword(), registrarUsuario.getDni());
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        catch (NotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
