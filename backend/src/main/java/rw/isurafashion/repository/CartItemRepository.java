package rw.isurafashion.repository;
import rw.isurafashion.model.*; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface CartItemRepository extends JpaRepository<CartItem,Long>{List<CartItem> findByUser(User u); Optional<CartItem> findByUserAndProduct(User u,Product p);}
