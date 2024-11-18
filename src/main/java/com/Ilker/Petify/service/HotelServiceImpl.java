package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.City;
import com.Ilker.Petify.entity.CorporateCustomer;
import com.Ilker.Petify.entity.Hotel;
import com.Ilker.Petify.exception.HotelNotFoundException;
import com.Ilker.Petify.repository.CityRepository;
import com.Ilker.Petify.repository.CorporateCustomerRepository;
import com.Ilker.Petify.repository.HotelRepository;
import com.Ilker.Petify.request.hotel.AddHotelRequest;
import com.Ilker.Petify.request.hotel.UpdateHotelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

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


}