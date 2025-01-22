package ak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ak.service.OtpService;
import ak.service.VendorService;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private VendorService vendorService;

    @PostMapping("/validate")
    public ModelAndView validateOtp(
            @RequestParam("email") String email,
            @RequestParam("otp") String otp) {
        ModelAndView mav = new ModelAndView();
        try {
            boolean isValid = otpService.validateOtp(email, otp);
            if (isValid) {
                mav.setViewName("home"); // OTP successfully validated, redirect to home page
            } else {
                mav.addObject("errorMessage", "Invalid OTP.");
                mav.setViewName("otp");
            }
        } catch (Exception e) {
            mav.addObject("errorMessage", "An error occurred while validating the OTP.");
            mav.setViewName("otp");
        }
        return mav;
    }

    @PostMapping("/generate")
    public ModelAndView generateOtp(@RequestParam("email") String email) {
        ModelAndView mav = new ModelAndView();
        try {
            // Check if the email exists in the database
            if (vendorService.findByEmail(email) == null) {
                mav.addObject("errorMessage", "Email not registered!");
                mav.setViewName("otp"); // Redirect to the OTP page with error message
            } else {
                otpService.generateAndSaveOtp(email); // Generate and save OTP
                mav.addObject("successMessage", "OTP successfully generated and sent to " + email);
                mav.setViewName("otp"); // Redirect to the OTP page
            }
        } catch (Exception e) {
            mav.addObject("errorMessage", "An error occurred while generating the OTP.");
            mav.setViewName("otp");
        }
        return mav;
    }
}
