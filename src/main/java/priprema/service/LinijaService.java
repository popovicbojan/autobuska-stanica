package priprema.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import priprema.model.Linija;

public interface LinijaService {
	
	Linija findOne(Long id);
	Linija save(Linija linija);
	Linija delete(Long id);
	List<Linija> findAll();
	Page<Linija> findAll(int pageNum);
	Page<Linija> search(
			@Param("destinacija") String destinacija,
			@Param("prevoznik") String prevoznik,
			@Param("maxCena") Double maxCena,
			int pageNum);

}
