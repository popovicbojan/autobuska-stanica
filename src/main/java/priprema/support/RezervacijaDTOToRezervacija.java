package priprema.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import priprema.model.Linija;
import priprema.model.Rezervacija;
import priprema.service.LinijaService;
import priprema.web.dto.RezervacijaDTO;

@Component
public class RezervacijaDTOToRezervacija implements Converter<RezervacijaDTO, Rezervacija>{

	@Autowired
	private LinijaService linijaService;
	
	@Override
	public Rezervacija convert(RezervacijaDTO source) {
		Linija linija = linijaService.findOne(source.getIdLinije());
		
		if(linija != null) {
			
			if(linija.getBrojMesta()<source.getBrojMesta()) {
				return null;
			}
			
			Rezervacija rez = new Rezervacija();
			
			rez.setLinija(linija);
			rez.setBrojMesta(source.getBrojMesta());
			return rez;
		}
		else {
			throw new IllegalStateException("Pokusali ste da rezervisete mesta na liniji koja ne postoji!");
		}
	}

}
