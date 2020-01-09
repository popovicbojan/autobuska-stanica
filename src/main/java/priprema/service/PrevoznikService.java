package priprema.service;

import java.util.List;

import priprema.model.Prevoznik;

public interface PrevoznikService {
	
	Prevoznik findOne(Long id);
	Prevoznik save(Prevoznik prevoznik);
	Prevoznik delete(Long id);
	List<Prevoznik> findAll();

}
