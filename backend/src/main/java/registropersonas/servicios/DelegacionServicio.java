package registropersonas.servicios;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registropersonas.modelo.Delegacion;
import registropersonas.modelo.Estado;
import registropersonas.repositorios.DelegacionRepositorio;
import registropersonas.dto.DelegacionReporte;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DelegacionServicio {

    @Autowired
    private DelegacionRepositorio delegacionRepositorio;

    private static final ModelMapper modelMapper = new ModelMapper();

    public DelegacionReporte aprobarDelegacion(Long idDelegacion) throws NotFoundException{
        Delegacion delegacion = delegacionRepositorio.findById(idDelegacion).orElseThrow(() -> new NotFoundException("No se encuentra la delegacion"));
        delegacion.setEstado(Estado.Aprobada);
        return modelMapper.map(delegacionRepositorio.save(delegacion), DelegacionReporte.class);
    }

    public DelegacionReporte revocarDelegacion(Long idDelegacion) throws NotFoundException{
        Delegacion delegacion = delegacionRepositorio.findById(idDelegacion).orElseThrow(() -> new NotFoundException("No se encuentra la delegacion"));
        delegacion.setEstado(Estado.Rechazada);
        return modelMapper.map(delegacionRepositorio.save(delegacion), DelegacionReporte.class);
    }

    public List<DelegacionReporte> reporteListadoDelegacion() {
        List<Delegacion> delegaciones = delegacionRepositorio.findAll();
        return delegaciones.stream().map(delegacion ->
                modelMapper.map(delegacion, DelegacionReporte.class)
        ).collect(Collectors.toList());
    }
}
