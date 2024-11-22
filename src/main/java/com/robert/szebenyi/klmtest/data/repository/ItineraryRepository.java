package com.robert.szebenyi.klmtest.data.repository;

import com.robert.szebenyi.klmtest.data.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
}
