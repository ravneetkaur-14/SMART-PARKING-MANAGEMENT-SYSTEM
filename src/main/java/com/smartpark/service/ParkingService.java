package com.smartpark.service;
import com.smartpark.entity.*; import com.smartpark.enums.*; import com.smartpark.repository.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import java.time.*; import java.util.*;
@Service public class ParkingService {
 private final ParkingSlotRepository slots; private final BookingRepository bookings; private final VehicleRepository vehicles;
 public ParkingService(ParkingSlotRepository s,BookingRepository b,VehicleRepository v){slots=s;bookings=b;vehicles=v;}
 public List<ParkingSlot> slots(){return slots.findAll();} public List<Vehicle> vehicles(User u){return vehicles.findByOwner(u);}
 @Transactional public Booking book(User u,Long vehicleId,Long slotId,LocalDate date,LocalTime entry,LocalTime exit){
  Vehicle vehicle=vehicles.findById(vehicleId).orElseThrow(()->new IllegalArgumentException("Vehicle not found."));
  ParkingSlot slot=slots.findById(slotId).orElseThrow(()->new IllegalArgumentException("Slot not found."));
  if(slot.getStatus()!=SlotStatus.AVAILABLE)throw new IllegalArgumentException("This slot is not available.");
  if(vehicle.getOwner().getId()!=u.getId())throw new IllegalArgumentException("Vehicle does not belong to this account.");
  if(!exit.isAfter(entry))throw new IllegalArgumentException("Exit time must be after entry time.");
  Booking b=new Booking(); b.setBookingCode("SP-"+UUID.randomUUID().toString().substring(0,8).toUpperCase()); b.setUser(u);b.setVehicle(vehicle);b.setSlot(slot);b.setBookingDate(date);b.setEntryTime(entry);b.setExpectedExitTime(exit);
  b.setEstimatedCharge(Math.max(1,Duration.between(entry,exit).toMinutes()/60.0)*slot.getHourlyPrice()); slot.setStatus(SlotStatus.RESERVED); slots.save(slot); return bookings.save(b);
 }
 @Transactional public void cancel(User u,Long id){Booking b=bookings.findById(id).orElseThrow();if(!b.getUser().getId().equals(u.getId()))throw new IllegalArgumentException("Not your booking.");if(b.getStatus()==BookingStatus.ACTIVE||b.getStatus()==BookingStatus.COMPLETED)throw new IllegalArgumentException("This booking cannot be cancelled.");b.setStatus(BookingStatus.CANCELLED);b.getSlot().setStatus(SlotStatus.AVAILABLE);slots.save(b.getSlot());bookings.save(b);}
 public BookingRepository bookingRepository(){return bookings;}
}
