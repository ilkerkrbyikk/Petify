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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);
    private static final long MAX_FILE_SIZE = 5*1024*1024;

    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;



    @Override
    public List<Hotel> getAll() {
        logger.info("Fetching all hotels");
        return hotelRepository.findAll();
    }

    @Override
    public List<Hotel> getAvailableHotels() {
        logger.info("Fetching available hotels");
        return hotelRepository.findByAvailableTrue();
    }

    @Override
    public Hotel add(AddHotelRequest request) {
        logger.info("Adding new hotel with name: {}", request.getName());
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

        logger.info("Hotel added successfully with ID: {}", hotel.getId());
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel update(UpdateHotelRequest request, Long id) {
        logger.info("Updating hotel with ID: {}", id);
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

        logger.info("Hotel updated successfully with ID: {}", updatedHotel.getId());
        return hotelRepository.save(hotel);
    }

    @Override
    public void delete(Long id) {
        logger.info("Attempting to delete hotel with ID: {}", id);
        checkIsExistsHotelById(id);
        hotelRepository.deleteById(id);
        logger.info("Hotel with ID: {} deleted successfully", id);
    }

    public void checkIsExistsHotelById(Long id){
        Optional<Hotel> optional = hotelRepository.findById(id);
        if(optional.isEmpty()){
            logger.error("Hotel not found with given ID: {}", id);
            throw new HotelNotFoundException("Hotel not found with given ID: " + id);
        }
    }

    @Override
    public Hotel uploadHotelImage(Long hotelId, MultipartFile file) throws IOException {
        logger.info("Uploading image for hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with given ID: " + hotelId));

        if(file.getSize() > MAX_FILE_SIZE){
            logger.error("File size too big for hotel ID: {}. Size: {}", hotelId, file.getSize());
            throw new FileSizeTooBigException("File size must be max 5MB.");
        }

        String contentType = file.getContentType();
        if (contentType == null ||
                (!contentType.equals("image/jpeg") &&
                        !contentType.equals("image/png"))) {
            logger.error("Invalid file type for hotel ID: {}. Type: {}", hotelId, contentType);
            throw new InvalidFileTypeException("Only JPEG and PNG image files can upload.");
        }

        hotel.setHotelImage(file.getBytes());
        logger.info("Image uploaded successfully for hotel ID: {}", hotelId);
        return hotelRepository.save(hotel);
    }


    @Operation(
            summary = "Retrieve the base64 encoded image of a hotel",
            description = "This endpoint retrieves the base64 encoded image of a hotel identified by its ID."
    )
    @Override
    public String getHotelImageBase64(Long hotelId) {
        logger.info("Retrieving base64 image for hotel ID: {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with given ID: " + hotelId
                        ));

        String base64Image = hotel.getBase64Image();
        if (base64Image == null) {
            logger.error("No image found for hotel ID: {}", hotelId);
            throw new ImageNotFoundException("No image found for hotel with ID: " + hotelId);
        }

        logger.info("Base64 image retrieved successfully for hotel ID: {}", hotelId);
        return hotel.getBase64Image();
    }


}