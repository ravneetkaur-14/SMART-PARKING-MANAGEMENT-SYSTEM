package com.smartpark.repository;
import com.smartpark.entity.User; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface UserRepository extends JpaRepository<User,Long>{Optional<User> findByEmail(String email); long countByRole(com.smartpark.enums.Role role);}
