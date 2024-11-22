package com.robert.szebenyi.klmtest.data.repository;

import com.robert.szebenyi.klmtest.data.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

    Optional<Airport> findByIata(String iata);
}
