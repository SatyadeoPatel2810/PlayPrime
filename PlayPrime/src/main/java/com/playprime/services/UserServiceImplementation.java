package com.playprime.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playprime.repository.UserRepository;
import com.playprime.entities.User;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository urepo;

	@Override
	public String addUser(User usr) {
		urepo.save(usr);
		return "user is created";

	}

	@Override
	public boolean emailExist(String email) {
		if (!urepo.findByEmail(email).isPresent()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean checkUser(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		User usr = urepo.findByEmail(email).orElseThrow(() -> new Exception("User not found"));
		String db_password = usr.getPassword();
		if (db_password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<User> viewUser() {
		// TODO Auto-generated method stub
		List<User> userList = urepo.findAll();

		;
		return userList;
	}

	@Override
	public User getUser(String email) throws Exception {
		User user = urepo.findByEmail(email).orElseThrow(() -> new Exception("User not found"));
		return user;
	}

	@Override
	public void updateUser(User us) throws Exception {
		// TODO Auto-generated method stub
		if (urepo.findByEmail(us.getEmail()).isPresent()) {
			User user = urepo.findByEmail(us.getEmail()).get();
			user.setEmail(us.getEmail());
			user.setName(us.getName());
			user.setPhone(us.getPhone());
			user.setPassword(us.getPassword());
			user.setGender(us.getGender());
			user.setAddress(us.getAddress());
			urepo.save(user);
		}

	}

	@Override
	public boolean deleteUser(int id) {

		if (urepo.existsById(id)) {
			urepo.deleteById(id);
			return true;
		} else {

			return false;
		}
	}

}
