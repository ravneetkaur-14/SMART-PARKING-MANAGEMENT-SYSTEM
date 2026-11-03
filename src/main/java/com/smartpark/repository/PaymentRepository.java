package com.smartpark.repository;
import com.smartpark.entity.Payment; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface PaymentRepository extends JpaRepository<Payment,Long>{Optional<Payment> findByBookingId(Long bookingId);}
