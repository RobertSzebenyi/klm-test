package com.robert.szebenyi.klmtest.service;

import com.robert.szebenyi.klmtest.rest.dto.CreateBookingRequest;
import com.robert.szebenyi.klmtest.rest.dto.ItineraryDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Transactional
@SpringBootTest
class ControllerServiceTest {

    @Autowired
    private ControllerService controllerService;

    @ParameterizedTest
    @CsvSource({
            "Alice,2020-05-26T06:45:00+00:00,LHR→AMS",
            "Bruce,2020-06-04T11:04:00+00:00,GVA→AMS→LHR",
            "Cindy,2020-06-06T10:00:00+00:00,AAL→AMS→LHR→JFK→SFO",
            "Derek,2020-06-12T08:09:00+00:00,AMS→LHR",
            "Erica,2020-06-13T20:40:00+00:00,ATL→AMS→AAL",
            "Fred,2020-06-14T09:10:00+00:00,AMS→CDG→LHR"
    })
    void createBookingTest(String paxName, String departureUtc, String itinerarySequence) {

        OffsetDateTime departure = OffsetDateTime.parse(departureUtc);
        List<String> itineraries = Arrays.asList(itinerarySequence.split("→"));

        AtomicInteger sequence = new AtomicInteger(1);
        var itineraryDtos =
                itineraries.stream().map(itinerary -> new ItineraryDto(itinerary, departure, sequence.getAndIncrement())).toList();
        CreateBookingRequest bookingRequest = new CreateBookingRequest(paxName, itineraryDtos);
        controllerService.createBooking(bookingRequest);
    }
}