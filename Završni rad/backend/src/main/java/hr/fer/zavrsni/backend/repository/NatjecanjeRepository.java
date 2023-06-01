package hr.fer.zavrsni.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Natjecanje;

@Repository
public interface NatjecanjeRepository extends JpaRepository<Natjecanje, Long> {
    // Add custom query methods if needed
}

