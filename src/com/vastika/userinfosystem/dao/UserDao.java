package com.vastika.userinfosystem.dao;

import java.util.List;

import com.vastika.userinfosystem.model.User;



public interface UserDao {
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(int id);
	
	User getUserById(int id);
	
	List<User> getAllUserInfo();
}
