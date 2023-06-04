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
	
	List<Disciplina> findByNazivdisciplina(String nazivdisciplina);
	
	@Query("SELECT DISTINCT disciplina FROM Rezultat r WHERE r.natjecanje.idnatjecanje = :idnatjecanje")
    List<Disciplina> findByIdnatjecanjeGroupByDisciplina(@Param("idnatjecanje") Long idnatjecanje);


}
