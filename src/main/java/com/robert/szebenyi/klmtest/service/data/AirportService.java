package com.robert.szebenyi.klmtest.service.data;

import com.robert.szebenyi.klmtest.data.entity.Airport;
import com.robert.szebenyi.klmtest.data.repository.AirportRepository;
import com.robert.szebenyi.klmtest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Airport findByIata(String iata) {
        return airportRepository.findByIata(iata).orElseThrow(() -> new NotFoundException("Airport with iata " + iata + " not found"));
    }
}
