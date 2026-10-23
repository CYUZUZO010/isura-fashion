package rw.isurafashion.repository;
import rw.isurafashion.model.*; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface OrderRepository extends JpaRepository<Order,Long>{List<Order> findByCustomerOrderByCreatedAtDesc(User u); Optional<Order> findByCustomerAndId(User u,Long id); Optional<Order> findByIdempotencyKeyAndCustomer(String key,User u);}
