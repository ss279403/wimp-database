package com.lmig.gfc.wimp.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.services.MovieRepository;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {

	private MovieRepository movies;

	public MovieApiController(MovieRepository movies) {
		this.movies = movies;
	}

	@GetMapping("")
	public List<Movie> getAll() {
		return movies.findAll();
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Movie create(@RequestBody Movie movie) {
		return movies.save(movie);
	}

	@GetMapping("{id}")
	public Movie getOne(@PathVariable Long id) {
		return movies.findOne(id);
	}

	@PutMapping("{id}")
	public Movie update(@RequestBody Movie movie, @PathVariable Long id) {
		movie.setId(id);
		return movies.save(movie);
	}

	@DeleteMapping("{id}")
	public Movie delete(@PathVariable Long id) {
		// Get the movie from the database so I can return it later.
		Movie movie = movies.findOne(id);
		// Delete the dog from the database (in this case, just set the value in the
		// array list to null).
		movies.delete(id);

		// return the dog I just deleted.
		return movie;
	}

}
