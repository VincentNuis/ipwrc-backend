package spring.boot.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import spring.boot.Role.UserRole;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private final SecretKey key = Jwts.SIG.HS512.key().build();

    // Genereer een JWT-token
    public String generateToken(String email, List<UserRole> roles) {
        System.out.println("Generating Token...");
        return Jwts.builder()
                .subject(email)
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dag
                .signWith(key)
                .compact();
    }

    // Haal de claims uit het token
    public Claims getClaims(String token) {
        System.out.print("Getting Claims...");
        return Jwts.parser() // Gebruik parserBuilder() in plaats van parser()
                .verifyWith(key) // Stel de signing key in
                .build()
                .parseSignedClaims(token) // Parseer de claims
                .getPayload();
    }


    // Valideer of het token geldig is
    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token); // Haal claims op

            // Valideer de vervaldatum
            if (claims.getExpiration().before(new Date())) {
                System.out.println("Token verlopen...");
                return false;  // Token is verlopen
            }
            System.out.println("Token Geldig...");
            return true; // Token is geldig
        } catch (Exception e) {
            return false; // Token is ongeldig of expired
        }
    }
}