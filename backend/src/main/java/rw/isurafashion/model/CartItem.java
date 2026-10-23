package rw.isurafashion.model;
import jakarta.persistence.*; import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor
public class CartItem {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false) private User user;
  @ManyToOne(optional=false) private Product product;
  @Column(nullable=false) private int quantity;
}
