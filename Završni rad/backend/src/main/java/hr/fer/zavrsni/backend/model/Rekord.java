package hr.fer.zavrsni.backend.model;

import java.sql.Timestamp;

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
    private Timestamp vrijeme;


    public Rekord() {
    }

    public Rekord(Long idrekord, Disciplina disciplina, Timestamp vrijeme) {
        this.idrekord = idrekord;
        this.disciplina = disciplina;
        this.vrijeme = vrijeme;
    }


}
