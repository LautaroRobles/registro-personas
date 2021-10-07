package registropersonas.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter @Setter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaNacimiento;
    private String ciudad;
    private String localidad;
    private String foto;
    @OneToMany(mappedBy = "autorizante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Delegacion> delegaciones;
}
