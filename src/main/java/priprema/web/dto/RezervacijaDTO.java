package priprema.web.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class RezervacijaDTO {

	private Long id;
	private Long idLinije;
	

	private String destinacija;
	private Long idPrevoznika;
	private String nazivPrevoznika;
	
	@Min(value = 0)
	private int brojMesta;
	
	
	public RezervacijaDTO() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdLinije() {
		return idLinije;
	}


	public void setIdLinije(Long idLinije) {
		this.idLinije = idLinije;
	}





	public String getDestinacija() {
		return destinacija;
	}


	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}


	public int getBrojMesta() {
		return brojMesta;
	}


	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
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
