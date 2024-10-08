package com.playprime.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.playprime.entities.Movie;
import com.playprime.entities.User;
import com.playprime.services.MovieService;
import com.playprime.services.UserService;


import jakarta.servlet.http.HttpSession;
@Controller
public class Usercontroller {
	
	
		@Autowired
		UserService userv;
		@Autowired
		MovieService movserv;
		
		@PostMapping("/register")
	    public String addUser(@ModelAttribute User usr, Model model) {
	        boolean status = userv.emailExist(usr.getEmail());
	        if (status == true) {
	            return "registerFail";
	        } else {
	            userv.addUser(usr);
	            return "registersuccess";
	        }
	          
	    }

		
		@PostMapping("/login")
		public String validateUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
		    try {
		        boolean loginStatus = userv.checkUser(email, password);
		        if (loginStatus) {
		            
		            session.setAttribute("email", email);
		            
		            
		            if (email.equals("admin@gmail.com")) {
		                return "adminHome";
		            } else {
		                return "home"; 
		            }
		        } else {
		            
		            
		            return "login"; 
		        }
		    } catch (Exception e) {
		        
		       
		        return "login";
		    }
		}

		
		@GetMapping("viewuser")
		public String viewUser(Model model) {
			List<User> userList=userv.viewUser();
			model.addAttribute("user", userList);
			return "viewuser";
			
		}

		@GetMapping("exploremovies")
		public String exploreMovie(Model model, HttpSession session) throws Exception {
			
			
			String email = (String)session.getAttribute("email");

			User usr=userv.getUser(email);
			boolean status = usr.isPremium();
			
			if(status == true)
			{
				List<Movie> movieList = movserv.viewMovie();
				model.addAttribute("movie", movieList);
				return "viewmovieuser";
			}
			else
			{
				return "payement";
			}
		}
		
		@PostMapping("/update")
		public String updateProfile(@ModelAttribute User us ) throws Exception {
			userv.updateUser(us);
			return "home";
		}
		@GetMapping("logout")
		public String logout(HttpSession session) {
			session.invalidate();
			
			return "login";
			
		}
		
		@GetMapping("/deleteUser")
		public String deleteUser(@RequestParam int id, Model model) {
			if(userv.deleteUser(id)) {
				model.addAttribute("deletesuccess","User deleted successfully!");
			}else {
				model.addAttribute("deletefailure","User not found!");
			}
			return "deleteUser";
		}
		
	}

