package Thoughtworks.UserAuth.Security;

import Thoughtworks.UserAuth.Entities.User;
import Thoughtworks.UserAuth.Repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    CustomAuthenticationProvider(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String passWord = authentication.getCredentials().toString();
        Optional<User> user = userRepository.findByName(userName);
        if (user.isPresent()) {
            if (passwordEncoder.matches(passWord, user.get().getPassCode())) {
                ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new GrantedAuthorityImp("ROLE_USER"));
                grantedAuthorities.add(new GrantedAuthorityImp("USER_RW"));
                return new UsernamePasswordAuthenticationToken(userName, passWord, grantedAuthorities);
            } else {
                throw new RuntimeException("password not correct");
            }
        } else {
            throw new RuntimeException("user not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
