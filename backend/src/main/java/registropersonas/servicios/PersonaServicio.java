package registropersonas.servicios;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registropersonas.modelo.Delegacion;
import registropersonas.modelo.Estado;
import registropersonas.modelo.Persona;
import registropersonas.repositorios.DelegacionRepositorio;
import registropersonas.repositorios.PersonaRepositorio;
import registropersonas.dto.PersonaReporteDTO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServicio {

    @Autowired
    private PersonaRepositorio personaRepositorio;
    @Autowired
    private DelegacionRepositorio delegacionRepositorio;

    private static final ModelMapper modelMapper = new ModelMapper();

    public void autorizarPersona(Long idAutorizante, Long idAutoriza) {
        Persona autorizante = personaRepositorio.findById(idAutorizante).orElse(null);
        Persona autoriza = personaRepositorio.findById(idAutoriza).orElse(null);

        if(autorizante != null && autoriza != null) {
            Delegacion delegacion = new Delegacion();
            delegacion.setAutorizante(autorizante);
            delegacion.setAutoriza(autoriza);
            delegacion.setEstado(Estado.EnEspera);

            delegacionRepositorio.save(delegacion);
        }
    }

    public List<PersonaReporteDTO> reporteListadoPersonas() {
        List<Persona> personas = personaRepositorio.findAll();
        return personas.stream().map(persona ->
                modelMapper.map(persona, PersonaReporteDTO.class)
        ).collect(Collectors.toList());
    }

    public void actualizarPersona(Long idPersona, Date fechaNacimiento, String ciudad, String localidad, String foto) {
        Persona persona = personaRepositorio.findById(idPersona).orElse(null);

        if(persona != null) {
            persona.setFechaNacimiento(fechaNacimiento);
            persona.setCiudad(ciudad);
            persona.setLocalidad(localidad);
            persona.setFoto(foto);

            personaRepositorio.save(persona);
        }
    }
}
