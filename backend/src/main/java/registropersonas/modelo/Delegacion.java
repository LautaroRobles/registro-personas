package registropersonas.modelo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import registropersonas.repositorios.PersonaRepositorio;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Table
@Entity
@Getter
@Setter
public class Delegacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_autorizante", nullable = false)
    private Persona autorizante;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_autoriza", nullable = false)
    private Persona autoriza;
    @Enumerated(EnumType.STRING)
    private Estado estado;
}
