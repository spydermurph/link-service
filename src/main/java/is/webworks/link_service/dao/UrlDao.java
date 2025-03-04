package is.webworks.link_service.dao;

import is.webworks.link_service.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UrlDao extends JpaRepository<Url, Integer> {
    Url findByRedirectCode(UUID uuid);
}
