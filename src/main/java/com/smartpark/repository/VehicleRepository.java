package com.smartpark.repository;
import com.smartpark.entity.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface VehicleRepository extends JpaRepository<Vehicle,Long>{List<Vehicle> findByOwner(User owner); Optional<Vehicle> findByRegistrationNumberIgnoreCase(String reg);}
