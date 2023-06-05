package hr.fer.zavrsni.backend.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Disciplina;
import hr.fer.zavrsni.backend.model.Natjecanje;
import hr.fer.zavrsni.backend.model.Rezultat;


@Repository
public interface RezultatRepository extends JpaRepository<Rezultat, Long> {
	
	@Query("SELECT DISTINCT o.idosoba AS idosoba, o.imeosoba AS imeosoba, o.prezimeosoba AS prezimeosoba, k.nazivklub AS nazivklub, " +
			   "ARRAY_AGG(r.disciplina.nazivdisciplina) AS discipline, MIN(r.vrijeme) AS minvrijeme " +
		       "FROM Rezultat r " +
		       "JOIN Osoba o ON r.osoba.idosoba = o.idosoba " +
		       "JOIN Limit l ON r.disciplina.iddisciplina = l.disciplina.iddisciplina " +
		       "JOIN Klub k ON o.klub.idklub = k.idklub " +
		       "WHERE r.vrijeme <= l.vrijeme " +
		       "AND o.drzava.iddrzava = 8 " +
		       "GROUP BY o.idosoba, o.imeosoba, o.prezimeosoba, k.nazivklub " +
		       "ORDER BY minvrijeme")
	List<Object[]> findKvalificirani();


	
	List<Rezultat> findByNatjecanjeIdnatjecanjeOrderByDisciplinaIddisciplina(Long id);
	
	List<Rezultat> findByNatjecanjeIdnatjecanjeAndDisciplinaIddisciplinaOrderByVrijemeAsc(Long idn, Long idd);
	
	
	@Query("SELECT r.disciplina.iddisciplina, d.nazivdisciplina, MIN(r.vrijeme) AS vrijeme, r.bodovi, r.datum, o "
		       + "FROM Rezultat r "
		       + "JOIN Osoba o ON r.osoba.idosoba = o.idosoba " 
		       + "JOIN Disciplina d ON r.disciplina.iddisciplina = d.iddisciplina "
		       + "WHERE r.osoba.idosoba = :idosoba "
		       + "GROUP BY r.disciplina.iddisciplina, d.nazivdisciplina, r.bodovi, r.datum, o "
		       + "ORDER BY r.disciplina.iddisciplina ASC")
	List<Object[]> findRezultatiByOsoba(@Param("idosoba") Long idosoba);





}

