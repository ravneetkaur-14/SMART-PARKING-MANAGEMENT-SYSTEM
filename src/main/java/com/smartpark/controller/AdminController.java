package com.smartpark.controller;
import com.smartpark.entity.*; import com.smartpark.enums.*; import com.smartpark.repository.*; import jakarta.servlet.http.*; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*; import java.time.LocalDateTime;
@Controller @RequestMapping("/admin") public class AdminController {
 private final BookingRepository bookings; private final ParkingSlotRepository slots; private final PaymentRepository payments;
 public AdminController(BookingRepository b,ParkingSlotRepository s,PaymentRepository p){bookings=b;slots=s;payments=p;}
 private boolean allowed(HttpSession s){User u=(User)s.getAttribute("user");return u!=null&&u.getRole()==Role.ADMIN;}
 @GetMapping("/entries") String entries(HttpSession s,Model m){if(!allowed(s))return "redirect:/login";m.addAttribute("bookings",bookings.findTop8ByOrderByCreatedAtDesc());return "admin/entries";}
 @PostMapping("/entries/{id}/enter") String enter(@PathVariable Long id,HttpSession s){if(allowed(s)){Booking b=bookings.findById(id).orElseThrow();b.setStatus(BookingStatus.ACTIVE);b.getSlot().setStatus(SlotStatus.OCCUPIED);slots.save(b.getSlot());bookings.save(b);}return "redirect:/admin/entries";}
 @PostMapping("/entries/{id}/exit") String exit(@PathVariable Long id,@RequestParam PaymentMethod method,HttpSession s){if(allowed(s)){Booking b=bookings.findById(id).orElseThrow();b.setStatus(BookingStatus.COMPLETED);b.getSlot().setStatus(SlotStatus.AVAILABLE);slots.save(b.getSlot());bookings.save(b);Payment p=new Payment();p.setBooking(b);p.setAmount(b.getEstimatedCharge());p.setMethod(method);p.setPaymentCode("PAY-"+System.currentTimeMillis());payments.save(p);}return "redirect:/admin/entries";}
 @PostMapping("/slots/{id}/status") String status(@PathVariable Long id,@RequestParam SlotStatus status,HttpSession s){if(allowed(s)){ParkingSlot p=slots.findById(id).orElseThrow();p.setStatus(status);slots.save(p);}return "redirect:/slots";}
}
