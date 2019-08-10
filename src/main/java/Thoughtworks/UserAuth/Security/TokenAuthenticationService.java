package Thoughtworks.UserAuth.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class TokenAuthenticationService {
    private static final long EXPIRE_TIME = 36000;
    private static final String SECRET = "P@ssw02d";
    private static final String TOKEN_PREFIX = "TOKEN";
    private static final String HEADER_TITLE = "Authorization";

    static void addAuthorization(HttpServletResponse response, Authentication authentication) {
        String jwt = Jwts.builder()
                .claim("authorization", authentication.getAuthorities())
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("token", jwt);
    }
}
