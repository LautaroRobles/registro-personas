package registropersonas.modelo.buscadorPersonas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuscadorPersonas {
    private static BuscadorPersonas instancia;
    private List<PersonaExistente> personasExistentes = new ArrayList<>();
    private BuscadorPersonas() {

        PersonaExistente p1 = new PersonaExistente("Lautaro", "Robles", "123");
        PersonaExistente p2 = new PersonaExistente("Jose", "Barreto", "124");
        PersonaExistente p3 = new PersonaExistente("Lucas", "Robaina", "125");
        PersonaExistente p4 = new PersonaExistente("Mateo", "Robaina", "126");

        personasExistentes = Arrays.asList(p1, p2, p3, p4);
    }
    public static BuscadorPersonas getInstancia() {
        if(instancia == null) {
            instancia = new BuscadorPersonas();
        }
        return instancia;
    }

    public PersonaExistente buscarPersonaExistente(String dni) {
        return personasExistentes.stream().filter(persona -> persona.getDni().equals(dni)).findFirst().orElse(null);
    }
}
