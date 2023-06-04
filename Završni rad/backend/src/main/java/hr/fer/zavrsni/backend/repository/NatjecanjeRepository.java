package hr.fer.zavrsni.backend.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Natjecanje;

@Repository
public interface NatjecanjeRepository extends JpaRepository<Natjecanje, Long> {

	List<Natjecanje> findByIdnatjecanje(Long idnatjecanje);
	
	


	
	
	
	
}

