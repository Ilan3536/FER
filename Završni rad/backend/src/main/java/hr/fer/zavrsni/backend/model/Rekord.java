package hr.fer.zavrsni.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rekord")
public class Rekord {

    @Id
    @Column(name = "idrekord")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idrekord;

    @OneToOne
    @JoinColumn(name = "iddisciplina", referencedColumnName = "iddisciplina")
    private Disciplina disciplina;

    @Column(name = "vrijeme")
    private String vrijeme;


    public Rekord() {
    }

    public Rekord(Long idrekord, Disciplina disciplina, String vrijeme) {
        this.idrekord = idrekord;
        this.disciplina = disciplina;
        this.vrijeme = vrijeme;
    }


}
