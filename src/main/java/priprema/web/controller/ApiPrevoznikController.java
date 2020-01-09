package priprema.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import priprema.model.Prevoznik;
import priprema.service.PrevoznikService;
import priprema.support.PrevoznikDTOToPrevoznik;
import priprema.support.PrevoznikToPrevoznikDTO;
import priprema.web.dto.PrevoznikDTO;

@RestController
@RequestMapping(value = "api/prevoznici")
public class ApiPrevoznikController {
	
	@Autowired
	private PrevoznikService prevoznikService;

	@Autowired
	private PrevoznikDTOToPrevoznik prevoznikDTOToPrevoznik;
	
	@Autowired
	private PrevoznikToPrevoznikDTO prevoznikToPrevoznikDTO;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PrevoznikDTO>> getPrevoznici(){
		List<Prevoznik> prevoznici = prevoznikService.findAll();
		
		return new ResponseEntity<>(prevoznikToPrevoznikDTO.convert(prevoznici), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PrevoznikDTO> addPrevoznik(@RequestBody PrevoznikDTO prevoznikDto){
		
		Prevoznik prevoznik = prevoznikDTOToPrevoznik.convert(prevoznikDto);
		
		prevoznikService.save(prevoznik);
		
		return new ResponseEntity<PrevoznikDTO>(prevoznikDto, HttpStatus.CREATED);
		
	}
}
