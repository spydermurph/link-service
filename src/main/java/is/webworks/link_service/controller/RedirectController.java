package is.webworks.link_service.controller;

import is.webworks.link_service.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping
public class RedirectController {
    @Autowired
    RedirectService redirectService;

    @GetMapping("/{id}")
    public ResponseEntity<String> redirect(@PathVariable UUID id){
        return redirectService.redirect(id);
    }
}
