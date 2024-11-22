package com.robert.szebenyi.klmtest.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {

    @Column(name = "booking_reference", columnDefinition = "varchar(6)", nullable = false, unique = true)
    String bookingReference;

    @Column(name = "pax_name", columnDefinition = "varchar(255)", nullable = false)
    String paxName;

    public Booking() {
        this.bookingReference = generateBookingReference();
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public String getPaxName() {
        return paxName;
    }

    public void setPaxName(String paxName) {
        this.paxName = paxName;
    }

    private static String generateBookingReference() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 6).toUpperCase();  // Example: "D39F1E"
    }
}
