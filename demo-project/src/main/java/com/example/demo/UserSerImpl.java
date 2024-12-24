package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.UUID;
@Service
public class UserSerImpl implements UserService {

	 @Autowired
	    private UserRepo userRepository;

	    @Autowired
	    private JavaMailSender mailSender;
	
	@Override
	public AdminTable saveUser(AdminTable admin) {
		// TODO Auto-generated method stub
		String randomPassword = UUID.randomUUID().toString().substring(0, 8);
		admin.setPassword(randomPassword);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(admin.getEmail());
        mailMessage.setSubject("Your Account Password");
        mailMessage.setText("name");
        mailMessage.setText("Your generated password is: " + randomPassword);
        mailSender.send(mailMessage);
		return userRepository.save(admin);
	}
	
	public boolean userExists(String email, String mobile) {
        // Check if a user exists by email or mobile
        return userRepository.existsByEmail(email) || userRepository.existsByMobile(mobile);
    }

	@Override
	public AdminTable getUser(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElse(null);
	}
	
	
	
	@Override
	public boolean validateLogin(String email, String rawPassword) {
	    AdminTable user = userRepository.findByEmail(email).orElse(null);
	    if (user != null && user.getPassword().equals(rawPassword)) {
	        return true; // Login successful
	    }
	    return false; // Invalid credentials
	}


	
	
	

}
