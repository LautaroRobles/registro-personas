package registropersonas.modelo.buscadorPersonas;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonaExistente {
    private String nombre;
    private String apellido;
    private String dni;

    public PersonaExistente(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
}
