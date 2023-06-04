package hr.fer.zavrsni.backend.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Natjecanje;
import hr.fer.zavrsni.backend.model.Rezultat;


@Repository
public interface RezultatRepository extends JpaRepository<Rezultat, Long> {
	
	@Query("SELECT DISTINCT o.idosoba AS idosoba, o.imeosoba AS imeosoba, o.prezimeosoba AS prezimeosoba, MIN(r.idrezultat) AS idrezultat " +
		       "FROM Rezultat r " +
		       "JOIN Osoba o ON r.osoba.idosoba = o.idosoba " +
		       "JOIN Limit l ON r.disciplina.iddisciplina = l.disciplina.iddisciplina " +
		       "WHERE r.vrijeme <= l.vrijeme " +
		       "GROUP BY o.idosoba, o.imeosoba, o.prezimeosoba " +
		       "ORDER BY o.idosoba")
	List<Object[]> findKvalificirani();


	List<Rezultat> findByNatjecanjeIdnatjecanje(Long id);

	List<Rezultat> findByNatjecanjeIdnatjecanjeAndDisciplinaIddisciplinaOrderByVrijemeAsc(Long idn, Long idd);
	
	@Query("SELECT DISTINCT disciplina FROM Rezultat r WHERE r.natjecanje.idnatjecanje = :idnatjecanje")
    List<Rezultat> findByIdnatjecanjeGroupByDisciplina(@Param("idnatjecanje") Long idnatjecanje);



}

