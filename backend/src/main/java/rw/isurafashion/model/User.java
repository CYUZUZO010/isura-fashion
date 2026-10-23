package rw.isurafashion.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.time.Instant;

@Entity @Table(name="users") @Getter @Setter @NoArgsConstructor
public class User {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true) private String email;
  @JsonIgnore @Column(nullable=false) private String passwordHash;
  @Column(nullable=false) private String phone;
  @Column(nullable=false) private String role = "CUSTOMER";
  @Column(nullable=false) private String loyaltyTier = "STANDARD";
  @Column(nullable=false) private boolean locked = false;
  @Column(nullable=false) private int failedLogins = 0;
  private Instant lockedUntil;
  @Column(nullable=false) private Instant createdAt = Instant.now();
}
