package priprema.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import priprema.model.Rezervacija;
import priprema.service.RezervacijaService;
import priprema.support.RezervacijaDTOToRezervacija;
import priprema.support.RezervacijaToRezervacijaDTO;
import priprema.web.dto.RezervacijaDTO;

@RestController
@RequestMapping(value = "api/rezervacije")
public class ApiRezervacijeController {

	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private RezervacijaToRezervacijaDTO toDTO;
	
	@Autowired
	private RezervacijaDTOToRezervacija rezervacijaDTOToRezervacija;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RezervacijaDTO>> getAll(){
		List<Rezervacija> rezervacije = rezervacijaService.findAll();
		
		return new ResponseEntity<List<RezervacijaDTO>>(toDTO.convert(rezervacije), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<RezervacijaDTO> add(
			@RequestBody 
			@Validated
			RezervacijaDTO rezervacijaDTO){
		Rezervacija toSave = rezervacijaDTOToRezervacija.convert(rezervacijaDTO);
		
		if(toSave == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		rezervacijaService.save(toSave);
		
		return new ResponseEntity<RezervacijaDTO>(toDTO.convert(toSave), HttpStatus.CREATED);
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
