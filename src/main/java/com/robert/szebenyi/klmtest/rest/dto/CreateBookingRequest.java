package com.robert.szebenyi.klmtest.rest.dto;

import java.util.List;

public record CreateBookingRequest(
        String paxName,
        List<ItineraryDto> itineraries
) {
}
