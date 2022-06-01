package kg.peaksoft.peaksoftlmsm1.db.dto.link;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LinkResponse {
    private Long id;
    private String text;
    private String link;

}
