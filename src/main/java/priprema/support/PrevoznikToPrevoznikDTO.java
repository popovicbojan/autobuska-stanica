package priprema.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import priprema.model.Prevoznik;
import priprema.web.dto.PrevoznikDTO;
@Component
public class PrevoznikToPrevoznikDTO implements Converter<Prevoznik, PrevoznikDTO>{

	@Override
	public PrevoznikDTO convert(Prevoznik source) {
		PrevoznikDTO dto = new PrevoznikDTO();
		
		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		dto.setAdresa(source.getAdresa());
		dto.setPib(source.getPib());
		
		return dto;
	}
	
	public List<PrevoznikDTO> convert(List<Prevoznik> prevoznici){
		List<PrevoznikDTO> dtos = new ArrayList<PrevoznikDTO>();
		
		for(Prevoznik p : prevoznici) {
			dtos.add(convert(p));
		}
		
		return dtos;
	}

}
