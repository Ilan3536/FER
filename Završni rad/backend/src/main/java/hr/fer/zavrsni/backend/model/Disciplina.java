package hr.fer.zavrsni.backend.model;

import lombok.Data;

import java.io.Serializable;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "disciplina")
public class Disciplina implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddisciplina")
    private Long iddisciplina;

    @Column(name = "nazivdisciplina")
    private String nazivdisciplina;

    @Column(name = "spol")
    private String spol;

    public Disciplina() {
	}

	public Disciplina(Long iddisciplina, String nazivdisciplina, String spol) {
		super();
		this.iddisciplina = iddisciplina;
		this.nazivdisciplina = nazivdisciplina;
		this.spol = spol;
	}

    
    
}
