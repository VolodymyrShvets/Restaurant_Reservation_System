package com.restaurant.system.service.api;

import com.restaurant.system.model.Feedback;

public interface FeedbackService {
    Feedback leaveFeedback(Feedback feedback);

    Feedback getFeedback(long id);

    Feedback updateFeedback(Feedback feedback);

    void deleteFeedback(long id);
}
