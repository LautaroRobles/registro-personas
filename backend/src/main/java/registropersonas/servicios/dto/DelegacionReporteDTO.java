package registropersonas.servicios.dto;

import lombok.Getter;
import lombok.Setter;
import registropersonas.modelo.Estado;

@Getter
@Setter
public class DelegacionReporteDTO {
    private Long id;
    private PersonaReporteDTO autorizante;
    private PersonaReporteDTO autoriza;
    private Estado estado;
}
