package ak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import ak.entity.Vendor;
import ak.service.VendorService;

@RestController
public class LoginController {

    @Autowired
    private VendorService vendorService;

    public LoginController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/stag")
    public ModelAndView validateLogin(
            @RequestParam("email") String email, 
            @RequestParam("password") String password,
            @RequestParam(value = "g-recaptcha-response", required = false) String recaptchaResponse
    ) throws Exception {
        ModelAndView mav = new ModelAndView();
   
       if (!validateRecaptcha(recaptchaResponse)) {
           mav.addObject("errorMessage", "reCAPTCHA verification failed. Please try again.");
          mav.setViewName("login");
          return mav;
      }

        List<Vendor> vendorList = vendorService.findByEmailContaining(email);

        if (vendorList.isEmpty()) {
            mav.addObject("errorMessage", "Invalid email or password.");
            mav.setViewName("login");
            return mav;
        }

        Vendor vendorData = vendorList.get(0);
        String dbPwd = vendorData.getPassword();

        if (password.equals(dbPwd)) {
            System.out.println("Successfully logged in");
            mav.addObject("vendorName", vendorData.getName()); 
            mav.setViewName("home"); 
        } else {
            mav.addObject("errorMessage", "Invalid email or password.");
            mav.setViewName("login");
        }

        return mav;
    }


  
   private boolean validateRecaptcha(String recaptchaResponse) {
       final String SECRET_KEY = "6LfSzLQqAAAAAGEKUEzZguANSoyCTzHOiDD86w45";
       final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

        if (recaptchaResponse == null || recaptchaResponse.isEmpty()) {
            return false;
      }

      try {
           RestTemplate restTemplate = new RestTemplate();
           String requestUrl = VERIFY_URL + "?secret=" + SECRET_KEY + "&response=" + recaptchaResponse;

           String response = restTemplate.postForObject(requestUrl, null, String.class);

          return response != null && response.contains("\"success\": true");
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
    }
}
