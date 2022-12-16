package com.pac.service;

import com.pac.excpetion.IdCardException;
import com.pac.excpetion.UserException;
import com.pac.model.IdCard;


public interface IdCardService {
	
	//key to check for session
	public IdCard getPanCardByNumber(String panNo, String key) throws IdCardException, UserException;

	public IdCard getAdharCardByNo(String aadharNo, String key) throws IdCardException, UserException;

	public IdCard addIdCard(IdCard idCard, String key) throws IdCardException, UserException;
}
