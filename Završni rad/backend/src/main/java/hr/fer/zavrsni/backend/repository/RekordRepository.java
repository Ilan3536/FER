package hr.fer.zavrsni.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Rekord;

@Repository
public interface RekordRepository extends JpaRepository<Rekord, Long> {

	Optional<Rekord> findById(Long id);
}
