//package ak.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import ak.service.VendorService;
//import ak.service.OtpService;
//
//@RestController
//@RequestMapping("/forget")
//public class ForgetController {
//
//    @Autowired
//    private VendorService vendorService;
//
//    @Autowired
//    private OtpService otpService;
//
//    @GetMapping
//    public ModelAndView forgetRemember() {
//        return new ModelAndView("forget");
//    }
//
//    @PostMapping("/generateOtp")
//    public ModelAndView generateOtp(@RequestParam("email") String email) {
//        ModelAndView mav = new ModelAndView();
//        try {
//            if (vendorService.findByEmail(email) == null) {
//                mav.addObject("errorMessage", "Email not registered!");
//                mav.setViewName("forget");
//            } else {
//                otpService.generateAndSaveOtp(email);
//                mav.addObject("email", email); // Pass email to OTP view
//                mav.addObject("successMessage", "OTP successfully generated and sent to " + email);
//                mav.setViewName("otp");
//            }
//        } catch (Exception e) {
//            mav.addObject("errorMessage", "An error occurred while generating the OTP.");
//            mav.setViewName("forget");
//        }
//        return mav;
//    }
//
//}
