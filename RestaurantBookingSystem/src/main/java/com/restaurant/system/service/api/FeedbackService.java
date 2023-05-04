package com.restaurant.system.service.api;

import com.restaurant.system.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback leaveFeedback(Feedback feedback);

    Feedback getFeedback(long id);

    List<Feedback> getAllUserFeedbacks(long customerId);

    Feedback updateFeedback(Feedback feedback);

    void deleteFeedback(long id);
}
