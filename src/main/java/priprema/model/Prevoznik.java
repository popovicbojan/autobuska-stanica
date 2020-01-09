package priprema.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_prevoznik")
public class Prevoznik {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "naziv", nullable = false, unique = true)
	private String naziv;
	
	@Column(name = "adresa")
	private String adresa;
	
	@Column(name = "pib", nullable = false, unique = true)
	private String pib;
	
	@OneToMany(mappedBy = "prevoznik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Linija> linije = new ArrayList<Linija>();

	public Prevoznik(String naziv, String adresa, String pib) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.pib = pib;
	}

	public Prevoznik() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public List<Linija> getLinije() {
		return linije;
	}

	public void setLinije(List<Linija> linije) {
		this.linije = linije;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}
	
	public void addLinija(Linija linija) {
		linije.add(linija);
		if(linija.getPrevoznik() != null && !linija.getPrevoznik().equals(this)) {
			linija.setPrevoznik(this);
		}
	}
	
}
