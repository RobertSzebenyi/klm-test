package com.robert.szebenyi.klmtest.service;

import com.robert.szebenyi.klmtest.data.entity.Booking;
import com.robert.szebenyi.klmtest.data.entity.Itinerary;
import com.robert.szebenyi.klmtest.data.mapper.BookingMapper;
import com.robert.szebenyi.klmtest.exception.ValidationException;
import com.robert.szebenyi.klmtest.rest.dto.BookingDto;
import com.robert.szebenyi.klmtest.rest.dto.CreateBookingRequest;
import com.robert.szebenyi.klmtest.rest.dto.ItineraryDto;
import com.robert.szebenyi.klmtest.service.data.AirportService;
import com.robert.szebenyi.klmtest.service.data.BookingService;
import com.robert.szebenyi.klmtest.service.data.ItineraryService;
import com.robert.szebenyi.klmtest.service.data.ItineraryViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ControllerService {

    private final BookingService bookingService;
    private final ItineraryService itineraryService;
    private final AirportService airportService;
    private final ItineraryViewService itineraryViewService;

    private final BookingMapper mapper;

    @Autowired
    public ControllerService(BookingService bookingService, ItineraryService itineraryService,
                             AirportService airportService, ItineraryViewService itineraryViewService, BookingMapper mapper) {
        this.bookingService = bookingService;
        this.itineraryService = itineraryService;
        this.airportService = airportService;
        this.itineraryViewService = itineraryViewService;
        this.mapper = mapper;
    }

    @Transactional
    public void createBooking(CreateBookingRequest bookingRequest) {
        validateCreateBookingRequest(bookingRequest);
        var booking = bookingService.createBooking(bookingRequest.paxName());
        createItineraries(bookingRequest.itineraries(), booking);
    }

    public List<BookingDto> getAllBookings() {
        return itineraryViewService.getAllBookings().stream().map(mapper::toBookingDto).toList();
    }

    public List<BookingDto> getAllBookingsBefore(OffsetDateTime departureUtc) {
        return itineraryViewService.getAllBookingsBefore(departureUtc).stream().map(mapper::toBookingDto).toList();
    }

    public List<BookingDto> getAllBookingsWithSpecificAirportSequence(String airportSequence) {
        return itineraryViewService.getAllBookingsWithSpecificAirportSequence(airportSequence).stream().map(mapper::toBookingDto).toList();
    }

    private void createItineraries(List<ItineraryDto> itineraryDto, Booking booking) {
        itineraryService.createItineraries(itineraryDto.stream().map(itinerary -> new Itinerary(booking,
                airportService.findByIata(itinerary.iataCode()),
                itinerary.departureUtc(),
                itinerary.sequence())).toList());
    }

    void validateCreateBookingRequest(CreateBookingRequest bookingRequest) {
        if (bookingRequest.itineraries() == null || bookingRequest.itineraries().size() < 2) {
            throw new ValidationException("Itinerary sequence is less then 2");
        }
    }
}
