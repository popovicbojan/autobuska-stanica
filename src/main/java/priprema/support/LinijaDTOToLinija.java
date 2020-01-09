package priprema.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import priprema.model.Linija;
import priprema.model.Prevoznik;
import priprema.service.LinijaService;
import priprema.service.PrevoznikService;
import priprema.web.dto.LinijaDTO;

@Component
public class LinijaDTOToLinija implements Converter<LinijaDTO, Linija> {

	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private PrevoznikService prevoznikService;
	

	@Override
	public Linija convert(LinijaDTO source) {
		
		Linija linija = null;
		
		Prevoznik prevoznik = prevoznikService.findOne(source.getIdPrevoznika());
		
		
		
		if (prevoznik != null) {
			if (source.getId() != null) {
				linija = linijaService.findOne(source.getId());
				if (linija == null) {
					throw new IllegalStateException("Pokusali ste da izmenite liniju koje ne postoji!");
				}
			} else {
				linija = new Linija();
			}
			linija.setId(source.getId());
			linija.setBrojMesta(source.getBrojMesta());
			linija.setCenaKarte(source.getCenaKarte());
			linija.setDestinacija(source.getDestinacija());
			linija.setPrevoznik(prevoznik);
			linija.setVremePolaska(source.getVremePolaska());
			// TODO Auto-generated method stub
			return linija;
		}
		else {
			throw new IllegalStateException("Pokusaliu ste da dodate nepostojeceg prevoznika na liniju!");
		}
	}

}
