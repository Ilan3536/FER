package hr.fer.zavrsni.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hr.fer.zavrsni.backend.model.Osoba;
import hr.fer.zavrsni.backend.repository.OsobaRepository;

import java.util.List;

@RestController
@RequestMapping("/osobe")
public class OsobaController {

    private final OsobaRepository osobaRepository;

    public OsobaController(OsobaRepository osobaRepository) {
        this.osobaRepository = osobaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Osoba>> getAllOsoba() {
        List<Osoba> osobaList = osobaRepository.findAll();
        return ResponseEntity.ok(osobaList);
    }
    
    @GetMapping("/{spol}")
    public ResponseEntity<List<Osoba>> getAllOsobaBySpol(@PathVariable String spol) {
        List<Osoba> osobaList = osobaRepository.findBySpol(spol);
        return ResponseEntity.ok(osobaList);
    }

}

