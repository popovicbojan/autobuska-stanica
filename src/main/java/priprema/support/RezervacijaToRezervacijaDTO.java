package priprema.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import priprema.model.Rezervacija;
import priprema.web.dto.RezervacijaDTO;
@Component
public class RezervacijaToRezervacijaDTO implements Converter<Rezervacija, RezervacijaDTO>{

	@Override
	public RezervacijaDTO convert(Rezervacija source) {
		RezervacijaDTO dto = new RezervacijaDTO();
		
		dto.setId(source.getId());
		dto.setIdLinije(source.getLinija().getId());
		dto.setDestinacija(source.getLinija().getDestinacija());
		dto.setIdPrevoznika(source.getLinija().getPrevoznik().getId());
		dto.setNazivPrevoznika(source.getLinija().getPrevoznik().getNaziv());
		dto.setBrojMesta(source.getBrojMesta());
		
		
		return dto;
	}
	
	public List<RezervacijaDTO> convert (List<Rezervacija> rezervacije){
		List<RezervacijaDTO> dtos = new ArrayList<RezervacijaDTO>();
		
		for(Rezervacija r : rezervacije) {
			dtos.add(convert(r));
		}
		
		return dtos;
	}
	

}
