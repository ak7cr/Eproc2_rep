package ak.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ak.entity.EmailDetails;
import ak.service.EmailService;

@CrossOrigin(origins = "http://localhost:8085/registration")
@RestController
public class EmailController {

    @Autowired 
    private EmailService emailService;
    
    @GetMapping("/login")
    public ModelAndView LoginPage() {
        return new ModelAndView("login");
    }
    
    @GetMapping("/otp")
    public ModelAndView OtpVerificationPage() {
        return new ModelAndView("otp");
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details) {
        return emailService.sendSimpleMail(details);
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        return emailService.sendMailWithAttachment(details);
    }
    
    @PostMapping("/sendOtp")
    public ResponseEntity<Map<String, Object>> sendOtp(@RequestBody EmailDetails details) {
        String email = details.getRecipient();
        Map<String, Object> response = new HashMap<>();
        try {
            emailService.sendOtpMail(email);
            response.put("success", true);
            response.put("message", "OTP sent successfully.");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred while sending the OTP.");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<Map<String, Object>> verifyOtp(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String otp = requestBody.get("otp");
        Map<String, Object> response = new HashMap<>();
        boolean isVerified = emailService.verifyOtp(email, otp);
        response.put("success", isVerified);
        response.put("message", isVerified ? "OTP verified successfully!" : "Invalid or expired OTP!");
        return ResponseEntity.ok(response);
    }
}