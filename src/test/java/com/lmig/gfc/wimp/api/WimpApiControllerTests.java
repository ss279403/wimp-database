package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.services.ActorRepository;
import com.lmig.gfc.wimp.services.MovieRepository;

public class WimpApiControllerTests {

	private WimpApiController controller;
	private MovieRepository movies;
	private ActorRepository actors;

	@Before
	public void setUp() {
		movies = mock(MovieRepository.class);
		actors = mock(ActorRepository.class);
		controller = new WimpApiController(movies, actors);
	}

	@Test
	public void create_saves_a_movie_and_adds_an_actor_if_not_already_listed_and_returns_movie() {
		// arrange
		Movie movie = new Movie();
		movie.setActors(new ArrayList<Actor>());
		Actor actor = new Actor();
		when(movies.findOne(0L)).thenReturn(movie);
		when(actors.findOne(1L)).thenReturn(actor);
		// act
		Movie actual = controller.create(0L, 1L);
		// assert
		assertThat(actual).isSameAs(movie);
		verify(movies).save(movie);
		assertThat(movie.getActors()).contains(actor);
		verify(movies).findOne(0L);
		verify(actors).findOne(1L);
	}

	@Test
	public void create_doesNot_add_actor_when_already_listed_on_movie_then_retuns_movie() {
		// arrange
		Actor actor = new Actor();
		ArrayList<Actor> stars = new ArrayList<Actor>();
		stars.add(actor);
		Movie movie = new Movie();
		movie.setActors(stars);
		when(movies.findOne(0L)).thenReturn(movie);
		when(actors.findOne(1L)).thenReturn(actor);

		// act
		Movie actual = controller.create(0L, 1L);

		// assert
		verify(movies).findOne(0L);
		verify(actors).findOne(1L);
		assertThat(actual).isSameAs(movie);
		verify(movies, never()).save(movie);
		assertThat(movie.getActors()).hasSize(1);
	}

}
