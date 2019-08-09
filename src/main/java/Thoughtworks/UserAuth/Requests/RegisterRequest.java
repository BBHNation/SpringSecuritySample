package Thoughtworks.UserAuth.Requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequest {
    @NotBlank(message = "user name should not be blank")
    private String userName;

    @NotBlank(message = "password should not be blank")
    private String password;
}
