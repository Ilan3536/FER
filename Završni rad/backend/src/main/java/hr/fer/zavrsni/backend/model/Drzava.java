package hr.fer.zavrsni.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "drzava")
public class Drzava {

	@Id
	@Column(name = "iddrzava")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iddrzava;

	@Column(name = "nazivdrzava")
	private String nazivdrzava;
	
	public Drzava() {
	}

	public Drzava(Long iddrzava, String nazivdrzava) {
		super();
		this.iddrzava = iddrzava;
		this.nazivdrzava = nazivdrzava;
	}

 
}
