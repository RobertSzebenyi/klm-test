package com.robert.szebenyi.klmtest.service;

import com.robert.szebenyi.klmtest.data.entity.Booking;
import com.robert.szebenyi.klmtest.data.entity.Itinerary;
import com.robert.szebenyi.klmtest.rest.dto.CreateBookingRequest;
import com.robert.szebenyi.klmtest.rest.dto.ItineraryDto;
import com.robert.szebenyi.klmtest.service.data.AirportService;
import com.robert.szebenyi.klmtest.service.data.BookingService;
import com.robert.szebenyi.klmtest.service.data.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ControllerService {

    private final BookingService bookingService;
    private final ItineraryService itineraryService;
    private final AirportService airportService;

    @Autowired
    public ControllerService(BookingService bookingService, ItineraryService itineraryService,
                             AirportService airportService) {
        this.bookingService = bookingService;
        this.itineraryService = itineraryService;
        this.airportService = airportService;
    }

    @Transactional
    public void createBooking(CreateBookingRequest bookingRequest) {
        var booking = bookingService.createBooking(bookingRequest.paxName());
        createItineraries(bookingRequest.itineraries(), booking);
    }

    private void createItineraries(List<ItineraryDto> itineraryDto, Booking booking) {
        itineraryService.createItineraries(itineraryDto.stream().map(itinerary -> new Itinerary(booking,
                airportService.findByIata(itinerary.iataCode()),
                itinerary.departureUtc(),
                itinerary.sequence())).toList());
    }
}
