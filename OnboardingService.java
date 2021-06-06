package com.ahliunited.branch.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;


@Service
public class OnBoardingService {
	
	public Map<String, String> getCustomerType(){
		
		Map<String, String> customerTypeMap = new HashMap<String, String>();
		customerTypeMap.put("11", "INDIVIDUAL");
		customerTypeMap.put("2", "NON-INDIVIDUAL");
	    return customerTypeMap;
	}
	
	public Map<String, String> getCountries(){
		
		Map<String, String> countryMap = new HashMap<String, String>();
        countryMap.put("1", "Kuwait");
        countryMap.put("2", "India");
        return countryMap;
		
	}

	public Map<String, String> getCustomerSegment() {
		
		Map<String, String> customerSegmentMap = new HashMap<String, String>();
		customerSegmentMap.put("11", "NORMAL CUSTOMER");
		customerSegmentMap.put("2", "VIP");
	    return customerSegmentMap;
	}

	public Map<String, String> getResidencyType() {
		
		Map<String, String> residencyTypeMap = new HashMap<String, String>();
		residencyTypeMap.put("1", "KUWAIT");
		residencyTypeMap.put("2", "USA");
	    return residencyTypeMap;
	}

}
