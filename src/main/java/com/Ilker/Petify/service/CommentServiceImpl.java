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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;
    private final PetBarberRepository petBarberRepository;
    private final PetSitterRepository petSitterRepository;
    private final HotelRepository hotelRepository;

    @Override
    public Comment addHotelComment(AddCommentRequest request, Long id) {
        logger.info("Adding comment for hotel with ID: {}", id);

        Comment comment = new Comment();
        comment.setCreatedAt(LocalDateTime.now());
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new HotelNotFoundException("Hotel not found given ID: " + request.getHotelId()));
        comment.setRating(request.getRating());
        comment.setCommentText(request.getCommentText());

        logger.info("Comment added successfully for hotel ID: {}", id);
        return commentRepository.save(comment);
    }

    @Override
    public Comment addPetSitterComment(AddCommentRequest request,Long id) {
        logger.info("Adding comment for pet sitter with ID: {}", id);

        Comment comment = new Comment();
        comment.setCreatedAt(LocalDateTime.now());
        PetSitter petSitter = petSitterRepository.findById(request.getHotelId())
                .orElseThrow(()-> new PetSitterNotFoundException("Pet Sitter not found given ID: " + request.getPetSitterId()));
        comment.setRating(request.getRating());
        comment.setCommentText(request.getCommentText());

        logger.info("Comment added successfully for pet sitter ID: {}", id);
        return commentRepository.save(comment);
    }

    @Override
    public Comment addPetBarberComment(AddCommentRequest request,Long id) {
        logger.info("Adding comment for pet barber with ID: {}", id);
        Comment comment = new Comment();
        comment.setCreatedAt(LocalDateTime.now());
        PetBarber petBarber = petBarberRepository.findById(request.getHotelId())
                .orElseThrow(()-> new PetBarberNotFoundException("Pet barber not found given ID: " + request.getPetBarberId()));
        comment.setRating(request.getRating());
        comment.setCommentText(request.getCommentText());

        logger.info("Comment added successfully for pet barber ID: {}", id);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(UpdateCommentRequest request, Long id) {
        logger.info("Updating comment with ID: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new CommentNotExistsException("Comment not exists."));
        comment.setCommentText(request.getCommentText());
        comment.setRating(request.getRating());

        logger.info("Comment updated successfully with ID: {}", id);
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        logger.info("Attempting to delete comment with ID: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new CommentNotExistsException("Comment not exits."));
        commentRepository.deleteById(id);
        logger.info("Comment with ID: {} deleted successfully", id);

    }

    @Override
    public double calculatePetBarberAverageRating(PetBarber petBarber) {
        logger.info("Calculating average rating for pet barber with ID: {}", petBarber.getId());
        List<Comment> comments = commentRepository.findByPetBarber(petBarber);
        if (comments.isEmpty()) {
            logger.warn("No comments found for pet barber with ID: {}", petBarber.getId());
            return 0.0;
        }
        double totalRating = 0;
        for (Comment comment : comments) {
            totalRating += comment.getRating();
        }
        double averageRating = totalRating / comments.size();
        logger.info("Average rating for pet barber with ID: {} is {}", petBarber.getId(), averageRating);
        return averageRating;
    }

    @Override
    public double calculatePetSitterAverageRating(PetSitter petSitter) {
        logger.info("Calculating average rating for pet sitter with ID: {}", petSitter.getId());
        List<Comment> comments = commentRepository.findByPetSitter(petSitter);
        if (comments.isEmpty()) {
            logger.warn("No comments found for pet sitter with ID: {}", petSitter.getId());
            return 0.0;
        }
        double totalRating = 0;
        for (Comment comment : comments) {
            totalRating += comment.getRating();
        }
        double averageRating = totalRating / comments.size();
        logger.info("Average rating for pet sitter with ID: {} is {}", petSitter.getId(), averageRating);
        return averageRating;
    }

    @Override
    public double calculateHotelAverageRating(Hotel hotel) {
        logger.info("Calculating average rating for hotel with ID: {}", hotel.getId());
        List<Comment> comments = commentRepository.findByHotel(hotel);
        if (comments.isEmpty()) {
            logger.warn("No comments found for hotel with ID: {}", hotel.getId());
            return 0.0;
        }
        double totalRating = 0;
        for (Comment comment : comments) {
            totalRating += comment.getRating();
        }
        double averageRating = totalRating / comments.size();
        logger.info("Average rating for hotel with ID: {} is {}", hotel.getId(), averageRating);
        return averageRating;
    }
}
