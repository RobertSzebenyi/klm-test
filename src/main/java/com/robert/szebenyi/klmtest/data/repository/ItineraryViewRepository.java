package com.robert.szebenyi.klmtest.data.repository;

import com.robert.szebenyi.klmtest.data.entity.ItineraryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface ItineraryViewRepository extends JpaRepository<ItineraryView, String> {

    @Query("SELECT booking FROM ItineraryView booking WHERE booking.departureUtc < :departureUtc")
    List<ItineraryView> findAllBeforeDepartureUtc(@Param("departureUtc") OffsetDateTime departureUtc);

    @Query("SELECT iv FROM ItineraryView iv WHERE iv.itinerary LIKE %:airportSequence%")
    List<ItineraryView> findAllByItinerary(@Param("airportSequence") String airportSequence);
}
