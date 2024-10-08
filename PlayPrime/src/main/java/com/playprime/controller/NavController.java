package com.playprime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.playprime.entities.Movie;
@Controller
public class NavController {

	@GetMapping("map-register")
    public String mapRegister() {
        
        return "register";
    }

    @GetMapping("map-login")
    public String showLogin() {
        
        return "login";
    }


    @GetMapping("/map-addmovie")
    public String mapAddMovie() {
        return "addmovie";
    }
    
    @GetMapping("openUpdatePage")
	public String openUpdateStudentPage() {
		return "myProfile";
	}
	
    @GetMapping("/openDeletePage")
    public String deletemovie() {
        return "deleted";
    }
    
    @GetMapping("openSearchMovie")
	public String openSearchStudent() {
		return "searchMovie";
	}
    @GetMapping("/AdminHome")
    public String adminHome(Model model) {
        return "AdminHome"; 
    }

    @GetMapping("/openDeleteUser")
    public String openDeleteUser() {
        return "deleteUser";
    }
}


