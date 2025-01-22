// Java Program to Illustrate Creation Of
// Service Interface

package ak.service;

import org.springframework.stereotype.Service;

import ak.entity.EmailDetails;

@Service
public interface EmailService {

  
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
    
    String sendOtpMail(String email); // To send 
    boolean verifyOtp(String email, String otp); // to verify
  
}
