package hr.fer.zavrsni.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.zavrsni.backend.model.NastupaZa;
import hr.fer.zavrsni.backend.repository.NastupaZaRepository;

import java.util.List;

@RestController
@RequestMapping("/nastupaza")
public class NastupaZaController {

    private final NastupaZaRepository nastupaZaRepository;

    public NastupaZaController(NastupaZaRepository nastupaZaRepository) {
        this.nastupaZaRepository = nastupaZaRepository;
    }

    @GetMapping
    public ResponseEntity<List<NastupaZa>> getAllNastupaZa() {
        List<NastupaZa> nastupaZaList = nastupaZaRepository.findAll();
        return ResponseEntity.ok(nastupaZaList);
    }
    
}

