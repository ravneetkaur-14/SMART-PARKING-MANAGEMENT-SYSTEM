package com.smartpark.config;
import com.smartpark.entity.*; import com.smartpark.enums.*; import com.smartpark.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration public class DataSeeder {
 @Bean CommandLineRunner seed(UserRepository users,ParkingSlotRepository slots,PasswordEncoder encoder){return args->{
  User admin=users.findByEmail("admin@smartpark.com").orElseGet(()->users.save(new User("System Admin","admin@smartpark.com",encoder.encode("Admin@123"),Role.ADMIN)));
  users.findByEmail("user@smartpark.com").orElseGet(()->users.save(new User("Demo User","user@smartpark.com",encoder.encode("User@123"),Role.USER)));
  if(slots.count()==0){for(int floor=1;floor<=3;floor++)for(int n=1;n<=10;n++){VehicleType t=n<=3?VehicleType.BIKE:n<=8?VehicleType.CAR:VehicleType.SUV;double p=t==VehicleType.BIKE?20:t==VehicleType.CAR?40:60;slots.save(new ParkingSlot((char)('A'+floor-1)+String.format("%02d",n),"Floor "+floor,t,p));}}
 }; }
}
