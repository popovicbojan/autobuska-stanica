package priprema.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import priprema.model.Prevoznik;
import priprema.service.PrevoznikService;
import priprema.web.dto.PrevoznikDTO;
@Component
public class PrevoznikDTOToPrevoznik implements Converter<PrevoznikDTO, Prevoznik>{
	
	@Autowired
	private PrevoznikService prevoznikService;

	@Override
	public Prevoznik convert(PrevoznikDTO source) {
		Prevoznik prevoznik = null;
		
		if(source.getId() != null) {
			prevoznik = prevoznikService.findOne(source.getId());
			
			if(prevoznik == null) {
				throw new IllegalStateException("Pokusali ste da promenite nepostojeceg prevoznika!");
			}
			
		} else {
			prevoznik = new Prevoznik();
		}
		
		prevoznik.setId(source.getId());
		prevoznik.setNaziv(source.getNaziv());
		prevoznik.setAdresa(source.getAdresa());
		prevoznik.setPib(source.getPib());
		
		return prevoznik;
	}

}
