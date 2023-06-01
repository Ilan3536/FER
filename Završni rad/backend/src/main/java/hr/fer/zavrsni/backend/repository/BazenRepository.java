package hr.fer.zavrsni.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Bazen;

@Repository
public interface BazenRepository extends JpaRepository<Bazen, Long>{

}
