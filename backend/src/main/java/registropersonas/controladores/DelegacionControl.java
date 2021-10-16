package registropersonas.controladores;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import registropersonas.dto.DelegacionReporte;
import registropersonas.servicios.DelegacionServicio;

import java.util.List;

@RestController
@RequestMapping("/api/delegacion")
public class DelegacionControl {
    @Autowired
    private DelegacionServicio delegacionServicio;

    @PostMapping("/{id}/aprobar")
    @ResponseStatus(HttpStatus.OK)
    DelegacionReporte aprobarDelegacion(@PathVariable Long id) throws NotFoundException {
        return delegacionServicio.aprobarDelegacion(id);
    }

    @PostMapping("/{id}/revocar")
    @ResponseStatus(HttpStatus.OK)
    DelegacionReporte revocarDelegacion(@PathVariable Long id) throws NotFoundException {
        return delegacionServicio.revocarDelegacion(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<DelegacionReporte> reporteDelegaciones() {
        return delegacionServicio.reporteListadoDelegacion();
    }
}
