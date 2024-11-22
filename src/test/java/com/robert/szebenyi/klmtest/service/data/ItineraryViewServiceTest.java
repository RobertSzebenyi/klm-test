package com.robert.szebenyi.klmtest.service.data;

import com.robert.szebenyi.klmtest.data.entity.ItineraryView;
import com.robert.szebenyi.klmtest.data.repository.ItineraryViewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItineraryViewServiceTest {

    @Mock
    private ItineraryViewRepository itineraryViewRepository;

    @InjectMocks
    private ItineraryViewService itineraryViewService;

    private List<ItineraryView> expectedBookings;

    @BeforeEach
    void setUp() {
        expectedBookings = Arrays.asList(
                new ItineraryView("ABC123", "Alice",
                        OffsetDateTime.parse("2020-05-26T06:45:00+00:00"), "LHR→AMS"),
                new ItineraryView("DEF456", "Bruce",
                        OffsetDateTime.parse("2020-06-04T11:04:00+00:00"), "GVA→AMS→LHR"),
                new ItineraryView("GHI789", "Cindy",
                        OffsetDateTime.parse("2020-06-06T10:00:00+00:00"), "AAL→AMS→LHR→JFK→SFO"),
                new ItineraryView("JKL012", "Derek",
                        OffsetDateTime.parse("2020-06-12T08:09:00+00:00"), "AMS→LHR"),
                new ItineraryView("MNO345", "Erica",
                        OffsetDateTime.parse("2020-06-13T20:40:00+00:00"), "ATL→AMS→AAL"),
                new ItineraryView("PQR678", "Fred",
                        OffsetDateTime.parse("2020-06-14T09:10:00+00:00"), "AMS→CDG→LHR")
        );
    }

    @Test
    void getAllBookingsTest() {
        when(itineraryViewRepository.findAll()).thenReturn(expectedBookings);

        var allBookings = itineraryViewService.getAllBookings();
        assertEquals(expectedBookings.size(), allBookings.size());

        assertNotNull(allBookings);

        for (int i = 0; i < expectedBookings.size(); i++) {
            assertEquals(expectedBookings.get(i).getBookingReference(), allBookings.get(i).getBookingReference());
            assertEquals(expectedBookings.get(i).getPaxName(), allBookings.get(i).getPaxName());
            assertEquals(expectedBookings.get(i).getItinerary(), allBookings.get(i).getItinerary());
            assertEquals(expectedBookings.get(i).getDepartureUtc(), allBookings.get(i).getDepartureUtc());
        }
    }

}