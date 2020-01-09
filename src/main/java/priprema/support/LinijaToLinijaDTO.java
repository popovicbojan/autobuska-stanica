package priprema.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import priprema.model.Linija;
import priprema.web.dto.LinijaDTO;
@Component
public class LinijaToLinijaDTO implements Converter<Linija, LinijaDTO>{

	@Override
	public LinijaDTO convert(Linija source) {
		LinijaDTO dto = new LinijaDTO();
		
		dto.setId(source.getId());
		dto.setDestinacija(source.getDestinacija());
		dto.setCenaKarte(source.getCenaKarte());
		dto.setBrojMesta(source.getBrojMesta());
		dto.setIdPrevoznika(source.getPrevoznik().getId());
		dto.setNazivPrevoznika(source.getPrevoznik().getNaziv());
		dto.setVremePolaska(source.getVremePolaska());
		
		
		return dto;
	}
	
	public List<LinijaDTO> convert(List<Linija> linije){
		
		List<LinijaDTO> dtos = new ArrayList<LinijaDTO>();
		
		for (Linija l : linije) {
			dtos.add(convert(l));
		}
		
		return dtos;
		
	}


}
