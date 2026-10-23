package rw.isurafashion.repository;
import rw.isurafashion.model.User; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface UserRepository extends JpaRepository<User,Long>{Optional<User> findByEmailIgnoreCase(String email);}
