package hr.fer.zavrsni.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Bazen;

@Repository
public interface BazenRepository extends JpaRepository<Bazen, Long>{
	
	@Query("SELECT b.idbazen, b.nazivbazen, b.kapacitet, d.nazivdrzava, b.grad, b.adresa " +
		       "FROM Bazen b " +
		       "JOIN Drzava d ON b.drzava.iddrzava = d.iddrzava " +
		       "ORDER BY b.idbazen")
	List<Object[]> findPools();

}
