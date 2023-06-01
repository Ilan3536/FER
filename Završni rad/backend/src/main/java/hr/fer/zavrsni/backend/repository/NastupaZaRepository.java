package hr.fer.zavrsni.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.NastupaZa;

@Repository
public interface NastupaZaRepository extends JpaRepository<NastupaZa, Long> {
    // Add custom query methods if needed
}

