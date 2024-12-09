package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Hotel;
import com.Ilker.Petify.request.hotel.AddHotelRequest;
import com.Ilker.Petify.request.hotel.UpdateHotelRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HotelService {
    List<Hotel> getAvailableHotels();
    Hotel add(AddHotelRequest request);
    Hotel update(UpdateHotelRequest request, Long id);
    void delete(Long id);
    List<Hotel> getAll();
    Hotel uploadHotelImage(Long hotelId, MultipartFile file) throws IOException;
    String getHotelImageBase64(Long hotelId);


}
