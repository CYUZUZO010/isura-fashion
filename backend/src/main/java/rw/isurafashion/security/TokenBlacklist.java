package rw.isurafashion.security;
import org.springframework.stereotype.Service; import java.time.Instant; import java.util.concurrent.ConcurrentHashMap;
@Service public class TokenBlacklist {private final ConcurrentHashMap<String,Instant> revoked=new ConcurrentHashMap<>(); public void revoke(String token,Instant expires){revoked.put(token,expires);} public boolean isRevoked(String token){Instant x=revoked.get(token);if(x==null)return false;if(x.isBefore(Instant.now())){revoked.remove(token);return false;}return true;}}
