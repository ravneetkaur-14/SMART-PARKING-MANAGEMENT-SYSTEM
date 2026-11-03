package com.smartpark.repository;
import com.smartpark.entity.*; import com.smartpark.enums.*; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import java.time.*; import java.util.*;
public interface BookingRepository extends JpaRepository<Booking,Long>{
 List<Booking> findTop8ByOrderByCreatedAtDesc(); List<Booking> findByUserOrderByCreatedAtDesc(User user);
 long countByBookingDate(LocalDate date); long countByStatus(BookingStatus status);
 @Query("select coalesce(sum(b.estimatedCharge),0) from Booking b where b.bookingDate=:date and b.status <> :cancelled") double revenue(@Param("date") LocalDate date,@Param("cancelled") BookingStatus cancelled);
}
