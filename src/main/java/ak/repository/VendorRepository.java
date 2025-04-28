package ak.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ak.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>{
	 // Add a method to search by name, email, phone, or address
    List<Vendor> findByNameContainingOrEmailContainingOrPhoneContainingOrAddressContaining(
        String name, String email, String phone
    );
    
    List<Vendor> findByEmail(String email);
    List<Vendor> findByPhone(String phone);
}
