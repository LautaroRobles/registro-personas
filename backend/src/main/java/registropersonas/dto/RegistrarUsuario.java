package registropersonas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegistrarUsuario {
    private String username;
    private String password;
    private String dni;
}
