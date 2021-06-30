package com.ahliunited.branch.domain;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ahliunited.branch.ws.custdetails.types.response.CustomerDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data
public class OnBoarding implements Serializable{
	
	private static final long serialVersionUID = -2688313271552697799L;
	
	private String referenceNumber;
	private String customerType;
	private String customerSubType;
	private String customerStatus;
	private String accountType;
	private String residencyType;
	private String customerSegment;
	private String accountCurreny;
	@NotEmpty(message = "{onboarding.language.error}")
	private String customerLanguage;
	private String accountOpenReason;
	@NotEmpty(message = "{onboarding.mobile.length}")
	private String mobileNumber;
	@NotEmpty(message = "{onboarding.email.length}")
	private String emailAddress;
	
	@NotEmpty @Size(min = 12, message = "{onboarding.civilId.length}")
	private String civilIdNumber;
	private String documentNumber;
	private String customerBranch;
	
	
	private String civilId;
	private String civilIdExpiry;
	private String firstNameEn;
	private String lastNameEn;
	private String firstNameAr;
	private String lastNameAr;
	private String dateOfBirth;
	private String age;
	private String gender;
	private String nationality;
	private String passportNo;
	
	private String addressArArea;
	private String addressArBlock;
	private String addressArJada;
	private String addressArBuilding;
	private String addressArApartment;
	private String addressArStreet;
	private String addressArZipCode;
	
	private String addressEnArea;
	private String addressEnBlock;
	private String addressEnJada;
	private String addressEnBuilding;
	private String addressEnApartment;
	private String addressEnStreet;
	private String addressEnZipCode;
	
	private String country;
	private String branch;
	private String userType;
	private String unitCode;
	
	private String hasMobileId;
	
	private CustomerDetails existingCustomerDetails;
	
	
}
