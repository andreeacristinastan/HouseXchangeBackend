package backend.controller;

import backend.dto.feedback.FeedbackCreationDto;
import backend.dto.feedback.FeedbackDto;
import backend.dto.property.PropertyCreationDto;
import backend.dto.property.PropertyDto;
import backend.service.FeedbackService;
import backend.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowPrivateNetwork = "true", allowedHeaders = "*")
public class FeedbackController {
    private FeedbackService feedbackService;

    //Add property to a specific user REST API
    @PostMapping
    public ResponseEntity<FeedbackDto> createFeedback(@RequestBody FeedbackCreationDto feedbackCreationDto) {
        FeedbackDto savedFeedback = feedbackService.createFeedback(feedbackCreationDto);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }
}
