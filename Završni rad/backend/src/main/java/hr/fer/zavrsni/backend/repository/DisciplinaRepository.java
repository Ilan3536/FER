package hr.fer.zavrsni.backend.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Bazen;
import hr.fer.zavrsni.backend.model.Disciplina;
import hr.fer.zavrsni.backend.model.Rezultat;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{
	
	@Query("SELECT DISTINCT r.disciplina.iddisciplina, r.disciplina.nazivdisciplina, r.disciplina.spol "
	        + "FROM Rezultat r "
	        + "WHERE r.natjecanje.idnatjecanje = :idnatjecanje "
	        + "ORDER BY r.disciplina.iddisciplina")
	List<Object[]> findByNatjecanjeIdnatjecanjeOrderByIddisciplina(@Param("idnatjecanje") Long idnatjecanje);

	List<Disciplina> findByNazivdisciplina(String nazivdisciplina);


}
