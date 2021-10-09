package registropersonas.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class ActualizarPersona {
    private Date fecha;
    private String localidad;
    private String ciudad;
    private String foto;
}
