package com.pac.service;

import com.pac.excpetion.UserException;
import com.pac.model.User;

public interface UserService {

	public User createUser(User user) throws UserException;

	public User updateUser(User user, String key) throws UserException;
}
