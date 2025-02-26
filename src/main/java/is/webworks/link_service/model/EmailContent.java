package is.webworks.link_service.model;

import lombok.Data;

import java.util.Date;

@Data
public class EmailContent {
    public EmailContent(){
        expiryDate = new Date();
        expiryDate.setYear(9999);
    }

    // Email content. Can be either HTML or text.
    private String emailContent;
    private String userName;
    private String password;
    private Date expiryDate;
}