package com.smartpark.controller;
import com.smartpark.entity.User; import com.smartpark.service.AuthService; import jakarta.servlet.http.*; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
@Controller public class AuthController {
 private final AuthService auth; public AuthController(AuthService a){auth=a;}
 @GetMapping({"/","/login"}) String login(){return "auth/login";}
 @PostMapping("/login") String login(@RequestParam String email,@RequestParam String password,HttpSession s,Model m){try{User u=auth.login(email,password);s.setAttribute("user",u);return "redirect:/dashboard";}catch(IllegalArgumentException e){m.addAttribute("error",e.getMessage());return "auth/login";}}
 @GetMapping("/register") String register(){return "auth/register";}
 @PostMapping("/register") String register(@RequestParam String name,@RequestParam String email,@RequestParam String password,HttpSession s,Model m){try{s.setAttribute("user",auth.register(name,email,password));return "redirect:/dashboard";}catch(IllegalArgumentException e){m.addAttribute("error",e.getMessage());return "auth/register";}}
 @GetMapping("/logout") String logout(HttpSession s){s.invalidate();return "redirect:/login?logout";}
}
