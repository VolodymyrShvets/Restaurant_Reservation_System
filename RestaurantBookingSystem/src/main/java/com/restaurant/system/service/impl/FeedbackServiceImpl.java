package com.restaurant.system.service.impl;

import com.restaurant.system.model.Feedback;
import com.restaurant.system.repository.FeedbackRepository;
import com.restaurant.system.service.api.FeedbackService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private FeedbackRepository repository;

    @Override
    public Feedback leaveFeedback(Feedback feedback) {
        log.info("Creating new Feedback with id {}", feedback.getID());

        if (repository.existsById(feedback.getID()))
            throw new IllegalArgumentException(); // TODO

        Feedback newFeedback = repository.save(feedback);

        log.info("Feedback with id {} successfully created", feedback.getID());
        return newFeedback;
    }

    @Override
    public Feedback getFeedback(long id) {
        log.info("Getting Feedback with id {}", id);

        Optional<Feedback> feedback = repository.findById(id);
        if (feedback.isEmpty())
            throw new IllegalArgumentException(); // TODO create FeedbackNotFound exception

        return feedback.get();
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        log.info("Updating Feedback with id {}", feedback.getID());

        Optional<Feedback> persistedFeedback = repository.findById(feedback.getID());
        if (persistedFeedback.isEmpty())
            throw new IllegalArgumentException(); // TODO create UserNotFound exception

        //persistedUser = populateWithPresentFields(); // TODO create method
        Feedback storedFeedback = repository.save(persistedFeedback.get());

        log.info("Feedback with id {} successfully updated", storedFeedback.getID());
        return storedFeedback;
    }

    @Override
    public void deleteFeedback(long id) {
        log.info("Deleting the Feedback with id {}", id);
        repository.deleteById(id);
    }
}
