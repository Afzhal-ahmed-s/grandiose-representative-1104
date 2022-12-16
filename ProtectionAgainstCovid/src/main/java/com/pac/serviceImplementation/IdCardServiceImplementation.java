package com.pac.serviceImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.IdCardDao;
import com.pac.dao.UserSessionDao;
import com.pac.excpetion.IdCardException;
import com.pac.excpetion.UserException;
import com.pac.model.CurrentUserSession;
import com.pac.model.IdCard;
import com.pac.service.IdCardService;

@Service
public class IdCardServiceImplementation implements IdCardService{

	@Autowired
	private IdCardDao idCardDao;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
	@Override
	public IdCard getPanCardByNumber(String panNo, String key) throws IdCardException, UserException {

		CurrentUserSession currentUserSession = userSessionDao.findByUniqueUserId(key);
		
		if(currentUserSession != null) {
			IdCard idCard = idCardDao.getIdCardByPanNo(panNo);
			if(idCard != null) {
				return idCard;
			}
			else throw new IdCardException("No such User with given pan number present.");
		}
		else throw new UserException("No such User logged-in. Please login to use this feature.");

	}

	@Override
	public IdCard getAdharCardByNo(String aadharNo, String key) throws IdCardException, UserException {

		CurrentUserSession currentUserSession = userSessionDao.findByUniqueUserId(key);
		
		if(currentUserSession != null) {
			IdCard idCard = idCardDao.getIdCardByAadharNo(aadharNo);
			if(idCard != null) {
				return idCard;
			}
			else throw new IdCardException("No such User with given aadhar number present.");
		}
		else throw new UserException("No such User logged-in. Please login to use this feature.");

	}

	@Override
	public IdCard addIdCard(IdCard idCard, String key) throws IdCardException, UserException {

		CurrentUserSession currentUserSession = userSessionDao.findByUniqueUserId(key);
		
		if(currentUserSession != null) {
			Optional<IdCard> iCard = idCardDao.findById(idCard.getId());
			
			if(iCard.isEmpty()) {
				return idCardDao.save(idCard);
			}
			else throw new IdCardException("An ID card exists under a user. Please give valid input details.");
		}
		else throw new UserException("No such User logged-in. Please login to use this feature.");

	}

}
