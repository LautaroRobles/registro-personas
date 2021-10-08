package registropersonas.servicios;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registropersonas.modelo.Delegacion;
import registropersonas.modelo.Estado;
import registropersonas.repositorios.DelegacionRepositorio;
import registropersonas.dto.DelegacionReporteDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DelegacionServicio {

    @Autowired
    private DelegacionRepositorio delegacionRepositorio;

    private static final ModelMapper modelMapper = new ModelMapper();

    public void aprobarDelegacion(Long idDelegacion) {
        Delegacion delegacion = delegacionRepositorio.findById(idDelegacion).orElse(null);
        if(delegacion != null) {
            delegacion.setEstado(Estado.Aprobada);
            delegacionRepositorio.save(delegacion);
        }
    }

    public void revocarDelegacion(Long idDelegacion) {
        Delegacion delegacion = delegacionRepositorio.findById(idDelegacion).orElse(null);
        if(delegacion != null) {
            delegacion.setEstado(Estado.Rechazada);
            delegacionRepositorio.save(delegacion);
        }
    }

    public List<DelegacionReporteDTO> reporteListadoDelegacion() {
        List<Delegacion> delegaciones = delegacionRepositorio.findAll();
        return delegaciones.stream().map(delegacion ->
                modelMapper.map(delegacion, DelegacionReporteDTO.class)
        ).collect(Collectors.toList());
    }
}
