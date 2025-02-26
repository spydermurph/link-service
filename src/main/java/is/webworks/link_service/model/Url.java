package is.webworks.link_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String messageUrl;
    private Date createdDate;
    private Date expiryDate;
}