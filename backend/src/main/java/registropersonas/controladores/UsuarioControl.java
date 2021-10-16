package registropersonas.controladores;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    @ResponseStatus(HttpStatus.OK)
    String login(@RequestBody LoginUsuario loginUsuario) {
        return usuarioServicio.login(loginUsuario.getUsername(), loginUsuario.getPassword());
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    Usuario registrarse(@RequestBody RegistrarUsuario registrarUsuario) throws NotFoundException {
        return usuarioServicio.registrarUsuario(registrarUsuario.getUsername(), registrarUsuario.getPassword(), registrarUsuario.getDni());
    }
}
