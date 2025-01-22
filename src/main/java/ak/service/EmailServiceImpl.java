package ak.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ak.entity.EmailDetails;
import ak.entity.OtpEntity;
import ak.repository.OtpRepository;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OtpRepository otpRepository;

    @Value("${spring.mail.username}")
    private String sender;

    // Send OTP and store it in the database with an expiry time
    @Override
    public String sendOtpMail(String email) {
        String otp = generateOtp(); // Generate a 6-digit OTP

        // Save OTP in the database with an expiry timestamp (5 minutes from now)
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5)); // OTP valid for 5 minutes
        otpRepository.save(otpEntity);

        // Prepare email details
        String subject = "Your OTP Code";
        String msgBody = "Your OTP is: " + otp + ". It is valid for 5 minutes.";
        EmailDetails details = new EmailDetails(email, msgBody, subject, null);

        // Use the existing simple mail functionality
        return sendSimpleMail(details);
    }

    // Verify OTP with expiration check
    @Transactional
    @Override
    public boolean verifyOtp(String email, String otp) {
        // Fetch all OTPs for the email
        List<OtpEntity> otpEntities = otpRepository.findByEmail(email);

        // Iterate over the OTPs to find a valid match
        for (OtpEntity otpEntity : otpEntities) {
            if (otpEntity.getOtp().equals(otp) && otpEntity.getExpiryTime().isAfter(LocalDateTime.now())) {
                // Delete the OTP after successful verification
                otpRepository.deleteById(otpEntity.getId());
                return true; // OTP is correct and valid
            }
        }

        // No valid OTP found
        return false;
    }



    // Generate a random 6-digit OTP
    private String generateOtp() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            // Log the error for better visibility
            e.printStackTrace();
            return "Error while Sending Mail";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            // Add attachment if it exists
            if (details.getAttachment() != null) {
                FileSystemResource file = new FileSystemResource(details.getAttachment());
                mimeMessageHelper.addAttachment(file.getFilename(), file);
            }

            javaMailSender.send(mimeMessage);
            return "Mail Sent Successfully with Attachment...";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while Sending Mail with Attachment";
        }
    }
}
