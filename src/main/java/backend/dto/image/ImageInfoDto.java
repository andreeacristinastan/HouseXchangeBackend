package backend.dto.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ImageInfoDto {
    private Long id;
    private String url;
}
