package registropersonas.servicios;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registropersonas.dto.DelegacionReporte;
import registropersonas.modelo.Delegacion;
import registropersonas.modelo.Estado;
import registropersonas.modelo.Persona;
import registropersonas.repositorios.DelegacionRepositorio;
import registropersonas.repositorios.PersonaRepositorio;
import registropersonas.dto.PersonaReporte;

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

    public DelegacionReporte autorizarPersona(Long idAutorizante, Long idAutoriza) throws NotFoundException {
        Persona autorizante = personaRepositorio.findById(idAutorizante).orElseThrow(() -> new NotFoundException("Persona autorizante no existe"));
        Persona autoriza = personaRepositorio.findById(idAutoriza).orElseThrow(() -> new NotFoundException("Persona a autorizar no existe"));

        Delegacion delegacion = new Delegacion();
        delegacion.setAutorizante(autorizante);
        delegacion.setAutoriza(autoriza);
        delegacion.setEstado(Estado.EnEspera);

        return modelMapper.map(delegacionRepositorio.save(delegacion), DelegacionReporte.class);
    }

    public List<PersonaReporte> reporteListadoPersonas() {
        List<Persona> personas = personaRepositorio.findAll();
        return personas.stream().map(persona ->
            modelMapper.map(persona, PersonaReporte.class)
        ).collect(Collectors.toList());
    }

    public PersonaReporte actualizarPersona(Long idPersona, Date fechaNacimiento, String ciudad, String localidad, String foto) throws NotFoundException {
        Persona persona = personaRepositorio.findById(idPersona).orElseThrow(() -> new NotFoundException("Persona no existe"));

        persona.setFechaNacimiento(fechaNacimiento);
        persona.setCiudad(ciudad);
        persona.setLocalidad(localidad);
        persona.setFoto(foto);

        return modelMapper.map(personaRepositorio.save(persona), PersonaReporte.class);
    }
}
