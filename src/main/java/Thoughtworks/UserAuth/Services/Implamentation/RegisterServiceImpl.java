package Thoughtworks.UserAuth.Services.Implamentation;

import Thoughtworks.UserAuth.Entities.User;
import Thoughtworks.UserAuth.Repositories.UserRepository;
import Thoughtworks.UserAuth.Services.RegisterService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(String userName, String password) {
        userRepository.save(new User(userName, passwordEncoder.encode(password)));
    }
}
