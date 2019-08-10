package Thoughtworks.UserAuth.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    static Authentication getAuthentication(HttpServletRequest request) {
        // 从Header中拿到token
        String token = request.getHeader(HEADER_TITLE);
        if (token != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            String userName = claims.getSubject();
            String authority = ((List<Map<String, String>>) claims.get("authorization")).get(0).get("authority");
            List<GrantedAuthority> authorization = AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
            return userName ==
                    null ? null :
                    new UsernamePasswordAuthenticationToken(userName, null, authorization);
        }
        return null;
    }
}
