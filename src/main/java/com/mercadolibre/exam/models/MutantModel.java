package com.mercadolibre.exam.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Mutant")
public class MutantModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    private String dna;
    private Date date;
    private Boolean isMutant;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public String getDna() {
        return dna;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setIsMutant(Boolean isMutant) {
        this.isMutant = isMutant;
    }

    public Boolean getIsMutant() {
        return isMutant;
    }
}