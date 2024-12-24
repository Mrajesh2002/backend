package com.example.demo;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins="http://192.168.0.105:3000/admin-dash")

@RequestMapping("/api")
public class UserController {
	 @Autowired
	    private UserService userService;
	 
	 

//	    @PostMapping("/user")
//	    public ResponseEntity<AdminTable> createUser(@RequestBody AdminTable admin) {
//	    	AdminTable savedUser = userService.saveUser(admin);
//	        return ResponseEntity.ok(savedUser);
//	    }
	 
	 @PostMapping("/check")
	    public ResponseEntity<String> createUser(@RequestBody AdminTable admin) {
	        // Check if user already exists by email or mobile
	        if (userService.userExists(admin.getEmail(), admin.getMobile())) {
	            return new ResponseEntity<>("User with this email or mobile already exists!", HttpStatus.BAD_REQUEST);
	        }

	        // If user doesn't exist, save the new user
	        userService.saveUser(admin);
	        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<AdminTable> getUser(@PathVariable Long id) {
	    	AdminTable admin = userService.getUser(id);
	        if (admin != null) {
	            return ResponseEntity.ok(admin);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	    @Autowired
	    private JavaMailSender mailSender;
	    
	    @PostMapping("/test-email")
	    public void testEmail() {
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo("rajeshmodela@gmail.com");  // Replace with the test recipient email
	        mailMessage.setSubject("Welcome!!!");
	        mailMessage.setText("This is a test email.");
	        mailSender.send(mailMessage);  // Send test email
	        System.out.println("Test email sent successfully.");
	    }

	    
	    
	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {
	        String email = loginData.get("email");
	        String password = loginData.get("password");

	        if (userService.validateLogin(email, password)) {
	            return ResponseEntity.ok("Login successful!");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
	        }
	    }

	    
	    
	   

	   
	    

	    
}
