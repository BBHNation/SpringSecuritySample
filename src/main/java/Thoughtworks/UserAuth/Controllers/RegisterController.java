package Thoughtworks.UserAuth.Controllers;

import Thoughtworks.UserAuth.Requests.RegisterRequest;
import Thoughtworks.UserAuth.Services.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public void register(@Valid @RequestBody RegisterRequest request) {
        registerService.register(
                request.getUserName(),
                request.getPassword());
    }
}
