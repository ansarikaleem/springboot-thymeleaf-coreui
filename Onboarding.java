package com.ahliunited.branch.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ahliunited.branch.domain.onboarding.OnBoarding;
import com.ahliunited.branch.service.OnBoardingService;

@Controller
@RequestMapping(value = "pages")
public class OnboardingController {
	
	@Autowired
    private OnBoardingService onBoardingService;
	

    @GetMapping(value = "onboarding")
    public ModelMap getOnboardingForm(Model model) {
    	
    	OnBoarding onBoarding = new OnBoarding();
    	model.addAttribute("onBoarding", onBoarding);
    	
        model.addAttribute("branchName", "Head Office");
        model.addAttribute("userName", "Mohammad Kaleem");
                
    	
        return new ModelMap();
    }
    
    @ModelAttribute("customerSegmentMap")
    public Map<String, String> getCustomerSegment() {
       return onBoardingService.getCustomerSegment();
    }
    
    @ModelAttribute("residencyTypeMap")
    public Map<String, String> getResidencyType(){
    	return onBoardingService.getResidencyType();
    }
    
    
    @ModelAttribute("customerTypeMap")
    public Map<String, String> getCustomerType() {
       return onBoardingService.getCustomerType();
    }
    
    @ModelAttribute("countriesMap")
    public Map<String, String> getCountries(){
    	return onBoardingService.getCountries();
    }
    
    @PostMapping("/onboarding")
    public String submitOnboardingForm(@ModelAttribute("onBoarding") OnBoarding onBoarding) {
        System.out.println("Customer Type is: "+onBoarding.getCustomerType());
        return "pages/onboarding-success";
    }
    
    

}
