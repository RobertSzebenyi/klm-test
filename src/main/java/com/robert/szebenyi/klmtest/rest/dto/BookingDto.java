package com.robert.szebenyi.klmtest.rest.dto;

import java.time.OffsetDateTime;

public record BookingDto(
        String paxName,
        OffsetDateTime departureUtc,
        String itinerary
) {
}
