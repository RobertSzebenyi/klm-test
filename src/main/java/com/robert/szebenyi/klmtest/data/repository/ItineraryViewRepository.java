package com.robert.szebenyi.klmtest.data.repository;

import com.robert.szebenyi.klmtest.data.entity.ItineraryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryViewRepository extends JpaRepository<ItineraryView, String> {
}
