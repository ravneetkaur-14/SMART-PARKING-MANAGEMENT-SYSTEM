package com.smartpark.entity;
import com.smartpark.enums.PaymentMethod;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="payments")
public class Payment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(unique=true) private String paymentCode; @OneToOne(optional=false) private Booking booking; private double amount;
 @Enumerated(EnumType.STRING) private PaymentMethod method; private String status="PAID"; private LocalDateTime paidAt=LocalDateTime.now();
 public Long getId(){return id;} public String getPaymentCode(){return paymentCode;} public void setPaymentCode(String v){paymentCode=v;}
 public Booking getBooking(){return booking;} public void setBooking(Booking v){booking=v;} public double getAmount(){return amount;} public void setAmount(double v){amount=v;}
 public PaymentMethod getMethod(){return method;} public void setMethod(PaymentMethod v){method=v;} public String getStatus(){return status;} public LocalDateTime getPaidAt(){return paidAt;}
}
