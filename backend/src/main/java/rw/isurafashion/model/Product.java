package rw.isurafashion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
public class Product {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true) private String sku;
  @Column(nullable=false) private String name;
  @Column(nullable=false) private String category;
  @Column(length=2000) private String description;
  @Column(nullable=false) private long priceRwf;
  @Column(nullable=false) private String size;
  @Column(nullable=false) private String colour;
  @Column(nullable=false) private int stockQty;
  @Column(nullable=false) private boolean published = true;
  private String imageUrl;
}
