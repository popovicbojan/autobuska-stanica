package priprema.web.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import priprema.model.Linija;
import priprema.service.LinijaService;
import priprema.support.LinijaDTOToLinija;
import priprema.support.LinijaToLinijaDTO;
import priprema.web.dto.LinijaDTO;

@RestController
@RequestMapping(value = "api/linije")
public class ApiLinijeController {
	
	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private LinijaToLinijaDTO toDTO;
	
	@Autowired
	private LinijaDTOToLinija toLinija;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<LinijaDTO>> getAll(
			@RequestParam(required = false) String destinacija,
			@RequestParam(required = false) String prevoznik,
			@RequestParam(required = false) Double maxCena,
			@RequestParam(value = "page", defaultValue = "0") int page){
		
		Page<Linija> linije;
		
		if(destinacija != null || prevoznik != null || maxCena != null) {
			linije = linijaService.search(destinacija, prevoznik, maxCena, page);
		} else {
			linije = linijaService.findAll(page);
		}
		
		HttpHeaders header = new HttpHeaders();
		header.add("ukupnoStrana", Integer.toString(linije.getTotalPages()));
		
		return new ResponseEntity<List<LinijaDTO>>(
				toDTO.convert(linije.getContent()),
				header,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<LinijaDTO> getOne(@PathVariable Long id){
		Linija linija = linijaService.findOne(id);
		
		if(linija == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<LinijaDTO>(toDTO.convert(linija), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<LinijaDTO> addOne(
			@RequestBody 
			@Validated
			LinijaDTO linijaDTO){
		Linija linija = toLinija.convert(linijaDTO);
		linijaService.save(linija);
		
		return new ResponseEntity<LinijaDTO>(toDTO.convert(linija), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<LinijaDTO> edit(
			@PathVariable Long id,
			@RequestBody LinijaDTO linijaDTO){
		
		if(!id.equals(linijaDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Linija toSave = toLinija.convert(linijaDTO);
		
		linijaService.save(toSave);
		
		return new ResponseEntity<LinijaDTO>(toDTO.convert(toSave), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<LinijaDTO> delete(@PathVariable Long id){
		Linija deleted = linijaService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<LinijaDTO>(toDTO.convert(deleted), HttpStatus.OK);
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
