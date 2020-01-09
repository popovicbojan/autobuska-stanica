package priprema.service.imp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priprema.model.Prevoznik;
import priprema.repository.PrevoznikRepository;
import priprema.service.PrevoznikService;

@Service
public class JpaPrevoznikService implements PrevoznikService{

	@Autowired
	private PrevoznikRepository prevoznikRepository;
	
	
	@Override
	public Prevoznik findOne(Long id) {
		// TODO Auto-generated method stub
		return prevoznikRepository.findOne(id);
	}

	@Override
	public Prevoznik save(Prevoznik prevoznik) {
		// TODO Auto-generated method stub
		return prevoznikRepository.save(prevoznik);
	}

	@Override
	public Prevoznik delete(Long id) {
		// TODO Auto-generated method stub
		Prevoznik toDelete = prevoznikRepository.findOne(id);
		if(toDelete == null) {
			throw new IllegalArgumentException("Pokusaj brisanja nepostojeceg prevoznika!");
		}
		
		prevoznikRepository.delete(toDelete);
		
		return toDelete;
	}

	@Override
	public List<Prevoznik> findAll() {
		// TODO Auto-generated method stub
		return prevoznikRepository.findAll();
	}

}
