package is.webworks.link_service.controller;

import is.webworks.link_service.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping
@CrossOrigin
public class RedirectController {
    @Autowired
    RedirectService redirectService;

    @GetMapping("/{id}")
    public ResponseEntity<String> redirect(@PathVariable UUID id){
        return redirectService.redirect(id);
    }
}
