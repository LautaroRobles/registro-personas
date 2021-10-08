package registropersonas.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class LoginUsuario implements Serializable {
    private String username;
    private String password;
}
