package ak.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ak.entity.Vendor;
import ak.repository.VendorRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private VendorRepository repository;


    @GetMapping
    public String homepage(Model model) {
        model.addAttribute("vendors", repository.findAll());
        return "home"; 
    }

 
    @GetMapping("/vendors")
    @ResponseBody
    public List<Vendor> getAllVendors(
        @RequestParam(required = false) String search,  
        @RequestParam(required = false) String sortBy  
    ) {
        List<Vendor> vendors = repository.findAll();

        if (search != null) {
            vendors = repository.findByNameContainingOrEmailContainingOrPhoneContainingOrAddressContaining(
                search, search, search, search
            );
        }

        if (sortBy != null) {
            switch (sortBy) {
                case "name":
                    vendors.sort(Comparator.comparing(Vendor::getName));
                    break;
                case "email":
                    vendors.sort(Comparator.comparing(Vendor::getEmail));
                    break;
                case "phone":
                    vendors.sort(Comparator.comparing(Vendor::getPhone));
                    break;
                case "address":
                    vendors.sort(Comparator.comparing(Vendor::getAddress));
                    break;
                default:
                    vendors.sort(Comparator.comparing(Vendor::getId)); // Default sort by ID
                    break;
            }
        }

        return vendors;
    }

}
