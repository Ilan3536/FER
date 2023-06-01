package hr.fer.zavrsni.backend.model;

import java.io.Serializable;
import java.sql.Time;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "limiti")
public class Limit implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlimit")
    private Long idlimit;


    @OneToOne
    @JoinColumn(name = "iddisciplina", referencedColumnName = "iddisciplina")
    private Disciplina disciplina;
    
    @Column(name = "vrijeme")
    private Time vrijeme;


    public Limit() {
    }

    public Limit(Long idlimit, Disciplina disciplina, Time vrijeme) {
        this.idlimit = idlimit;
        this.disciplina = disciplina;
        this.vrijeme = vrijeme;
    }

}

