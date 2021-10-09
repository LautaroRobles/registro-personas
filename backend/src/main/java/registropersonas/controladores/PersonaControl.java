package registropersonas.controladores;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import registropersonas.dto.ActualizarPersona;
import registropersonas.dto.DelegacionReporte;
import registropersonas.dto.PersonaReporte;
import registropersonas.servicios.PersonaServicio;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/persona")
public class PersonaControl {

    @Autowired
    private PersonaServicio personaServicio;

    @PostMapping("/{idPersona}/actualizar")
    public ResponseEntity<PersonaReporte> actualizarPersona(@PathVariable Long idPersona, @RequestBody ActualizarPersona actualizarPersona) {
        try {
            PersonaReporte personaActualizada = personaServicio.actualizarPersona(
                    idPersona,
                    actualizarPersona.getFecha(),
                    actualizarPersona.getCiudad(),
                    actualizarPersona.getLocalidad(),
                    actualizarPersona.getFoto()
            );
            return new ResponseEntity<>(personaActualizada, HttpStatus.OK);
        }
        catch (NotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{idAutorizante}/autorizar/{idAutoriza}")
    public ResponseEntity<DelegacionReporte> autorizarPersona(@PathVariable Long idAutorizante, @PathVariable Long idAutoriza) {
        try {
            DelegacionReporte delegacionCreada = personaServicio.autorizarPersona(idAutorizante, idAutoriza);
            return new ResponseEntity<>(delegacionCreada, HttpStatus.CREATED);
        }
        catch (NotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<PersonaReporte>> reporteDelegaciones() {
        return new ResponseEntity<>(personaServicio.reporteListadoPersonas(), HttpStatus.OK);
    }
}
