package hr.fer.zavrsni.backend.model;

import lombok.Data;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "rezultat")
@Data
public class Rezultat implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrezultat")
    private Long idrezultat;

    @ManyToOne
    @JoinColumn(name = "iddisciplina", referencedColumnName = "iddisciplina")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "idnatjecanje", referencedColumnName = "idnatjecanje")
    private Natjecanje natjecanje;

    @Column(name = "vrijeme")
    private Time vrijeme;

    @Column(name = "bodovi")
    private Integer bodovi;

    @Column(name = "datum")
    private Date datum;

    @ManyToOne
    @JoinColumn(name = "idosoba", referencedColumnName = "idosoba")
    private Osoba osoba;

    // Constructors

    public Rezultat() {
    }

    public Rezultat(Long idrezultat, Disciplina disciplina, Natjecanje natjecanje, Time vrijeme, Integer bodovi, Date datum, Osoba osoba) {
        this.idrezultat = idrezultat;
        this.disciplina = disciplina;
        this.natjecanje = natjecanje;
        this.vrijeme = vrijeme;
        this.bodovi = bodovi;
        this.datum = datum;
        this.osoba = osoba;
    }

    // ...
}

