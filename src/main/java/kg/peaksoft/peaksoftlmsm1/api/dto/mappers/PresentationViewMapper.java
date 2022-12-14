package kg.peaksoft.peaksoftlmsm1.api.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.api.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.Presentation;
import org.springframework.stereotype.Component;

@Component
public class PresentationViewMapper {

    public PresentationResponse mapToResponse(Presentation presentation) {
        if (presentation == null) {
            return null;
        }
        PresentationResponse presentationResponse = new PresentationResponse();
        if (presentation.getId() != null) {
            presentationResponse.setId(presentation.getId());
        }
        presentationResponse.setName(presentation.getName());
        presentationResponse.setDescription(presentation.getDescription());
        presentationResponse.setFile(presentation.getFile());
        return presentationResponse;
    }
}

