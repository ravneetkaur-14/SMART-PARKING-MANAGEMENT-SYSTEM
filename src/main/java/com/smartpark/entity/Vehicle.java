package com.smartpark.entity;

import com.smartpark.enums.VehicleType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="vehicles", uniqueConstraints=@UniqueConstraint(columnNames={"registrationNumber"}))
public class Vehicle {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private User owner;
    @Column(nullable=false) private String registrationNumber;
    @Enumerated(EnumType.STRING) private VehicleType vehicleType;
    private String contactNumber; private LocalDateTime entryTime; private LocalDateTime exitTime;
    public Vehicle() {}
    public Vehicle(User o,String reg,VehicleType t,String phone){owner=o;registrationNumber=reg;vehicleType=t;contactNumber=phone;}
    public Long getId(){return id;} public User getOwner(){return owner;} public void setOwner(User v){owner=v;}
    public String getRegistrationNumber(){return registrationNumber;} public void setRegistrationNumber(String v){registrationNumber=v;}
    public VehicleType getVehicleType(){return vehicleType;} public void setVehicleType(VehicleType v){vehicleType=v;}
    public String getContactNumber(){return contactNumber;} public void setContactNumber(String v){contactNumber=v;}
    public LocalDateTime getEntryTime(){return entryTime;} public void setEntryTime(LocalDateTime v){entryTime=v;}
    public LocalDateTime getExitTime(){return exitTime;} public void setExitTime(LocalDateTime v){exitTime=v;}
}
