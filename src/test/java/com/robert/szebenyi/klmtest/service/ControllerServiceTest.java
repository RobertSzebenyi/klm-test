package com.robert.szebenyi.klmtest.service;

import com.robert.szebenyi.klmtest.rest.dto.BookingDto;
import com.robert.szebenyi.klmtest.rest.dto.CreateBookingRequest;
import com.robert.szebenyi.klmtest.rest.dto.ItineraryDto;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        controllerService.getAllBookings().forEach(itineraryView -> {
            assertEquals(paxName, itineraryView.paxName());
            assertEquals(itinerarySequence, itineraryView.itinerary());
            assertEquals(departure, itineraryView.departureUtc());
        });
    }

    @Test
    void getAllBookingsBeforeTest() {
        createDefaultBookings();

        List<BookingDto> allBookingsBefore = controllerService.getAllBookingsBefore(
                OffsetDateTime.parse("2020-06-12T12:00:00+00:00"));
        assertNotNull(allBookingsBefore);
        assertEquals(4, allBookingsBefore.size());
    }

    @Test
    void getAllBookingsWithSpecificAirportSequenceTest() {
        createDefaultBookings();

        List<BookingDto> foundBookings = controllerService.getAllBookingsWithSpecificAirportSequence("AMS→LHR");
        assertNotNull(foundBookings);
        assertEquals(3, foundBookings.size());

        foundBookings.forEach(booking -> assertTrue(booking.itinerary().contains("AMS→LHR")));
    }

    private void createDefaultBookings() {
        bookingTestData().forEach(booking -> {
            String paxNameFromStream = booking.get()[0].toString();
            OffsetDateTime departureFromStream = OffsetDateTime.parse(booking.get()[1].toString());
            String itineraryFromStream = booking.get()[2].toString();

            controllerService.createBooking(createBookingRequest(paxNameFromStream, departureFromStream, itineraryFromStream));
        });
    }

    private CreateBookingRequest createBookingRequest(String paxName, OffsetDateTime departure, String itinerarySequence) {
        List<String> itineraries = Arrays.asList(itinerarySequence.split("→"));
        AtomicInteger sequence = new AtomicInteger(1);
        var itineraryDtos =
                itineraries.stream().map(itinerary -> new ItineraryDto(itinerary, departure, sequence.getAndIncrement())).toList();
        return new CreateBookingRequest(paxName, itineraryDtos);
    }
}