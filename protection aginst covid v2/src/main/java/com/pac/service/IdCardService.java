package com.pac.service;

import com.pac.exception.IdCardException;
import com.pac.exception.UserException;
import com.pac.model.Idcard;

public interface IdCardService {

	public Idcard getPanCardByNumber(String panNo, String key) throws IdCardException, UserException;

	public Idcard getAdharCardByNo(Long adharNo, String key) throws IdCardException, UserException;

	public Idcard addIdCard(Idcard idCard, String key) throws IdCardException, UserException;
}
