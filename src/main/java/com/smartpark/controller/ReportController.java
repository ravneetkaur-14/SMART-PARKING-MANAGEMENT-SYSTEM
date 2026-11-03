package com.smartpark.controller;

import com.smartpark.entity.User;
import com.smartpark.repository.BookingRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Controller
public class ReportController {
    private final BookingRepository bookings;
    public ReportController(BookingRepository bookings) { this.bookings = bookings; }

    @GetMapping("/admin/reports.csv")
    @ResponseBody
    public byte[] csv(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != com.smartpark.enums.Role.ADMIN) {
            throw new IllegalStateException("Administrator access is required.");
        }
        StringBuilder csv = new StringBuilder("Booking ID,Date,Vehicle,Slot,Status,Estimated charge\n");
        bookings.findAll().forEach(b -> csv.append(b.getBookingCode()).append(',')
                .append(b.getBookingDate()).append(',')
                .append(b.getVehicle().getRegistrationNumber()).append(',')
                .append(b.getSlot().getSlotCode()).append(',')
                .append(b.getStatus()).append(',')
                .append(b.getEstimatedCharge()).append('\n'));
        return csv.toString().getBytes(StandardCharsets.UTF_8);
    }
}
