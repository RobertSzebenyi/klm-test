package com.robert.szebenyi.klmtest.service.data;

import com.robert.szebenyi.klmtest.data.entity.Booking;
import com.robert.szebenyi.klmtest.data.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public Booking createBooking(String paxName) {
        var booking = new Booking();
        booking.setPaxName(paxName);
        return bookingRepository.save(booking);
    }
}
