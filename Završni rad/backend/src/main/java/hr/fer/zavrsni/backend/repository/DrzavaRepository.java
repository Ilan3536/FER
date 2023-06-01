package hr.fer.zavrsni.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Drzava;

@Repository
public interface DrzavaRepository extends JpaRepository<Drzava, Long>{

}
