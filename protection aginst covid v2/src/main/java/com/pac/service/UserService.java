package com.pac.service;

import com.pac.exception.UserException;
import com.pac.model.User;

public interface UserService {

	public User createCustomer(User user) throws UserException;

	public User updateCustomer(User user, String key) throws UserException;
}
