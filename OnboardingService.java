package com.ahliunited.branch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ahliunited.branch.domain.MIDAuthentication;


@Service
public class OnBoardingService {
	
	List<MIDAuthentication> midAuthList = new ArrayList<MIDAuthentication>();
	
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

	public List<MIDAuthentication> getMIDAuthList(String civilIdNumber) {
		
			MIDAuthentication midAuthentication = new MIDAuthentication();
			
			midAuthentication.setCustomerCivilId("28707050795");
			midAuthentication.setCustomerName("Mohammad Kaleem Ansari");
			midAuthentication.setCivilIdExpiryDate("2022-08-15");
			midAuthentication.setIsAuthenticated("true");
			
			midAuthList.add(midAuthentication);
		
		return midAuthList;
	}

	public void deleteAuth(String id) {
		
		midAuthList.remove(id);
	}

	public Map<String, String> getAreaInEnglish() {
		
		Map<String, String> areaMap = new HashMap<String, String>();
		areaMap.put("Kuwait City", "Kuwait City");
		areaMap.put("Salmiya", "Salmiya");
		areaMap.put("Farwania", "Farwaniya");
		areaMap.put("Hawally", "Hawally");
		return areaMap;
	}
	
	public Map<String, String> getAreaInArabic() {
		
		Map<String, String> areaMap = new HashMap<String, String>();
		areaMap.put("Kuwait City", "Kuwait City");
		areaMap.put("Salmiya", "Salmiya");
		areaMap.put("Farwania", "Farwaniya");
		areaMap.put("Hawally", "Hawally");
		return areaMap;
	}

	public Map<String, String> getAccountCurrency() {
		
		Map<String, String> currencyMap = new HashMap<String, String>();
		currencyMap.put("KD", "Kuwaiti Dinar");
		currencyMap.put("USD", "American Doller");
		return currencyMap;
	}

	public Map<String, String> getAccountOpenReason() {
		
		Map<String, String> reasonMap = new HashMap<String, String>();
		
		reasonMap.put("1", "For Saving");
		return reasonMap;
	}

	public Map<String, String> getBranches() {
		Map<String, String> branchMap = new HashMap<String, String>();
		
		branchMap.put("1", "Head Office");
		return branchMap;
	}

}
