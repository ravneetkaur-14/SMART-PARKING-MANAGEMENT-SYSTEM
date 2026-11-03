package com.smartpark.entity;

import com.smartpark.enums.BookingStatus;
import jakarta.persistence.*;
import java.time.*;

@Entity @Table(name="bookings")
public class Booking {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(unique=true,nullable=false) private String bookingCode;
    @ManyToOne(optional=false) private User user; @ManyToOne(optional=false) private Vehicle vehicle; @ManyToOne(optional=false) private ParkingSlot slot;
    private LocalDate bookingDate; private LocalTime entryTime; private LocalTime expectedExitTime; private double estimatedCharge;
    @Enumerated(EnumType.STRING) private BookingStatus status=BookingStatus.CONFIRMED;
    private LocalDateTime createdAt=LocalDateTime.now();
    public Long getId(){return id;} public String getBookingCode(){return bookingCode;} public void setBookingCode(String v){bookingCode=v;}
    public User getUser(){return user;} public void setUser(User v){user=v;} public Vehicle getVehicle(){return vehicle;} public void setVehicle(Vehicle v){vehicle=v;}
    public ParkingSlot getSlot(){return slot;} public void setSlot(ParkingSlot v){slot=v;} public LocalDate getBookingDate(){return bookingDate;} public void setBookingDate(LocalDate v){bookingDate=v;}
    public LocalTime getEntryTime(){return entryTime;} public void setEntryTime(LocalTime v){entryTime=v;} public LocalTime getExpectedExitTime(){return expectedExitTime;} public void setExpectedExitTime(LocalTime v){expectedExitTime=v;}
    public double getEstimatedCharge(){return estimatedCharge;} public void setEstimatedCharge(double v){estimatedCharge=v;} public BookingStatus getStatus(){return status;} public void setStatus(BookingStatus v){status=v;}
    public LocalDateTime getCreatedAt(){return createdAt;}
}
