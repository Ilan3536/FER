package hr.fer.zavrsni.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import hr.fer.zavrsni.backend.model.Rekord;
import hr.fer.zavrsni.backend.repository.RekordRepository;

import java.util.List;

@RestController
@RequestMapping("/rekordi")
public class RekordController {

    private final RekordRepository rekordRepository;

    @Autowired
    public RekordController(RekordRepository rekordRepository) {
        this.rekordRepository = rekordRepository;
    }

    @GetMapping
    public List<Rekord> getAllRekordi() {
        return rekordRepository.findAll();
    }

    @GetMapping("/{id}")
    public Rekord getRekordById(@PathVariable Long id) {
        return rekordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rekord ID: " + id));
    }

    @PostMapping
    public Rekord createRekord(@RequestBody Rekord rekord) {
        return rekordRepository.save(rekord);
    }

    @PutMapping("/{id}")
    public Rekord updateRekord(@PathVariable Long id, @RequestBody Rekord updatedRekord) {
        Rekord rekord = rekordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rekord ID: " + id));

        rekord.setDisciplina(updatedRekord.getDisciplina());
        rekord.setVrijeme(updatedRekord.getVrijeme());

        return rekordRepository.save(rekord);
    }

    @DeleteMapping("/{id}")
    public void deleteRekord(@PathVariable Long id) {
        rekordRepository.deleteById(id);
    }
}

