package com.smartpark.entity;

import com.smartpark.enums.*;
import jakarta.persistence.*;

@Entity @Table(name="parking_slots")
public class ParkingSlot {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true) private String slotCode;
    private String floor;
    @Enumerated(EnumType.STRING) private VehicleType vehicleType;
    @Enumerated(EnumType.STRING) private SlotStatus status = SlotStatus.AVAILABLE;
    private double hourlyPrice;
    public ParkingSlot() {}
    public ParkingSlot(String code,String floor,VehicleType type,double price){slotCode=code;this.floor=floor;vehicleType=type;hourlyPrice=price;}
    public Long getId(){return id;} public String getSlotCode(){return slotCode;} public void setSlotCode(String v){slotCode=v;}
    public String getFloor(){return floor;} public void setFloor(String v){floor=v;} public VehicleType getVehicleType(){return vehicleType;}
    public void setVehicleType(VehicleType v){vehicleType=v;} public SlotStatus getStatus(){return status;} public void setStatus(SlotStatus v){status=v;}
    public double getHourlyPrice(){return hourlyPrice;} public void setHourlyPrice(double v){hourlyPrice=v;}
}
