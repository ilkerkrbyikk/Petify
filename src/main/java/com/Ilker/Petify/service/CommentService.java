package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Comment;
import com.Ilker.Petify.entity.Hotel;
import com.Ilker.Petify.entity.PetBarber;
import com.Ilker.Petify.entity.PetSitter;
import com.Ilker.Petify.request.comment.AddCommentRequest;
import com.Ilker.Petify.response.UpdateCommentRequest;

public interface CommentService {
    Comment addHotelComment(AddCommentRequest request, Long id);
    Comment addPetSitterComment(AddCommentRequest request, Long id);
    Comment addPetBarberComment(AddCommentRequest request,Long id);

    Comment update(UpdateCommentRequest request, Long id);
    void delete(Long id);

    double calculatePetBarberAverageRating(PetBarber petBarber);
    double calculatePetSitterAverageRating(PetSitter petSitter);
    double calculateHotelAverageRating(Hotel hotel);
}
