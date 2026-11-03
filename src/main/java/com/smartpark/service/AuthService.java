package com.smartpark.service;
import com.smartpark.entity.User; import com.smartpark.enums.Role; import com.smartpark.repository.UserRepository; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service;
@Service public class AuthService {
 private final UserRepository users; private final PasswordEncoder encoder;
 public AuthService(UserRepository u,PasswordEncoder e){users=u;encoder=e;}
 public User register(String name,String email,String password){if(users.findByEmail(email).isPresent())throw new IllegalArgumentException("Email is already registered."); return users.save(new User(name,email,encoder.encode(password),Role.USER));}
 public User login(String email,String password){User u=users.findByEmail(email).orElseThrow(()->new IllegalArgumentException("Invalid email or password.")); if(!encoder.matches(password,u.getPassword()))throw new IllegalArgumentException("Invalid email or password."); return u;}
}
