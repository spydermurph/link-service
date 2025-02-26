package is.webworks.link_service.service;

import is.webworks.link_service.dao.UrlDao;
import is.webworks.link_service.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Service
public class RedirectService {
    @Autowired
    UrlDao urlDao;

    public ResponseEntity<String> redirect(UUID id){
        Optional optional = urlDao.findById(id);
        if(optional.isEmpty()){
            // TODO: Need to find better way to handle url not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url not found.");
        }
        Url url = (Url) optional.get();
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url.getMessageUrl())).build();
    }
}
