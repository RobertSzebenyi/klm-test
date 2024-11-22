package com.robert.szebenyi.klmtest.data.mapper;

import com.robert.szebenyi.klmtest.data.entity.ItineraryView;
import com.robert.szebenyi.klmtest.rest.dto.BookingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDto toBookingDto(ItineraryView itineraryView);

}
