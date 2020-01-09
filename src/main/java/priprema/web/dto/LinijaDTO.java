package priprema.web.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class LinijaDTO {

	private Long id;
	
	@Min(value = 0)
	private int brojMesta;
	private double cenaKarte;
	private String vremePolaska;
	
	@NotBlank
	@NotEmpty
	@NotNull
	private String destinacija;
	private Long idPrevoznika;
	private String nazivPrevoznika;
	
	
	public LinijaDTO() {
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


	public Long getIdPrevoznika() {
		return idPrevoznika;
	}


	public void setIdPrevoznika(Long idPrevoznika) {
		this.idPrevoznika = idPrevoznika;
	}


	public String getNazivPrevoznika() {
		return nazivPrevoznika;
	}


	public void setNazivPrevoznika(String nazivPrevoznika) {
		this.nazivPrevoznika = nazivPrevoznika;
	}
	
	
	
}
