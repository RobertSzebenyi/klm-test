package com.robert.szebenyi.klmtest.service;

import com.robert.szebenyi.klmtest.data.entity.ItineraryView;
import com.robert.szebenyi.klmtest.rest.dto.CreateBookingRequest;
import com.robert.szebenyi.klmtest.rest.dto.ItineraryDto;
import com.robert.szebenyi.klmtest.service.data.ItineraryViewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
@TestPropertySource(properties = {
        "spring.profiles.active=test",
        "spring.liquibase.contexts=test",
        "spring.sql.init.mode=always",
        "spring.liquibase.drop-first=true"
})
@ActiveProfiles("test")
class ControllerServiceTest {

    @Autowired
    private ControllerService controllerService;

    @Autowired
    private ItineraryViewService itineraryViewService;

    static Stream<Arguments> bookingTestData() {
        return Stream.of(
                Arguments.of("Alice", "2020-05-26T06:45:00+00:00", "LHR→AMS"),
                Arguments.of("Bruce", "2020-06-04T11:04:00+00:00", "GVA→AMS→LHR"),
                Arguments.of("Cindy", "2020-06-06T10:00:00+00:00", "AAL→AMS→LHR→JFK→SFO"),
                Arguments.of("Derek", "2020-06-12T08:09:00+00:00", "AMS→LHR"),
                Arguments.of("Erica", "2020-06-13T20:40:00+00:00", "ATL→AMS→AAL"),
                Arguments.of("Fred", "2020-06-14T09:10:00+00:00", "AMS→CDG→LHR")
        );
    }

    @ParameterizedTest
    @MethodSource("bookingTestData")
    void createBookingTest(String paxName, String departureUtc, String itinerarySequence) {
        OffsetDateTime departure = OffsetDateTime.parse(departureUtc);
        controllerService.createBooking(createBookingRequest(paxName, departure, itinerarySequence));

        itineraryViewService.getAllBookings().forEach(itineraryView -> {
            assertEquals(paxName, itineraryView.getPaxName());
            assertEquals(itinerarySequence, itineraryView.getItinerary());
            assertEquals(departure, itineraryView.getDepartureUtc());
        });
    }

    @Test
    void getAllBookingsBeforeTest() {
        bookingTestData().forEach(booking -> {
            String paxNameFromStream = booking.get()[0].toString();
            OffsetDateTime departureFromStream = OffsetDateTime.parse(booking.get()[1].toString());
            String itineraryFromStream = booking.get()[2].toString();

            controllerService.createBooking(createBookingRequest(paxNameFromStream, departureFromStream, itineraryFromStream));
        });

        List<ItineraryView> allBookingsBefore = itineraryViewService.getAllBookingsBefore(
                OffsetDateTime.parse("2020-06-12T12:00:00+00:00"));
        assertNotNull(allBookingsBefore);
        assertEquals(4, allBookingsBefore.size());
    }

    private CreateBookingRequest createBookingRequest(String paxName, OffsetDateTime departure, String itinerarySequence) {
        List<String> itineraries = Arrays.asList(itinerarySequence.split("→"));
        AtomicInteger sequence = new AtomicInteger(1);
        var itineraryDtos =
                itineraries.stream().map(itinerary -> new ItineraryDto(itinerary, departure, sequence.getAndIncrement())).toList();
        return new CreateBookingRequest(paxName, itineraryDtos);
    }
}