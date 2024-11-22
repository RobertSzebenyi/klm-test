package com.robert.szebenyi.klmtest.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "itinerary_view")
public class ItineraryView {

    @Id
    @Column(name = "booking_reference")
    private String bookingReference;

    @Column(name = "pax_name")
    private String paxName;

    @Column(name = "departure_utc")
    private OffsetDateTime departureUtc;

    @Column(name = "itinerary")
    private String itinerary;

    public ItineraryView(String bookingReference, String paxName, OffsetDateTime departureUtc, String itinerary) {
        this.bookingReference = bookingReference;
        this.paxName = paxName;
        this.departureUtc = departureUtc;
        this.itinerary = itinerary;
    }

    public ItineraryView() {
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public String getPaxName() {
        return paxName;
    }

    public OffsetDateTime getDepartureUtc() {
        return departureUtc.withOffsetSameInstant(ZoneOffset.UTC);
    }

    public String getItinerary() {
        return itinerary;
    }
}
