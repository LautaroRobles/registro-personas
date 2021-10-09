package registropersonas.controladores;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DelegacionReporte> aprobarDelegacion(@PathVariable Long id) {
        try {
            DelegacionReporte delegacionAprobada = delegacionServicio.aprobarDelegacion(id);
            return new ResponseEntity<>(delegacionAprobada, HttpStatus.OK);
        }
        catch (NotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/revocar")
    public ResponseEntity<DelegacionReporte> revocarDelegacion(@PathVariable Long id) {
        try {
            DelegacionReporte delegacionRevocada = delegacionServicio.revocarDelegacion(id);
            return new ResponseEntity<>(delegacionRevocada, HttpStatus.OK);
        }
        catch (NotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<DelegacionReporte>> reporteDelegaciones() {
        return new ResponseEntity<>(delegacionServicio.reporteListadoDelegacion(), HttpStatus.OK);
    }
}
