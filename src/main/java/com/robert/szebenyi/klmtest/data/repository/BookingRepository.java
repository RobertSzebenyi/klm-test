package com.robert.szebenyi.klmtest.data.repository;

import com.robert.szebenyi.klmtest.data.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
