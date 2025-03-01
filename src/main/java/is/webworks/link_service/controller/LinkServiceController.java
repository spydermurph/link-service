package is.webworks.link_service.controller;

import is.webworks.link_service.model.EmailContent;
import is.webworks.link_service.model.EmailContentResponse;
import is.webworks.link_service.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin
public class LinkServiceController {
    @Autowired
    LinkService linkService;

    @PostMapping("transform")
    public ResponseEntity<EmailContentResponse> html(@RequestBody EmailContent emailContent){
        return linkService.transformMessage(emailContent);
    }
}
