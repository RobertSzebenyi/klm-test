package com.robert.szebenyi.klmtest.rest;


import com.robert.szebenyi.klmtest.rest.dto.BookingDto;
import com.robert.szebenyi.klmtest.rest.dto.CreateBookingRequest;
import com.robert.szebenyi.klmtest.service.ControllerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    final ControllerService controllerService;

    @Autowired
    public BookingController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping
    public List<BookingDto> getAllBookings() {
        return controllerService.getAllBookings();
    }

    @GetMapping(value = "/before")
    public List<BookingDto> getAllBookingsBefore(@RequestParam(name = "departure") String departureDateTime) {
        return controllerService.getAllBookingsBefore(OffsetDateTime.parse(departureDateTime));
    }

    @GetMapping(value = "/itinerary")
    public List<BookingDto> getAllBookingsWithSpecificAirportSequence(@RequestParam(name = "sequence") String itinerarySequence) {
        return controllerService.getAllBookingsWithSpecificAirportSequence(itinerarySequence);
    }

    @PostMapping()
    public void getAllBookingsWithSpecificAirportSequence(@Valid @RequestBody CreateBookingRequest createBookingRequest) {
        controllerService.createBooking(createBookingRequest);
    }
}
