package priprema;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import priprema.model.Linija;
import priprema.model.Prevoznik;
import priprema.model.Rezervacija;
import priprema.service.LinijaService;
import priprema.service.PrevoznikService;
import priprema.service.RezervacijaService;

@Component
public class TestData {
	
	@Autowired
	private PrevoznikService prevoznikService;
	
	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@PostConstruct
	public void init() {
		Prevoznik p1 = new Prevoznik();
		p1.setAdresa("Nis");
		p1.setNaziv("Nisexpress");
		p1.setPib("923812903");
		prevoznikService.save(p1);
		
		Prevoznik p2 = new Prevoznik();
		p2.setAdresa("Beograd");
		p2.setNaziv("Lasta");
		p2.setPib("12345543");
		prevoznikService.save(p2);
		
		Linija l1 = new Linija();
		l1.setBrojMesta(50);
		l1.setCenaKarte(1400);
		l1.setDestinacija("Novi Sad");
		l1.setPrevoznik(p1);
		l1.setVremePolaska("12:30");
		linijaService.save(l1);
		
		Linija l2 = new Linija();
		l2.setBrojMesta(100);
		l2.setCenaKarte(1000);
		l2.setDestinacija("Beograd");
		l2.setPrevoznik(p2);
		l2.setVremePolaska("10:45");
		linijaService.save(l2);
		
		Linija l3 = new Linija();
		l3.setBrojMesta(100);
		l3.setCenaKarte(1200);
		l3.setDestinacija("Subotica");
		l3.setPrevoznik(p2);
		l3.setVremePolaska("09:30");
		linijaService.save(l3);
		
		Linija l4 = new Linija();
		l4.setBrojMesta(60);
		l4.setCenaKarte(800);
		l4.setDestinacija("Zrenjanin");
		l4.setPrevoznik(p1);
		l4.setVremePolaska("16:30");
		linijaService.save(l4);
		
		Linija l5 = new Linija();
		l5.setBrojMesta(75);
		l5.setCenaKarte(500);
		l5.setDestinacija("Beocin");
		l5.setPrevoznik(p2);
		l5.setVremePolaska("12:30");
		linijaService.save(l5);
		
		Linija l6 = new Linija();
		l6.setBrojMesta(100);
		l6.setCenaKarte(1600);
		l6.setDestinacija("Sid");
		l6.setPrevoznik(p1);
		l6.setVremePolaska("12:30");
		linijaService.save(l6);
		
		Rezervacija r1 = new Rezervacija();
		r1.setBrojMesta(5);
		r1.setLinija(l1);
		rezervacijaService.save(r1);
		
		p1.addLinija(l1);
		l1.addRezervacija(r1);
	}

}
