package group2.candidates.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private Properties env = new Properties();


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.setAuthenticationManager(authenticationManager);
        this.env.setProperty("JWT_SECRET", "8y/B?E(H+MbQeThWmYq3t6w9z$C&F)J@NcRfUjXn2r4u7x!A%D*G-KaPdSgVkYp3");
        this.env.setProperty("AUTH_LOGIN_URL", "/authenticate");
        this.env.setProperty("TOKEN_PREFIX", "Bearer");
        this.env.setProperty("TOKEN_HEADER", "Authorization");
        this.env.setProperty("TOKEN_AUDIENCE", "Event-Candidate-Management-Client");
        this.env.setProperty("TOKEN_ISSUER", "Event-Candidate-Management-API");
        this.env.setProperty("TOKEN_TYPE", "JWT");

        setFilterProcessesUrl(this.env.getProperty("AUTH_LOGIN_URL"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.debug("jwt authentication is invoked");
        logger.debug(username);
        logger.debug(password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        logger.debug(authenticationManager.toString());
        return authenticationManager.authenticate(token);
        //return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        User account = (User) authResult.getPrincipal();
        System.out.println("user dd" + account.toString());
        List<String> authorities = account.getAuthorities()
                .stream()
                .peek(System.out::println)
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        System.out.println("length of authority " + authorities.size());

        byte[] jwtSecret = env.getProperty("JWT_SECRET").getBytes();

        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, Keys.hmacShaKeyFor(jwtSecret))
                .setHeaderParam("typ", env.getProperty("TOKEN_TYPE"))
                .setIssuer(env.getProperty("TOKEN_ISSUER"))
                .setAudience(env.getProperty("TOKEN_AUDIENCE"))
                .setSubject(account.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .claim("rol", authorities)
                .compact();

        response.addHeader(env.getProperty("TOKEN_HEADER"), env.getProperty("TOKEN_PREFIX") + " " + token);
    }


}
