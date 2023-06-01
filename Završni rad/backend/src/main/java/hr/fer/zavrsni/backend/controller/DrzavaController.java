package hr.fer.zavrsni.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.zavrsni.backend.model.Drzava;
import hr.fer.zavrsni.backend.repository.DrzavaRepository;

@RestController
@RequestMapping("/drzave")
public class DrzavaController {

	private final DrzavaRepository drzavaRepository;

	public DrzavaController(DrzavaRepository drzavaRepository) {
		super();
		this.drzavaRepository = drzavaRepository;
	}
	
	
	@GetMapping
	public ResponseEntity<List<Drzava>> getAllDrzave() {
		return ResponseEntity.ok(this.drzavaRepository.findAll());
	}
	
	
}
