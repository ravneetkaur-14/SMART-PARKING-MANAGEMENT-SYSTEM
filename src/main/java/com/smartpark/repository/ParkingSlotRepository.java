package com.smartpark.repository;
import com.smartpark.entity.ParkingSlot; import com.smartpark.enums.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,Long>{Optional<ParkingSlot> findBySlotCode(String code); List<ParkingSlot> findByStatus(SlotStatus status); List<ParkingSlot> findByVehicleType(VehicleType type);}
