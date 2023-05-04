package com.restaurant.system.service.impl;

import com.restaurant.system.model.Customer;
import com.restaurant.system.model.Feedback;
import com.restaurant.system.model.exception.EntityAlreadyExistsException;
import com.restaurant.system.model.exception.EntityNotFoundException;
import com.restaurant.system.repository.CustomerRepository;
import com.restaurant.system.repository.FeedbackRepository;
import com.restaurant.system.service.api.FeedbackService;
import com.restaurant.system.service.mapper.FeedbackMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private FeedbackRepository repository;
    @Lazy
    private CustomerRepository customerRepository;

    @Override
    public Feedback leaveFeedback(Feedback feedback) {
        log.info("Creating new Feedback with id {}", feedback.getID());

        if (repository.existsById(feedback.getID()))
            throw new EntityAlreadyExistsException(Feedback.class, "id " + feedback.getID());

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
            throw new EntityNotFoundException(Feedback.class, "id " + id);

        return feedback.get();
    }

    @Override
    public List<Feedback> getAllUserFeedbacks(long customerId) {
        log.info("Receiving all Feedbacks from Customer with id {}", customerId);

        return repository.findAllByCustomerID(customerId);
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        log.info("Updating Feedback with id {}", feedback.getID());

        Optional<Feedback> persistedFeedback = repository.findById(feedback.getID());
        if (persistedFeedback.isEmpty())
            throw new EntityNotFoundException(Feedback.class, "id " + feedback.getID());

        Feedback newFeedback = FeedbackMapper.INSTANCE.populateFeedbackWithPresentFeedbackFields(persistedFeedback.get(), feedback);
        Feedback storedFeedback = repository.save(newFeedback);

        log.info("Feedback with id {} successfully updated", storedFeedback.getID());
        return storedFeedback;
    }

    @Override
    public void deleteFeedback(long id) {
        log.info("Deleting the Feedback with id {}", id);
        repository.deleteById(id);
    }
}
