package registropersonas.servicios.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class PersonaReporteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaNacimiento;
    private String ciudad;
    private String localidad;
    private String foto;
}
