package hr.fer.zavrsni.backend.model;

import lombok.Data;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "natjecanje")
@Data
public class Natjecanje implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnatjecanje")
    private Long idnatjecanje;

    @ManyToOne
    @JoinColumn(name = "idbazen", referencedColumnName = "idbazen")
	private Bazen bazen;

    @Column(name = "nazivnatjecanje")
    private String nazivnatjecanje;

    @Column(name = "vrstanatjecanje")
    private String vrstanatjecanje;


    public Natjecanje() {
    }

    public Natjecanje(Long idnatjecanje, Bazen bazen, String nazivnatjecanje, String vrstanatjecanje) {
        this.idnatjecanje = idnatjecanje;
        this.bazen = bazen;
        this.nazivnatjecanje = nazivnatjecanje;
        this.vrstanatjecanje = vrstanatjecanje;
    }

}
