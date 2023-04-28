package com.restaurant.system.controller;

import com.restaurant.system.model.Feedback;
import com.restaurant.system.service.api.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {
    private FeedbackService feedbackService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Feedback leaveFeedback(@RequestBody Feedback feedback) {
        return feedbackService.leaveFeedback(feedback);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Feedback getFeedback(@PathVariable long id) {
        return feedbackService.getFeedback(id);
    } // TODO think about changing to get by user id and restaurant id

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Feedback updateFeedback(@RequestBody Feedback feedback) {
        return feedbackService.updateFeedback(feedback);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable long id) {
        feedbackService.deleteFeedback(id);
    }
}
