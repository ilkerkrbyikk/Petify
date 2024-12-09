package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.City;
import com.Ilker.Petify.entity.CorporateCustomer;
import com.Ilker.Petify.entity.Hotel;
import com.Ilker.Petify.exception.FileSizeTooBigException;
import com.Ilker.Petify.exception.HotelNotFoundException;
import com.Ilker.Petify.exception.ImageNotFoundException;
import com.Ilker.Petify.exception.InvalidFileTypeException;
import com.Ilker.Petify.repository.CityRepository;
import com.Ilker.Petify.repository.CorporateCustomerRepository;
import com.Ilker.Petify.repository.HotelRepository;
import com.Ilker.Petify.request.hotel.AddHotelRequest;
import com.Ilker.Petify.request.hotel.UpdateHotelRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {


    private static final long MAX_FILE_SIZE = 5*1024*1024;

    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;



    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public List<Hotel> getAvailableHotels() {
        return hotelRepository.findByAvailableTrue();
    }

    @Override
    public Hotel add(AddHotelRequest request) {
        Hotel hotel = new Hotel();

        City city = cityRepository.getCityById(request.getCityId());
        CorporateCustomer c = corporateCustomerRepository.getById(request.getCorporateCustomerId());
        hotel.setName(request.getName());
        hotel.setDescription(request.getDescription());
        hotel.setPhoneNumber(request.getPhoneNumber());
        hotel.setAvailable(true);
        hotel.setCity(city);
        hotel.setCorporateCustomer(c);
        hotel.setPrice(request.getPrice());

        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel update(UpdateHotelRequest request, Long id) {
        checkIsExistsHotelById(id);
        Hotel hotel = hotelRepository.findById(id).get();

        City city = cityRepository.getCityById(request.getCityId());
        CorporateCustomer c = corporateCustomerRepository.getById(request.getCorporateCustomerId());
        hotel.setName(request.getName());
        hotel.setDescription(request.getDescription());
        hotel.setPhoneNumber(request.getPhoneNumber());
        hotel.setAvailable(true);
        hotel.setCity(city);
        hotel.setCorporateCustomer(c);
        hotel.setPrice(request.getPrice());

        return hotelRepository.save(hotel);
    }

    @Override
    public void delete(Long id) {
        checkIsExistsHotelById(id);
        hotelRepository.deleteById(id);
    }

    public void checkIsExistsHotelById(Long id){
        Optional<Hotel> optional = hotelRepository.findById(id);
        if(optional.isEmpty()){
            throw new HotelNotFoundException("Hotel not found with given ID: " + id);
        }
    }

    @Override
    public Hotel uploadHotelImage(Long hotelId, MultipartFile file) throws IOException {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with given ID: " + hotelId));

        if(file.getSize() > MAX_FILE_SIZE){
            throw new FileSizeTooBigException("File size must be max 5MB.");
        }

        String contentType = file.getContentType();
        if (contentType == null ||
                (!contentType.equals("image/jpeg") &&
                        !contentType.equals("image/png"))) {
            throw new InvalidFileTypeException("Only JPEG and PNG image files can upload.");
        }

        hotel.setHotelImage(file.getBytes());
        return hotelRepository.save(hotel);
    }


    @Operation(
            summary = "Retrieve the base64 encoded image of a hotel",
            description = "This endpoint retrieves the base64 encoded image of a hotel identified by its ID."
    )
    @Override
    public String getHotelImageBase64(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with given ID: " + hotelId
                        ));

        String base64Image = hotel.getBase64Image();
        if (base64Image == null) {
            throw new ImageNotFoundException("No image found for hotel with ID: " + hotelId);
        }

        return hotel.getBase64Image();
    }


}