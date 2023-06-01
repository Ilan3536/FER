package hr.fer.zavrsni.backend.model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bazen")
public class Bazen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idbazen")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idbazen;
	
	@Column(name = "nazivbazen")
	private String nazivbazen;

	@Column(name = "kapacitet")
	private Integer kapacitet;
	
	@Column(name = "grad")
	private String grad;
	
	@Column(name = "adresa")
	private String adresa;
	
	@ManyToOne
    @JoinColumn(name = "iddrzava", referencedColumnName = "iddrzava")
	private Drzava drzava;
	
	public Bazen() {
	}

	public Bazen(Long idbazen, String nazivbazen, Integer kapacitet, String grad, String adresa, Drzava drzava) {
		super();
		this.idbazen = idbazen;
		this.nazivbazen = nazivbazen;
		this.kapacitet = kapacitet;
		this.grad = grad;
		this.adresa = adresa;
		this.drzava = drzava;
	}

}
