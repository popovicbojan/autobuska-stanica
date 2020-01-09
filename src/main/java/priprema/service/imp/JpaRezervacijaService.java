package priprema.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priprema.model.Linija;
import priprema.model.Rezervacija;
import priprema.repository.LinijaRepository;
import priprema.repository.RezervacijaRepository;
import priprema.service.LinijaService;
import priprema.service.RezervacijaService;

@Service
public class JpaRezervacijaService implements RezervacijaService{
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	@Autowired
	private LinijaService linijaService;

	@Override
	public Rezervacija findOne(Long id) {
		// TODO Auto-generated method stub
		return rezervacijaRepository.findOne(id);
	}

	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		// TODO Auto-generated method stub
		Linija linija = rezervacija.getLinija();

		linija.addRezervacija(rezervacija);
		
		return rezervacijaRepository.save(rezervacija);
	}

	@Override
	public Rezervacija delete(Long id) {
		// TODO Auto-generated method stub
		
		Rezervacija toDelete = rezervacijaRepository.findOne(id);
		
		if(toDelete == null) {
			throw new IllegalArgumentException("Pokusali ste da obrisete rezervaciju koja ne postoji!");
		}
		return toDelete;
	}

	@Override
	public List<Rezervacija> findAll() {
		// TODO Auto-generated method stub
		return rezervacijaRepository.findAll();
	}

}
