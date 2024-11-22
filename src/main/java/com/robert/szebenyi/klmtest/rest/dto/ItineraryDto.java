package com.robert.szebenyi.klmtest.rest.dto;

import java.time.OffsetDateTime;

public record ItineraryDto(
        String iataCode,
        OffsetDateTime departureUtc,
        Integer sequence
) {
}
