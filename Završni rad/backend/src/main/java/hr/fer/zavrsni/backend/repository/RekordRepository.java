package hr.fer.zavrsni.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Rekord;

@Repository
public interface RekordRepository extends JpaRepository<Rekord, Long> {
	
	@Query("SELECT o.imeosoba, o.prezimeosoba, d.nazivdisciplina, d.spol, to_char(r.vrijeme, 'HH24:MI:SS.MS') AS vrijeme, n.nazivnatjecanje " +
		       "FROM Disciplina d " +
		       "INNER JOIN Rezultat r ON d.iddisciplina = r.disciplina.iddisciplina " +
		       "INNER JOIN Osoba o ON r.osoba.idosoba = o.idosoba " +
		       "INNER JOIN Natjecanje n ON r.natjecanje.idnatjecanje = n.idnatjecanje " +
		       "WHERE r.idrezultat <= 34 " +
		       "ORDER BY d.spol DESC, r.vrijeme ASC")
	List<Object[]> findRekordi1();
	
	
	@Query("SELECT o.imeosoba, o.prezimeosoba, d.nazivdisciplina, d.spol, to_char(r.vrijeme, 'HH24:MI:SS.MS') AS vrijeme, n.nazivnatjecanje, "
			+ "osoba.drzava.nazivdrzava "
	        + "FROM Rezultat r "
	        + "INNER JOIN Osoba o ON r.osoba.idosoba = o.idosoba "
	        + "INNER JOIN Disciplina d ON r.disciplina.iddisciplina = d.iddisciplina "
		    + "INNER JOIN Natjecanje n ON r.natjecanje.idnatjecanje = n.idnatjecanje "
	        + "WHERE r.vrijeme = "
	        + "(SELECT rk.vrijeme FROM Rekord rk WHERE rk.disciplina.iddisciplina = r.disciplina.iddisciplina) "
	        + "ORDER BY d.spol DESC, r.disciplina.iddisciplina")
	List<Object[]> findRekordi();

	
	Optional<Rekord> findById(Long id);
	
	Optional<Rekord> findByDisciplinaIddisciplina(Long iddisciplina);
}
