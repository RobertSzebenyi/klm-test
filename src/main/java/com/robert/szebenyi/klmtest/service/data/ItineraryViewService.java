package com.robert.szebenyi.klmtest.service.data;

import com.robert.szebenyi.klmtest.data.entity.ItineraryView;
import com.robert.szebenyi.klmtest.data.repository.ItineraryViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ItineraryViewService {

    private final ItineraryViewRepository itineraryViewRepository;

    @Autowired
    public ItineraryViewService(ItineraryViewRepository itineraryViewRepository) {
        this.itineraryViewRepository = itineraryViewRepository;
    }

    public List<ItineraryView> getAllBookings() {
        return itineraryViewRepository.findAll();
    }

    public List<ItineraryView> getAllBookingsBefore(OffsetDateTime departureUtc) {
        return itineraryViewRepository.findAllBeforeDepartureUtc(departureUtc);
    }

    public List<ItineraryView> getAllBookingsWithSpecificAirportSequence(String airportSequence) {
        return itineraryViewRepository.findAllByItinerary(airportSequence);
    }

}
