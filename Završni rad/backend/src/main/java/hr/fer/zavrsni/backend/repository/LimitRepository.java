package hr.fer.zavrsni.backend.repository;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.zavrsni.backend.model.Limit;


@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
	
    List<Limit> findByDisciplinaIddisciplina(Long iddisciplina);
}

