package com.ahliunited.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahliunited.domain.Customer;
import com.ahliunited.service.ContactService;

@Controller
public class DashboardController {
	
	@Autowired
    private ContactService contactService;
	
	@GetMapping(value = "/")
	public String index(Model model) {
		
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
    	
        model.addAttribute("branchName", "Head Office");
        model.addAttribute("userName", "Mohammad Kaleem");
		
		
		return "pages/dashboard";
	}
	
	@ModelAttribute("customerSegmentMap")
    public Map<String, String> getCustomerSegment() {
       return contactService.getCustomerSegment();
    }
    
    @ModelAttribute("residencyTypeMap")
    public Map<String, String> getResidencyType(){
    	return contactService.getResidencyType();
    }
    
    
    @ModelAttribute("customerTypeMap")
    public Map<String, String> getCustomerType() {
       return contactService.getCustomerType();
    }
    
    @ModelAttribute("countriesMap")
    public Map<String, String> getCountries(){
    	return contactService.getCountries();
    }
    
    @PostMapping("/onboarding")
    public String submitOnboardingForm(Model model, @Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        System.out.println("Customer Name is: "+customer.getFirstName());
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("hasError", true);
            return "/ :: onboarding-main";
        }
        
        return "pages/form-success";
    }

}
