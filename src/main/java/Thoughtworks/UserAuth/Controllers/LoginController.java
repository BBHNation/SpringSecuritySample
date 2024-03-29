package Thoughtworks.UserAuth.Controllers;

import Thoughtworks.UserAuth.Requests.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
    @PostMapping
    public void login(@Valid @RequestBody LoginRequest request) {
        System.out.println(request.getUserName());
        System.out.println(request.getPassword());
        System.out.println("logging");
    }
}
