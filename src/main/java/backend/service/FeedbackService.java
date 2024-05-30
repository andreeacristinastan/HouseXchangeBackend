package backend.service;

import backend.dto.feedback.FeedbackCreationDto;
import backend.dto.feedback.FeedbackDto;

public interface FeedbackService {
    FeedbackDto createFeedback(FeedbackCreationDto feedbackCreationDto);
}
