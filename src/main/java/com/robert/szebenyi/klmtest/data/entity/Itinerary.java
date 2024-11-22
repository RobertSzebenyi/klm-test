package com.robert.szebenyi.klmtest.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;


@Entity
@Table(name = "itineraries")
public class Itinerary extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itinerary_id", referencedColumnName = "id", nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id", referencedColumnName = "id", nullable = false)
    private Airport airport;

    @Column(name = "departure", nullable = false)
    private OffsetDateTime departure;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    public Itinerary() {
    }

    public Itinerary(Booking booking, Airport airport, OffsetDateTime departure, Integer sequence) {
        this.booking = booking;
        this.airport = airport;
        this.departure = departure;
        this.sequence = sequence;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public OffsetDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(OffsetDateTime departure) {
        this.departure = departure;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
