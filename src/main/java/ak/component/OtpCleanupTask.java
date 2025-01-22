package ak.component;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ak.repository.OtpRepository;

@Component
public class OtpCleanupTask {

    @Autowired
    private OtpRepository otpRepository;

    @Scheduled(fixedRate = 3600000) 
    public void cleanUpExpiredOtps() {
        otpRepository.deleteExpiredOtps(LocalDateTime.now());
    }
}
