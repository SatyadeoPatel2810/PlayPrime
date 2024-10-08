package com.playprime.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.playprime.entities.Movie;

import com.playprime.repository.MovieRepository;
import com.playprime.services.MovieService;

@Service
public class MovieServiceImplementation implements MovieService {
	@Autowired
	MovieRepository movrepo;

	@Override
	public String addMovie(Movie mov) {

		movrepo.save(mov);
		return "movie is added";
	}

	@Override
	public List<Movie> viewMovie() {
		List<Movie> movieList = movrepo.findAll();
		;
		return movieList;
	}

	@Override
	public Movie updateMovie(Movie mov, int id) {
		// Retrieve the movie from the database or storage using the given id
		Movie movieToUpdate = movrepo.findById(id).get();

		if (movieToUpdate != null) {
			// Update the fields of the movieToUpdate object with the fields from mov
			movieToUpdate.setName(mov.getName());
			movieToUpdate.setLink(mov.getLink());
			movieToUpdate.setGenre(mov.getGenre());
			movieToUpdate.setCast(mov.getCast());
			movieToUpdate.setDirector(mov.getDirector());
			// No need to update id as it's the identifier

			// Save the updated movie back to the database or storage
			movrepo.save(movieToUpdate);
		}

		return movieToUpdate;
	}

	@Override
	public Optional<Movie> searchMovie(int id) {
		// TODO Auto-generated method stub
		return movrepo.findById(id);

	}

	@Override
	public boolean deleteMovie(int id) {

		if (movrepo.existsById(id)) {
			movrepo.deleteById(id);
			return true;
		} else {

			return false;
		}
	}

	@Override
	public Movie viewSearchMovie(int id) {
		Optional<Movie> movieSearch = movrepo.findById(id);

		return movieSearch.get();

	}
}