package com.restaurant.system.service.impl;

import com.restaurant.system.model.Customer;
import com.restaurant.system.model.Feedback;
import com.restaurant.system.repository.CustomerRepository;
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
    private CustomerRepository customerRepository;

    @Override
    public Feedback leaveFeedback(Feedback feedback) {
        log.info("Creating new Feedback with id {}", feedback.getID());

        if (repository.existsById(feedback.getID()))
            throw new IllegalArgumentException(); // TODO

        Customer customer = customerRepository.findById(feedback.getCustomer().getID()).get();

        customer.getFeedbacks().add(feedback);
        feedback.setCustomer(customer);
        customerRepository.save(customer);

        log.info("Feedback successfully created");
        return feedback;
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
