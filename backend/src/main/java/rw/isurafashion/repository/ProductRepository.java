package rw.isurafashion.repository;
import rw.isurafashion.model.Product; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface ProductRepository extends JpaRepository<Product,Long>{Optional<Product> findBySku(String sku); List<Product> findByPublishedTrueOrderByIdDesc(); List<Product> findByPublishedTrueAndNameContainingIgnoreCaseOrPublishedTrueAndSkuContainingIgnoreCase(String name,String sku);}
