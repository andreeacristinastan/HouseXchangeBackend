package backend.dto.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ImageCreationDto {
    private String publicId;
    private Long propertyId;
}
