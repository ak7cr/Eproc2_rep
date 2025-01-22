package ak.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ak.entity.OtpEntity;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
    
    // Find the latest valid OTP associated with an email
    @Query("SELECT o FROM OtpEntity o WHERE o.email = :email AND o.expiryTime > :now ORDER BY o.expiryTime DESC")
    OtpEntity findLatestValidOtp(@Param("email") String email, @Param("now") LocalDateTime now);
    
    // Other methods
    List<OtpEntity> findByEmail(String email);

    @Modifying
    @Query("DELETE FROM OtpEntity o WHERE o.id = :id")
    void deleteById(@SuppressWarnings("null") @Param("id") Long id);

    @Modifying
    @Query("DELETE FROM OtpEntity o WHERE o.expiryTime < :now")
    void deleteExpiredOtps(@Param("now") LocalDateTime now);
}
