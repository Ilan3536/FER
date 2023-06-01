package hr.fer.zavrsni.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.zavrsni.backend.model.Natjecanje;
import hr.fer.zavrsni.backend.repository.NatjecanjeRepository;

import java.util.List;

@RestController
@RequestMapping("/natjecanja")
public class NatjecanjeController {

    private final NatjecanjeRepository natjecanjeRepository;

    public NatjecanjeController(NatjecanjeRepository natjecanjeRepository) {
        this.natjecanjeRepository = natjecanjeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Natjecanje>> getAllNatjecanje() {
        List<Natjecanje> natjecanjeList = natjecanjeRepository.findAll();
        return ResponseEntity.ok(natjecanjeList);
    }

    // Add more controller methods as per your requirements

    // ...
}

