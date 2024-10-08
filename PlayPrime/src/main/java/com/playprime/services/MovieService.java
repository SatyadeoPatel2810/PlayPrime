package com.playprime.services;

import java.util.List;
import java.util.Optional;

import com.playprime.entities.Movie;


public interface MovieService  {
	public String addMovie(Movie mov);
	
	public List<Movie> viewMovie();
	
	public Movie  viewSearchMovie(int id);

	Optional<Movie> searchMovie(int id);
 
	
	

	 
	   public Movie updateMovie(Movie mov,int id);

	public boolean deleteMovie(int id);

	
	

	

	
	 
	
	    

		
	}
	
	


