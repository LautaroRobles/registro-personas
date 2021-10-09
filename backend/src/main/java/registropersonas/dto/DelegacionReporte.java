package registropersonas.dto;

import lombok.Getter;
import lombok.Setter;
import registropersonas.modelo.Estado;

@Getter
@Setter
public class DelegacionReporte {
    private Long id;
    private PersonaReporte autorizante;
    private PersonaReporte autoriza;
    private Estado estado;
}
