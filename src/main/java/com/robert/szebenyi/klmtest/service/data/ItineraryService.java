package com.robert.szebenyi.klmtest.service.data;

import com.robert.szebenyi.klmtest.data.entity.Itinerary;
import com.robert.szebenyi.klmtest.data.repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;

    @Autowired
    public ItineraryService(ItineraryRepository itineraryRepository) {
        this.itineraryRepository = itineraryRepository;
    }

    @Transactional
    public List<Itinerary> createItineraries(List<Itinerary> itineraries) {
        return itineraryRepository.saveAll(itineraries);
    }
}
