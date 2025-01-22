package ak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ak.entity.Vendor;
import ak.service.VendorService;
//import ak.util.PBKDF2Hasher;

@RestController
public class RegistrationController {
	
	@Autowired
	private VendorService  vendorService;
	
	@GetMapping("/reg")
	public ModelAndView getHomeRegistratioPage() {
		
		ModelAndView mav=new ModelAndView("registration");
		
		return mav;
		
	}
	
	

//	@GetMapping("/home")
//	public ModelAndView homePage() {
//
//		ModelAndView mav=new ModelAndView("home");
//
//		return mav;
//	}
//	
	
	public RegistrationController(VendorService vendorService) {
        this.vendorService = vendorService;
    }
    
    
    @PostMapping("/vendors")
    public ResponseEntity<?> registerVendor(@RequestBody Vendor vendor) throws Exception {
    	
//    String hashpwd=	PBKDF2Hasher.hashPassword(vendor.getPassword(), PBKDF2Hasher.getSalt());
//    	
//    	     vendor.setPassword(hashpwd);
//    	System.out.println("inide register method..."+ vendor.getPassword());

if (vendorService.findByEmail(vendor.getEmail()) != null) {
    return ResponseEntity.badRequest().body("{\"message\": \"Email already exists.\"}");
}


        Vendor savedVendor = vendorService.registerVendor(vendor);
        return ResponseEntity.ok().body("Vendor registered successfully with ID: " + savedVendor.getId());
    }


}
