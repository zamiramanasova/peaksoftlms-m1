package kg.peaksoft.peaksoftlmsm1.api.dto.test.request.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Option;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Test;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuestionResponse {

    private Long id;
    private String questionTitle;
    private List<Option> options;
    private Test test;

}
