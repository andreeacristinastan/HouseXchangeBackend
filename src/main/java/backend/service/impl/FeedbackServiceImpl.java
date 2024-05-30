package backend.service.impl;

import backend.dto.feedback.FeedbackCreationDto;
import backend.dto.feedback.FeedbackDto;
import backend.entity.Feedback;
import backend.entity.Trip;
import backend.entity.User;
import backend.exceptions.DatabaseException;
import backend.exceptions.ResourceNotFoundException;
import backend.mapper.FeedbackMapper;
import backend.mapper.TripMapper;
import backend.repository.FeedbackRepository;
import backend.repository.UserRepository;
import backend.security.AuthUtil;
import backend.service.FeedbackService;
import backend.utils.CheckPermissionsHelper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private UserRepository userRepository;
    private AuthUtil authUtil;
    private CheckPermissionsHelper checkPermissionsHelper;
    private FeedbackRepository feedbackRepository;

    @Override
    public FeedbackDto createFeedback(FeedbackCreationDto feedbackCreationDto) {
        User user;
        try {
            user = userRepository.findById(feedbackCreationDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }

        checkPermissionsHelper.checkAuth(user.getUsername(), authUtil);

        Feedback feedback = FeedbackMapper.mapToFeedback(feedbackCreationDto);

        Feedback savedFeedback;
        try {
            savedFeedback = feedbackRepository.save(feedback);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Exception occurred while accessing the database", exception);
        }
        return FeedbackMapper.mapToFeedbackDto(savedFeedback);
    }
}
