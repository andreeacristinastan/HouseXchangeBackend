package backend.dto.profileImage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProfileImageCreationDto {
    private String url;
    private Long userId;
}
