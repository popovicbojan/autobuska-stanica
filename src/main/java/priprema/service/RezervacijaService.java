package priprema.service;

import java.util.List;

import priprema.model.Rezervacija;

public interface RezervacijaService {
	
	Rezervacija findOne(Long id);
	Rezervacija save(Rezervacija rezervacija);
	Rezervacija delete(Long id);
	List<Rezervacija> findAll();

}
