package ak.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ak.entity.OtpEntity;
import ak.repository.OtpRepository;

@Service
public class OtpService {

    private static final int OTP_LENGTH = 6;

    @Autowired
    private OtpRepository otpRepository;

   
    public String generateOtp() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append((int) (Math.random() * 10)); // Random digit between 0-9
        }
        return otp.toString();
    }

  
    public void generateAndSaveOtp(String email) {
        // Generate a new OTP
        String otp = generateOtp();
        
        // Create an OtpEntity object
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(10)); 

        otpRepository.save(otpEntity);
  
    }

  
    public boolean validateOtp(String email, String otp) {
        OtpEntity otpEntity = otpRepository.findLatestValidOtp(email, LocalDateTime.now());

    
        if (otpEntity == null) {
            return false;
        }

        return otpEntity.getOtp().equals(otp);
    }
}
