package hr.fer.zavrsni.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Osoba;

@Repository
public interface OsobaRepository extends JpaRepository<Osoba, Long> {

	List<Osoba> findBySpol(String spol);

}

