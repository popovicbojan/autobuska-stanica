package priprema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import priprema.model.Linija;

@Repository
public interface LinijaRepository extends JpaRepository<Linija, Long>{
	
	@Query("SELECT l FROM Linija l WHERE"
			+ "(:destinacija is NULL or l.destinacija LIKE :destinacija) AND"
			+ "(:prevoznik is NULL or l.prevoznik.naziv LIKE :prevoznik) AND"
			+ "(:maxCena is NULL or l.cenaKarte <= :maxCena)"
			)
	Page<Linija> search(
			@Param("destinacija") String destinacija,
			@Param("prevoznik") String prevoznik,
			@Param("maxCena") Double maxCena,
			Pageable pageRequest);

}
