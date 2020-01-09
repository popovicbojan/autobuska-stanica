package priprema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_rezervacija")
public class Rezervacija {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Linija linija;
	
	@Column(name = "broj_mesta", nullable = false)
	private int brojMesta;

	public Rezervacija(Linija linija, int brojMesta) {
		super();
		this.linija = linija;
		this.brojMesta = brojMesta;
	}

	public Rezervacija() {
		super();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Linija getLinija() {
		return linija;
	}

	public void setLinija(Linija linija) {
		this.linija = linija;
		if(!linija.getRezervacije().contains(this)) {
			linija.getRezervacije().add(this);
		}
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	
	
}
