package hr.fer.zavrsni.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "nastupaza")
public class NastupaZa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idosoba")
    private Long idosoba;

    @Column(name = "idklub")
    private Long idklub;

    @Column(name = "odN")
    private Date odN;

    @Column(name = "doN")
    private Date doN;


    public NastupaZa() {
    }

    public NastupaZa(Long idosoba, Long idklub, Date odN, Date doN) {
        this.idosoba = idosoba;
        this.idklub = idklub;
        this.odN = odN;
        this.doN = doN;
    }
    
}

