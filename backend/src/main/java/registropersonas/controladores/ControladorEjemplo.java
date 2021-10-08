package registropersonas.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorEjemplo {
    @PreAuthorize("hasRole('ROLE_Admin')")
    @GetMapping("/admin")
    public String admin() {
        return "Hola admin!";
    }
    @PreAuthorize("hasRole('ROLE_Estandar')")
    @GetMapping("/normal")
    public String normal() {
        return "Hola mundo!";
    }

    @GetMapping("/asd")
    public String asd() {
        return "Hola mundo!";
    }
}
