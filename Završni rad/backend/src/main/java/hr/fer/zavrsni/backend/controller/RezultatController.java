package hr.fer.zavrsni.backend.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import hr.fer.zavrsni.backend.model.Disciplina;
import hr.fer.zavrsni.backend.model.Rezultat;
import hr.fer.zavrsni.backend.repository.RezultatRepository;

import java.util.List;

@RestController
@RequestMapping("/rezultati")
public class RezultatController {

    private final RezultatRepository rezultatRepository;

    public RezultatController(RezultatRepository rezultatRepository) {
        this.rezultatRepository = rezultatRepository;
    }

    @GetMapping
    public ResponseEntity<List<Rezultat>> getAllRezultati() {
        List<Rezultat> rezultatList = rezultatRepository.findAll();
        return ResponseEntity.ok(rezultatList);
    }
    
    @GetMapping("/kvalificirani")
    public List<Object[]> getKvalificirani() {
    	return rezultatRepository.findKvalificirani();
    }
    
    
    @GetMapping("/natjecanje/{idn}/disciplina/{idd}")
    public ResponseEntity<List<Rezultat>> getRezultatById(
    		@PathVariable("idn") Long idn, 
    		@PathVariable("idd") Long idd) {
        List<Rezultat> results = rezultatRepository.findByNatjecanjeIdnatjecanjeAndDisciplinaIddisciplinaOrderByVrijemeAsc(idn, idd);
        return ResponseEntity.ok(results);
    }

    
    @GetMapping("/natjecanje/{id}")
    public ResponseEntity<List<Rezultat>> getRezultatiByNatjecanje(@PathVariable("id") Long id) {
    	List<Rezultat> rezultatList = rezultatRepository.findByNatjecanjeIdnatjecanjeOrderByDisciplinaIddisciplina(id);
    	return ResponseEntity.ok(rezultatList);
    }
    
    @GetMapping("/natjecanje/count/{id}")
    public ResponseEntity<List<Object[]>> getRezultatiCountByDisciplina(@PathVariable("id") Long id) {
    	List<Object[]> rezultatList = rezultatRepository.findCountByNatjecanjeIdNatjecanje(id);
    	return ResponseEntity.ok(rezultatList);
    }
    
    @GetMapping("/osoba/{id}")
    public ResponseEntity<List<Object[]>> getRezultatiByOsoba(@PathVariable("id") Long id) {
    	List<Object[]> rezultatList = rezultatRepository.findRezultatiByOsoba(id);
    	return ResponseEntity.ok(rezultatList);
    }
    
    
}
