package kg.peaksoft.peaksoftlmsm1.api.dto.responseAll;

import kg.peaksoft.peaksoftlmsm1.api.dto.course.CourseResponce;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseResponseAll {

    private List<CourseResponce> courseResponses;
}
