package hr.fer.zavrsni.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hr.fer.zavrsni.backend.model.Disciplina;
import hr.fer.zavrsni.backend.repository.DisciplinaRepository;

@RestController
@RequestMapping("/discipline")
public class DisciplinaController {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaController(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> getAllDiscipline() {
        List<Disciplina> discipline = disciplinaRepository.findAll();
        return ResponseEntity.ok(discipline);
    }


}

