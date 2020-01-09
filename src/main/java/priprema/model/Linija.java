package priprema.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_linija")
public class Linija {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "broj_mesta", nullable = false)
	private int brojMesta;
	
	@Column(name = "cena_karte")
	private double cenaKarte;
	
	@Column(name = "vreme_polaska")
	private String vremePolaska;
	
	@Column(name = "destinacija")
	private String destinacija;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Prevoznik prevoznik;
	
	@OneToMany(mappedBy = "linija", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Rezervacija> rezervacije = new ArrayList<Rezervacija>();

	public Linija(int brojMesta, double cenaKarte, String vremePolaska, String destinacija, Prevoznik prevoznik,
			List<Rezervacija> rezervacije) {
		super();
		this.brojMesta = brojMesta;
		this.cenaKarte = cenaKarte;
		this.vremePolaska = vremePolaska;
		this.destinacija = destinacija;
		this.prevoznik = prevoznik;
		this.rezervacije = rezervacije;
	}

	public Linija() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public String getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	public Prevoznik getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
		if(!prevoznik.getLinije().contains(this)) {
			prevoznik.getLinije().add(this);
		}
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	public void addRezervacija(Rezervacija rezervacija) {
		this.rezervacije.add(rezervacija);
		this.brojMesta -= rezervacija.getBrojMesta();
		if(rezervacija.getLinija() != null && !rezervacija.getLinija().equals(this)) {
			rezervacija.setLinija(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linija other = (Linija) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
