package rw.isurafashion.model;
import jakarta.persistence.*; import lombok.*; import java.time.Instant; import java.util.*;
@Entity @Table(name="orders") @Getter @Setter @NoArgsConstructor
public class Order {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String orderNo;
 @ManyToOne(optional=false) private User customer;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private OrderState state=OrderState.PLACED;
 @Column(nullable=false) private long subtotal;
 @Column(nullable=false) private long discount;
 @Column(nullable=false) private long shipping;
 @Column(nullable=false) private long total;
 @Column(nullable=false) private String paymentMethod;
 private String paymentReference; private String trackingNumber; private String idempotencyKey;
 @Column(nullable=false) private Instant createdAt=Instant.now();
 @OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true) private List<OrderLine> lines=new ArrayList<>();
 public void addLine(OrderLine line){lines.add(line); line.setOrder(this);}
 public enum OrderState { PLACED, PAID, SHIPPED, DELIVERED, CANCELLED, PAYMENTTIMEDOUT }
}
