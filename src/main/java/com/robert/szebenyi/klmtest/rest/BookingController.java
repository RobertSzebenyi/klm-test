package com.robert.szebenyi.klmtest.rest;


import com.robert.szebenyi.klmtest.rest.dto.BookingDto;
import com.robert.szebenyi.klmtest.service.ControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
