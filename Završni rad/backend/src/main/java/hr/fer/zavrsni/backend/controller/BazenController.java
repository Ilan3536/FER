package hr.fer.zavrsni.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.zavrsni.backend.model.*;
import hr.fer.zavrsni.backend.repository.BazenRepository;

@RestController
@RequestMapping("/bazeni")
public class BazenController {
	
	private final BazenRepository bazenRepository;

	public BazenController(BazenRepository bazenRepository) {
		super();
		this.bazenRepository = bazenRepository;
	}
	
	
	@GetMapping
	public ResponseEntity<List<Object[]>> getPools() {
		return ResponseEntity.ok(this.bazenRepository.findPools());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Bazen>> getAllBazeni() {
		return ResponseEntity.ok(this.bazenRepository.findAll());
	}

}
