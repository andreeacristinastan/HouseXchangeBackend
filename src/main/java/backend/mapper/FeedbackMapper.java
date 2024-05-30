package backend.mapper;

import backend.dto.feedback.FeedbackCreationDto;
import backend.dto.feedback.FeedbackDto;
import backend.entity.Feedback;

public class FeedbackMapper {

    public static Feedback mapToFeedback(FeedbackCreationDto feedbackCreationDto) {
        return new Feedback(
                feedbackCreationDto.getFeedback(),
                feedbackCreationDto.getToTheProperty(),
                feedbackCreationDto.getToTheApp(),
                feedbackCreationDto.getOther(),
                feedbackCreationDto.getUserId()
        );

    }

    public static FeedbackDto mapToFeedbackDto(Feedback feedback) {
        return new FeedbackDto(
                feedback.getId(),
                feedback.getFeedback(),
                feedback.getToTheApp(),
                feedback.getToTheProperty(),
                feedback.getOther(),
                feedback.getUserId()
        );

    }
}
