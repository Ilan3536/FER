package hr.fer.zavrsni.backend.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.zavrsni.backend.model.Limit;
import hr.fer.zavrsni.backend.repository.LimitRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/limiti")
public class LimitController {

    private final LimitRepository limitRepository;

    public LimitController(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    @GetMapping
    public ResponseEntity<List<Limit>> getAllLimits() {
        List<Limit> limits = limitRepository.findAll();
        return ResponseEntity.ok(limits);
    }
    
    
}

