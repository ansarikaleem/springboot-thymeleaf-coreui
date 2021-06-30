package com.ahliunited.branch.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahliunited.branch.domain.AccountInfo;
import com.ahliunited.branch.domain.Country;
import com.ahliunited.branch.domain.MIDAuthentication;
import com.ahliunited.branch.domain.OnBoarding;
import com.ahliunited.branch.service.OnBoardingService;
import com.ahliunited.branch.ws.custdetails.types.request.CustDetailsFinRequestBuilder;
import com.ahliunited.branch.ws.midGetAuthResult.request.MidGetAuthResultRequestBuilder;
import com.ahliunited.branch.ws.midavailstatus.request.MidAvailStatusRequestBuilder;
import com.ahliunited.branch.ws.midpush.request.MidPushRequestBuilder;
import com.ahliunited.branch.ws.midqr.request.MidQRRequestBuilder;

@Controller
public class OnboardingController {
	
	@Autowired
	private OnBoardingService onBoardingService;
	@Autowired
	private CustDetailsFinRequestBuilder custDetailsFinRequestBuilder;
	@Autowired 
	private MidAvailStatusRequestBuilder midAvailStatusRequestBuilder;
	@Autowired 
	private MidQRRequestBuilder midQRRequestBuilder;
	@Autowired 
	private MidPushRequestBuilder midPushRequestBuilder;
	@Autowired 
	private MidGetAuthResultRequestBuilder midGetAuthResultRequestBuilder;
	

    @GetMapping(value = "onboarding")
    public ModelAndView getOnboardingForm(ModelAndView  model, OnBoarding onBoarding) {
    	
    	model.addObject("onBoarding", onBoarding);
//    	model.addObject("accountInfo", accountInfo);
    	
        model.addObject("branchName", "Head Office");
        model.addObject("userName", "Mohammad Kaleem");
        model.addObject("lastLoginTime", new Date());
        
        model.setViewName("onboarding");
        return model;
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
    
    @ModelAttribute("customerSubTypeMap")
    public Map<String, String> getCustomerSubType() {
       return onBoardingService.getCustomerType();
    }
    
    @ModelAttribute("customerStatusMap")
    public Map<String, String> getCustomerStatus() {
       return onBoardingService.getCustomerType();
    }
    
    @ModelAttribute("accountTypeMap")
    public Map<String, String> getAccountType() {
       return onBoardingService.getCustomerType();
    }
    
    @ModelAttribute("countriesMap")
    public Map<String, String> getCountries(){
    	return onBoardingService.getCountries();
    }
    
    @ModelAttribute("accountCurrenyMap")
    public Map<String, String> getAccountCurrency(){
    	return onBoardingService.getAccountCurrency();
    }
    
    @ModelAttribute("accountOpenReasonMap")
    public Map<String, String> getAccountOpenReason(){
    	return onBoardingService.getAccountOpenReason();
    }
    
    @ModelAttribute("areaEnMap")
    public Map<String, String> getKuwaitAreaInEnglish(){
    	return onBoardingService.getAreaInEnglish();
    }
    
    @ModelAttribute("areaArMap")
    public Map<String, String> getKuwaitAreaInArabic(){
    	return onBoardingService.getAreaInArabic();
    }
    
    @ModelAttribute("branchMap")
    public Map<String, String> getAUBBranch(){
    	return onBoardingService.getBranches();
    }
    
    
    
    
//    @RequestMapping(value = "/addAuth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody List<MIDAuthentication> getMIDAuth(@RequestBody OnBoarding onBoarding, ModelAndView  model) {
//    	System.out.println("request received for civil Id:"+onBoarding.getCivilIdNumber());
//    	List<MIDAuthentication> authList = onBoardingService.getMIDAuthList(onBoarding.getCivilIdNumber());
//    	return authList;
//    }
    
    @RequestMapping(value = "/addAuth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<MIDAuthentication> getCustomerMIDAuthList(@RequestBody MIDAuthentication midAuthentication) {
    	System.out.println("request received for civil Id:"+midAuthentication.getAuthMode());
    	if("push".equals(midAuthentication.getAuthMode())) {
    		String hasMobileId = "False";//midAvailStatusRequestBuilder.call(civilId);
    		if("False".equals(hasMobileId)) {
    			System.out.println("Customer does not have Kuwait Mobile Id.");
    		}

    	}
    	List<MIDAuthentication> authList = onBoardingService.getMIDAuthList(midAuthentication.getCustomerCivilId());
    	return authList;
    }
    
    @RequestMapping(value = "/deleteAuth/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<MIDAuthentication> deleteMIDAuth(@PathVariable String id) {
    	System.out.println("received a delete request for Id: "+id);
     onBoardingService.deleteAuth(id);
     List<MIDAuthentication> authList = onBoardingService.getMIDAuthList("");
     
     return authList;
    }
    
    
    
    @GetMapping("/mobileId/qr")
    public ResponseEntity<byte[]> getQrCode() {
    	
    	String qrCode = "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";
    	String qrCodeId="";
    	try {
//    		MidQRResponse  response = midQRRequestBuilder.call();
//    		qrCode = response.getQrCode();
//    		qrCodeId = response.getQrCodeId();
		} catch (Exception e) {
			e.printStackTrace();
		}

      return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCode.getBytes());
    }
    
    
    
    @RequestMapping(value = "/newApplication", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String newApplication(@RequestBody AccountInfo accountInfo) {
    	System.out.println("currency id:"+accountInfo.getAccountCurreny()+ " and mobile number ="+accountInfo.getMobileNumber());
    	String applicationRefNumber="34124324134";
    	return applicationRefNumber;
    }
    
//	@PostMapping("/newAccount")
//	public ModelAndView newAccountApplication(@Valid @ModelAttribute("onBoarding") OnBoarding onBoarding, final BindingResult bindingResult, ModelAndView model, Errors errors) {
//		
//		System.out.println("Customer Type is: " + onBoarding.getCustomerType());
//		
//		if (null != errors && errors.getErrorCount() > 0 || bindingResult.hasErrors()) {
//			model.addObject("hasError", true);
//		}else {
//			model.addObject("onBoarding", onBoarding);
//		}
//		
//		return model;
//	}
    
    @PostMapping("/onboarding")
    public ModelAndView submitOnboardingForm(@Valid @ModelAttribute("onBoarding") OnBoarding onBoarding, final BindingResult bindingResult, ModelAndView  model, Errors errors, RedirectAttributes redirectAttributes) {
        System.out.println("Reference Number is: "+onBoarding.getReferenceNumber());
        	if (null != errors && errors.getErrorCount() > 0) {
        	model.addObject("hasError", true);
        	model.setViewName("content");
            return model;
        }
        String civilId = onBoarding.getCivilIdNumber();
        try {
			System.out.println("civil Id value is "+civilId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
        redirectAttributes.addAttribute("referenceNumber", "34124324134");
        model.setViewName("redirect:/kyc");
        return model;
    }
      
    

}
