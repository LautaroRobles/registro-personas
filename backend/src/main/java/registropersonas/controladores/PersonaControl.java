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
    @ResponseStatus(HttpStatus.OK)
    PersonaReporte actualizarPersona(@PathVariable Long idPersona, @RequestBody ActualizarPersona actualizarPersona) throws NotFoundException {
        return personaServicio.actualizarPersona(
            idPersona,
            actualizarPersona.getFecha(),
            actualizarPersona.getCiudad(),
            actualizarPersona.getLocalidad(),
            actualizarPersona.getFoto()
        );
    }

    @PostMapping("/{idAutorizante}/autorizar/{idAutoriza}")
    @ResponseStatus(HttpStatus.CREATED)
    DelegacionReporte autorizarPersona(@PathVariable Long idAutorizante, @PathVariable Long idAutoriza) throws NotFoundException {
        return personaServicio.autorizarPersona(idAutorizante, idAutoriza);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<PersonaReporte> reportePersonas() {
        return personaServicio.reporteListadoPersonas();
    }
}
