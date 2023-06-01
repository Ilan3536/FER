package hr.fer.zavrsni.backend.model;

import lombok.Data;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "osoba")
@Data
public class Osoba implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idosoba")
    private Long idosoba;

	@ManyToOne
    @JoinColumn(name = "iddrzava", referencedColumnName = "iddrzava")
	private Drzava drzava;

    @Column(name = "imeosoba")
    private String imeosoba;

    @Column(name = "prezimeosoba")
    private String prezimeosoba;

    @Column(name = "spol")
    private String spol;

    @Column(name = "datumrodjenja")
    private Date datumrodjenja;

    @Column(name = "visina")
    private BigDecimal visina;

    @Column(name = "tezina")
    private BigDecimal tezina;

    @ManyToOne
    @JoinColumn(name = "idklub", referencedColumnName = "idklub")
	private Klub klub;

    // Constructors

    public Osoba() {
    }

    public Osoba(Long idosoba, Drzava drzava, String imeosoba, String prezimeosoba, String spol, Date datumrodjenja, BigDecimal visina, BigDecimal tezina, Klub klub) {
        this.idosoba = idosoba;
        this.drzava = drzava;
        this.imeosoba = imeosoba;
        this.prezimeosoba = prezimeosoba;
        this.spol = spol;
    }
}
