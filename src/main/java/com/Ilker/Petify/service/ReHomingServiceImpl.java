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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReHomingServiceImpl implements ReHomingService {

    private static final Logger logger = LoggerFactory.getLogger(ReHomingServiceImpl.class);

    private final ReHomingRepository reHomingRepository;
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;


    @Override
    public List<ReHoming> getAll() {
        logger.info("Fetching all re-homing announcements");
        return reHomingRepository.findAll();
    }

    @Override
    public ReHoming add(CreateAnnouncementRequest request) {
        logger.info("Adding new re-homing announcement for pet ID: {}", request.getPetId());
        Pet pet  = petRepository.findPetById(request.getPetId())
                .orElseThrow(() -> {
                    logger.error("Pet not found with ID: {}", request.getPetId());
                    return new PetNotFoundException("Pet not found with ID: " + request.getPetId());
                });

        Customer customer = customerRepository
                .findById(request.getCustomerId())
                .orElseThrow(() -> {
            logger.error("Customer not found with ID: {}", request.getCustomerId());
            return new CustomerNotFoundException("Customer not found with ID: " + request.getCustomerId());
        });

        ReHoming reHoming = new ReHoming();
        reHoming.setDescription(request.getDescription());
        reHoming.setCustomer(customer);
        reHoming.setPet(pet);

        logger.info("Re-homing announcement added successfully with ID: {}", reHoming.getId());
        return reHomingRepository.save(reHoming);
    }

    @Override
    public ReHoming update(UpdateAnnouncementRequest request, Long id) {
        logger.info("Updating re-homing announcement with ID: {}", id);
        isReHomingExistsById(id);
        ReHoming reHoming = reHomingRepository.findRehomingById(id);
        reHoming.setDescription(request.getDescription());

        //! İlanın içindeki hayvanı değiştirmeyi updatelemedim. Çalışmazsa dön.

        logger.info("Re-homing announcement updated successfully with ID: {}", reHoming.getId());
        return reHomingRepository.save(reHoming);
    }

    @Override
    public void delete(Long id) {
        logger.info("Attempting to delete re-homing announcement with ID: {}", id);
        isReHomingExistsById(id);
        reHomingRepository.deleteById(id);
        logger.info("Re-homing announcement with ID: {} deleted successfully", id);
    }

    public void isReHomingExistsById(Long id){
        Optional<ReHoming> optional = reHomingRepository.findPetById(id);
        if(optional.isEmpty()){
            logger.error("Announcement not found with ID: {}", id);
            throw new AnnouncementNotFoundException("Announcement not found with ID: " + id);
        }
    }
}