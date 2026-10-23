package rw.isurafashion.repository;
import rw.isurafashion.model.*; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface WishlistItemRepository extends JpaRepository<WishlistItem,Long>{List<WishlistItem> findByUser(User u); Optional<WishlistItem> findByUserAndProduct(User u,Product p);}
