package Thoughtworks.UserAuth.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/sessions")
public class OtherController {
    @GetMapping
    @PreAuthorize("hasAuthority('USER_RW')")
    public ResponseEntity<String> getInfo() {
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }
}
