package com.playprime.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.playprime.entities.Movie;
import com.playprime.entities.User;
import com.playprime.services.MovieService;
import com.playprime.services.UserService;

@Controller
public class MovieController {
	@Autowired
	MovieService movserv;

	@Autowired
	UserService userv;

	@PostMapping("addmovie")
	public String addMovie(@ModelAttribute Movie mov) {
		movserv.addMovie(mov);
		return "addmoviesuccess";

	}

	@GetMapping("viewmovie")
	public String viewMovie(Model model) {
		List<Movie> movieList = movserv.viewMovie();
		model.addAttribute("movie", movieList);
		return "viewmovie";
	}

	@GetMapping("viewmovieuser")
	public String viewMovieUser(Model model) {
		List<Movie> movieList = movserv.viewMovie();
		model.addAttribute("movie", movieList);
		return "viewmovieuser";
	}

	
	@GetMapping("/deleted")
	public String deleteMovie(@RequestParam int id, Model model) {
		if(movserv.deleteMovie(id)) {
			model.addAttribute("deletesuccess","Movie deleted successfully!");
		}else {
			model.addAttribute("deletefailure","Movie not found!");
		}
		return "deleted";
	}

	

	@GetMapping("/searchMovie")
	public String searchMovie(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (movserv.searchMovie(id).isPresent()) {
			Movie mv = movserv.searchMovie(id).get();
			model.addAttribute("Movie", id);
			Movie movieList = movserv.viewSearchMovie(id);
			model.addAttribute("movie", movieList);
			return "ViewSearchMoviehtml";
		} else {
			return "searchMovie";
		}
	}
}
