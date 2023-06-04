package hr.fer.zavrsni.backend.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

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
    
    @Column(name = "datumod")
    private Date datumod;

    @Column(name = "datumdo")
    private Date datumdo;
    

    public Natjecanje() {
    }

	public Natjecanje(Long idnatjecanje, Bazen bazen, String nazivnatjecanje, String vrstanatjecanje, Date datumod,
			Date datumdo) {
		super();
		this.idnatjecanje = idnatjecanje;
		this.bazen = bazen;
		this.nazivnatjecanje = nazivnatjecanje;
		this.vrstanatjecanje = vrstanatjecanje;
		this.datumod = datumod;
		this.datumdo = datumdo;
	}

}
