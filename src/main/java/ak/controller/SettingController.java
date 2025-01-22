package ak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ak.entity.Vendor;
import ak.service.VendorService;

@RestController
@RequestMapping("/settings")
public class SettingController {

    @Autowired
    private VendorService vendorService;

    @GetMapping
    public ModelAndView showSettings(@RequestParam("email") String email) {
        ModelAndView mav = new ModelAndView("settings");
        Vendor vendor = vendorService.findByEmail(email);
        mav.addObject("vendor", vendor);
        return mav;
    }

    @PostMapping("/update")
    public ModelAndView updateVendor(@RequestParam("email") String email,
                                     @RequestParam("name") String name,
                                     @RequestParam("phone") String phone,
                                     @RequestParam("address") String address) {
        ModelAndView mav = new ModelAndView("settings");
        Vendor vendor = vendorService.findByEmail(email);
        if (vendor != null) {
            vendor.setName(name);
            vendor.setPhone(phone);
            vendor.setAddress(address);
            vendorService.save(vendor);
            mav.addObject("successMessage", "Details updated successfully.");
        } else {
            mav.addObject("errorMessage", "Vendor not found.");
        }
        mav.addObject("vendor", vendor);
        return mav;
    }

    @PostMapping("/changePassword")
    public ModelAndView changePassword(@RequestParam("email") String email,
                                       @RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword) {
        ModelAndView mav = new ModelAndView("settings");
        Vendor vendor = vendorService.findByEmail(email);
        if (vendor != null && vendor.getPassword().equals(oldPassword)) {
            vendor.setPassword(newPassword);
            vendorService.save(vendor);
            mav.addObject("successMessage", "Password changed successfully.");
        } else {
            mav.addObject("errorMessage", "Incorrect old password.");
        }
        mav.addObject("vendor", vendor);
        return mav;
    }
}