package com.playprime.services;

import java.util.List;


import com.playprime.entities.User;


public interface UserService {

	public String addUser(User user);
	public boolean emailExist(String email);
	public List<User> viewUser();
	public boolean checkUser(String email, String password) throws Exception;
	public User getUser(String email) throws Exception;
	public void updateUser(User us) throws Exception;
	
	boolean deleteUser(int id);
	

}
