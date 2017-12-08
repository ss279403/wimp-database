package com.lmig.gfc.wimp.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.services.ActorRepository;
import com.lmig.gfc.wimp.services.MovieRepository;

@RestController
@RequestMapping("api/movies/{movieId}/actors")
public class WimpApiController {

	private MovieRepository movies;
	private ActorRepository actors;

	public WimpApiController(MovieRepository movies, ActorRepository actors) {
		this.movies = movies;
		this.actors = actors;
	}

	@PostMapping("")
	public Movie create(@PathVariable Long movieId, @RequestBody Long actorId) {
		Actor actor = actors.findOne(actorId);
		Movie movie = movies.findOne(movieId);
		movie.getActors().add(actor);
		movies.save(movie);
		return movie;

	}
}
