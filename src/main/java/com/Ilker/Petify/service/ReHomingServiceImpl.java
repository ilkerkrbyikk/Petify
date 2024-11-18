package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Customer;
import com.Ilker.Petify.entity.Pet;
import com.Ilker.Petify.entity.ReHoming;
import com.Ilker.Petify.exception.AnnouncementNotFoundException;
import com.Ilker.Petify.exception.CustomerNotFoundException;
import com.Ilker.Petify.exception.PetNotFoundException;
import com.Ilker.Petify.repository.CustomerRepository;
import com.Ilker.Petify.repository.PetRepository;
import com.Ilker.Petify.repository.ReHomingRepository;
import com.Ilker.Petify.request.announcement.CreateAnnouncementRequest;
import com.Ilker.Petify.request.announcement.UpdateAnnouncementRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReHomingServiceImpl implements ReHomingService {

    private final ReHomingRepository reHomingRepository;
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;


    @Override
    public List<ReHoming> getAll() {
        return reHomingRepository.findAll();
    }

    @Override
    public ReHoming add(CreateAnnouncementRequest request) {
        Pet pet  = petRepository.findPetById(request.getPetId())
                .orElseThrow(()-> new PetNotFoundException("Pet not found with ID: " + request.getPetId()));
        Customer customer = customerRepository
                .findById(request.getCustomerId())
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found with ID: " + request.getCustomerId()));

        ReHoming reHoming = new ReHoming();
        reHoming.setDescription(request.getDescription());
        reHoming.setCustomer(customer);
        reHoming.setPet(pet);

        return reHomingRepository.save(reHoming);
    }

    @Override
    public ReHoming update(UpdateAnnouncementRequest request, Long id) {
        isReHomingExistsById(id);
        ReHoming reHoming = reHomingRepository.findRehomingById(id);
        reHoming.setDescription(request.getDescription());

        //! İlanın içindeki hayvanı değiştirmeyi updatelemedim. Çalışmazsa dön.

        return reHomingRepository.save(reHoming);
    }

    @Override
    public void delete(Long id) {
        isReHomingExistsById(id);
        reHomingRepository.deleteById(id);
    }

    public void isReHomingExistsById(Long id){
        Optional<ReHoming> optional = reHomingRepository.findPetById(id);
        if(optional.isEmpty()){
            throw new AnnouncementNotFoundException("Announcement not found with ID: " + id);
        }
    }
}