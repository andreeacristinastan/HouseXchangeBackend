package backend.dto.feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FeedbackDto {
    private Long id;
    private String feedback;
    private Boolean toTheApp;
    private Boolean toTheProperty;
    private Boolean other ;
    private Long userId;

}
