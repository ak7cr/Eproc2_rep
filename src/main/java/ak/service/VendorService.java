package ak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ak.entity.Vendor;
import ak.repository.VendorRepository;

@Service
public class VendorService {
    
    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor registerVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public List<Vendor> findByEmailContaining(String email) {
        return vendorRepository.findByEmail(email);
    }
    
    public List<Vendor> findByPhoneContaining(String phone) {
        return vendorRepository.findByPhone(phone);
    }

    public Vendor findByEmail(String email) {
        return vendorRepository.findByEmail(email).stream().findFirst().orElse(null);
    }

    public Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }
    
   
}