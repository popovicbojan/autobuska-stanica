package priprema.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import priprema.model.Linija;
import priprema.repository.LinijaRepository;
import priprema.service.LinijaService;

@Service
public class JpaLinijaService implements LinijaService{
	
	@Autowired
	private LinijaRepository linijaRepository;

	@Override
	public Linija findOne(Long id) {
		// TODO Auto-generated method stub
		return linijaRepository.findOne(id);
	}

	@Override
	public Linija save(Linija linija) {
		// TODO Auto-generated method stub
		return linijaRepository.save(linija);
	}

	@Override
	public Linija delete(Long id) {
		// TODO Auto-generated method stub
		
		Linija toDelete = linijaRepository.findOne(id);
		
		if(toDelete == null) {
			throw new IllegalArgumentException("Pokusali ste da obrisete liniju koja ne postoji!");
		}
		
		linijaRepository.delete(toDelete);
		
		return toDelete;
	}

	@Override
	public List<Linija> findAll() {
		// TODO Auto-generated method stub
		return linijaRepository.findAll();
	}

	@Override
	public Page<Linija> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return linijaRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Linija> search(String destinacija, String prevoznik, Double maxCena, int pageNum) {
		// TODO Auto-generated method stub
		
		if (destinacija != null) {
			destinacija = "%" + destinacija + "%";
		}
		if(prevoznik != null) {
			prevoznik = "%" + prevoznik + "%";
		}
		
		return linijaRepository.search(destinacija, prevoznik, maxCena, new PageRequest(pageNum, 5));
	}

}
