package hr.fer.zavrsni.backend.model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "klub")
public class Klub implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "idklub")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idklub;

    @Column(name = "nazivklub")
    private String nazivklub;

    @Column(name = "godinaosnivanja")
    private String godinaosnivanja;

    @ManyToOne
    @JoinColumn(name = "idbazen", referencedColumnName = "idbazen")
	private Bazen bazen;
    
    public Klub() {
    }

    public Klub(Long idklub, String nazivklub, String godinaosnivanja, Bazen bazen) {
        this.idklub = idklub;
        this.nazivklub = nazivklub;
        this.godinaosnivanja = godinaosnivanja;
        this.bazen = bazen;
    }

}

