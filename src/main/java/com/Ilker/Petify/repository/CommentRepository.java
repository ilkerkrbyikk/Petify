package com.Ilker.Petify.repository;

import com.Ilker.Petify.entity.Comment;
import com.Ilker.Petify.entity.Hotel;
import com.Ilker.Petify.entity.PetBarber;
import com.Ilker.Petify.entity.PetSitter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPetBarber(PetBarber petBarber);
    List<Comment> findByHotel(Hotel hotel);
    List<Comment> findByPetSitter(PetSitter petSitter);

}
