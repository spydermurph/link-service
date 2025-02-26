package is.webworks.link_service.model;

import lombok.Data;

@Data
public class EmailContentResponse {
    public EmailContentResponse (){}

    public EmailContentResponse(String id, String emailContent) {
        this.emailContent = emailContent;
    }
    private String emailContent;
}
