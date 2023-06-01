package hr.fer.zavrsni.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.zavrsni.backend.model.Klub;
import hr.fer.zavrsni.backend.repository.KlubRepository;

@RestController
@RequestMapping("/klubovi")
public class KlubController {
	
	private final KlubRepository klubRepository;

	public KlubController(KlubRepository klubRepository) {
		super();
		this.klubRepository = klubRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Klub>> getAllKlubovi() {
		return ResponseEntity.ok(this.klubRepository.findAll());
	}
}

