package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Comment;
import com.Ilker.Petify.entity.Hotel;
import com.Ilker.Petify.entity.PetBarber;
import com.Ilker.Petify.entity.PetSitter;
import com.Ilker.Petify.exception.CommentNotExistsException;
import com.Ilker.Petify.exception.HotelNotFoundException;
import com.Ilker.Petify.exception.PetBarberNotFoundException;
import com.Ilker.Petify.exception.PetSitterNotFoundException;
import com.Ilker.Petify.repository.CommentRepository;
import com.Ilker.Petify.repository.HotelRepository;
import com.Ilker.Petify.repository.PetBarberRepository;
import com.Ilker.Petify.repository.PetSitterRepository;
import com.Ilker.Petify.request.comment.AddCommentRequest;
import com.Ilker.Petify.response.UpdateCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PetBarberRepository petBarberRepository;
    private final PetSitterRepository petSitterRepository;
    private final HotelRepository hotelRepository;

    @Override
    public Comment addHotelComment(AddCommentRequest request, Long id) {

        Comment comment = new Comment();
        comment.setCreatedAt(LocalDateTime.now());
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new HotelNotFoundException("Hotel not found given ID: " + request.getHotelId()));
        comment.setRating(request.getRating());
        comment.setCommentText(request.getCommentText());

        return commentRepository.save(comment);
    }

    @Override
    public Comment addPetSitterComment(AddCommentRequest request,Long id) {
        Comment comment = new Comment();
        comment.setCreatedAt(LocalDateTime.now());
        PetSitter petSitter = petSitterRepository.findById(request.getHotelId())
                .orElseThrow(()-> new PetSitterNotFoundException("Pet Sitter not found given ID: " + request.getPetSitterId()));
        comment.setRating(request.getRating());
        comment.setCommentText(request.getCommentText());

        return commentRepository.save(comment);
    }

    @Override
    public Comment addPetBarberComment(AddCommentRequest request,Long id) {
        Comment comment = new Comment();
        comment.setCreatedAt(LocalDateTime.now());
        PetBarber petBarber = petBarberRepository.findById(request.getHotelId())
                .orElseThrow(()-> new PetBarberNotFoundException("Pet barber not found given ID: " + request.getPetBarberId()));
        comment.setRating(request.getRating());
        comment.setCommentText(request.getCommentText());

        return commentRepository.save(comment);
    }

    @Override
    public Comment update(UpdateCommentRequest request, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new CommentNotExistsException("Comment not exists."));
        comment.setCommentText(request.getCommentText());
        comment.setRating(request.getRating());

        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new CommentNotExistsException("Comment not exits."));
        commentRepository.deleteById(id);

    }

    @Override
    public double calculatePetBarberAverageRating(PetBarber petBarber) {
        List<Comment> comments = commentRepository.findByPetBarber(petBarber);
        if (comments.isEmpty()) {
            return 0.0;
        }
        double totalRating = 0;
        for (Comment comment : comments) {
            totalRating += comment.getRating();
        }
        return totalRating / comments.size();
    }

    @Override
    public double calculatePetSitterAverageRating(PetSitter petSitter) {
        List<Comment> comments = commentRepository.findByPetSitter(petSitter);
        if (comments.isEmpty()) {
            return 0.0;
        }
        double totalRating = 0;
        for (Comment comment : comments) {
            totalRating += comment.getRating();
        }
        return totalRating / comments.size();
    }

    @Override
    public double calculateHotelAverageRating(Hotel hotel) {
        List<Comment> comments = commentRepository.findByHotel(hotel);
        if (comments.isEmpty()) {
            return 0.0;
        }
        double totalRating = 0;
        for (Comment comment : comments) {
            totalRating += comment.getRating();
        }
        return totalRating / comments.size();
    }
}
