package hr.fer.zavrsni.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hr.fer.zavrsni.backend.model.Natjecanje;
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
    
    @GetMapping("/{id}")
    public ResponseEntity<List<Osoba>> getNatjecanjeDetailsById(@PathVariable Long id) {
        List<Osoba> results = osobaRepository.findByIdosoba(id);
        return ResponseEntity.ok(results);
    }
    
    @GetMapping("/{spol}")
    public ResponseEntity<List<Osoba>> getAllOsobaBySpol(@PathVariable String spol) {
        List<Osoba> osobaList = osobaRepository.findBySpol(spol);
        return ResponseEntity.ok(osobaList);
    }
    
    @GetMapping("/{ime}/{prezime}")
    public ResponseEntity<List<Osoba>> getOsobaByImeAndPrezime(@PathVariable String ime, @PathVariable String prezime) {
    	ime = ime.substring(0, 1).toUpperCase() + ime.substring(1).toLowerCase();
    	prezime = prezime.substring(0, 1).toUpperCase() + prezime.substring(1).toLowerCase();
        List<Osoba> osobaList = osobaRepository.findByImeosobaAndPrezimeosoba(ime, prezime);
        return ResponseEntity.ok(osobaList);
    }

}

