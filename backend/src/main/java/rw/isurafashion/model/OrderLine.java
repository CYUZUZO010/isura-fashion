package rw.isurafashion.model;
import jakarta.persistence.*; import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor
public class OrderLine {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private Order order;
 @ManyToOne(optional=false) private Product product;
 @Column(nullable=false) private int quantity;
 @Column(nullable=false) private long unitPrice;
}
