package registropersonas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import registropersonas.dto.RegistrarUsuario;
import registropersonas.dto.LoginUsuario;
import registropersonas.dto.TokenUsuario;
import registropersonas.modelo.Usuario;
import registropersonas.servicios.UsuarioServicio;

@RestController
@RequestMapping("/usuario")
public class UsuarioControl {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/login")
    public ResponseEntity<TokenUsuario> login(@RequestBody LoginUsuario loginUsuario) {

        TokenUsuario tokenUsuario = usuarioServicio.login(loginUsuario.getUsername(), loginUsuario.getPassword());

        return new ResponseEntity<>(tokenUsuario, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrarse(@RequestBody RegistrarUsuario registrarUsuario) {
        Usuario usuario = usuarioServicio.registrarUsuario(registrarUsuario.getUsername(), registrarUsuario.getPassword(), registrarUsuario.getDni());
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
