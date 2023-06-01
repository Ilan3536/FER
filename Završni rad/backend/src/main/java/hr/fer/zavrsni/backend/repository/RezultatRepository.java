package hr.fer.zavrsni.backend.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Rezultat;


@Repository
public interface RezultatRepository extends JpaRepository<Rezultat, Long> {
	
	@Query("SELECT DISTINCT o.idosoba AS idosoba, o.imeosoba AS imeosoba, o.prezimeosoba AS prezimeosoba, MIN(r.idrezultat) AS idrezultat " +
		       "FROM Rezultat r " +
		       "JOIN Osoba o ON r.idosoba = o.idosoba " +
		       "JOIN Limit l ON r.disciplina.iddisciplina = l.disciplina.iddisciplina " +
		       "WHERE r.vrijeme <= l.vrijeme " +
		       "GROUP BY o.idosoba, o.imeosoba, o.prezimeosoba " +
		       "ORDER BY idrezultat")
	List<Object[]> findKvalificirani();
		
		
	List<Rezultat> findByNatjecanjeIdnatjecanjeAndDisciplinaIddisciplina(Long idnatjecanje, Long iddisciplina);


}

