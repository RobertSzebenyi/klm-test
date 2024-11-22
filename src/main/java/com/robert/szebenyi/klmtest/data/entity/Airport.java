package com.robert.szebenyi.klmtest.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "airports")
public class Airport extends BaseEntity {

    @Column(name = "iata_code", columnDefinition = "varchar(3)", nullable = false, unique = true)
    String iata;

    @Column(name = "name", columnDefinition = "varchar(255)", nullable = false)
    String name;

    // Needed for JPA TODO check later
    public Airport() {
    }

    public String getIata() {
        return iata;
    }

    public String getName() {
        return name;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public void setName(String name) {
        this.name = name;
    }
}
