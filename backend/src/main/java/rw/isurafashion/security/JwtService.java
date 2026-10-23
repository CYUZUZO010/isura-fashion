package rw.isurafashion.security;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import java.nio.charset.StandardCharsets; import java.security.Key; import java.util.Date;
@Service public class JwtService {
 private final Key key; public JwtService(@Value("${app.jwt-secret}") String secret){key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));}
 public String create(Long id,String email,String role){return Jwts.builder().subject(email).claim("id",id).claim("role",role).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+1000L*60*30)).signWith(key).compact();}
 public Claims parse(String token){return Jwts.parser().verifyWith((javax.crypto.SecretKey)key).build().parseSignedClaims(token).getPayload();}
}
