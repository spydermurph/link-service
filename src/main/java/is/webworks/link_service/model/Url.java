package is.webworks.link_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID redirectCode;
    private String messageUrl;
    private Date createdDate;
    private Date expiryDate;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}