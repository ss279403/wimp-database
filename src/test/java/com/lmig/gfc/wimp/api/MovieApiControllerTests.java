package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.services.MovieRepository;

public class MovieApiControllerTests {

	private MovieApiController controller;
	private MovieRepository repo;

	@Before
	public void setUp() {
		repo = mock(MovieRepository.class);
		controller = new MovieApiController(repo);
	}

	@Test
	public void getAll_movies_from_a_list_then_returns_the_list() {
		// arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		when(repo.findAll()).thenReturn(movies);
		// act
		List<Movie> actual = controller.getAll();
		// assert
		assertThat(actual).isSameAs(movies);
		verify(repo).findAll();
	}

	@Test
	public void create_saves_a_movie_then_returns_it() {
		// arrange
		Movie movie = new Movie();
		when(repo.save(movie)).thenReturn(movie);
		// act
		Movie actual = controller.create(movie);
		// assert
		assertThat(actual).isSameAs(movie);
		verify(repo).save(movie);
	}

	@Test
	public void getOne_gets_one_movie_from_a_valid_id_and_returns_it() {
		// arrange
		Movie movie = new Movie();
		when(repo.findOne(0L)).thenReturn(movie);
		// act
		Movie actual = controller.getOne(0L);

		// assert
		assertThat(actual).isSameAs(movie);
		verify(repo).findOne(0L);
	}

	@Test
	public void getOne_gets_one_movie_from_an_invalid_id_and_returns_it() {
		// arrange

		// act
		Movie actual = controller.getOne(0L);

		// assert
		assertThat(actual).isNull();
		verify(repo).findOne(0L);
	}

	@Test
	public void update_sets_the_id_of_a_movie_and_returns_it_after_saving_it() {
		// arrange
		Movie movie = new Movie();
		when(repo.save(movie)).thenReturn(movie);
		// act
		Movie actual = controller.update(movie, 0L);

		// assert
		assertThat(actual).isSameAs(movie);
		verify(repo).save(movie);
		assertThat(movie.getId()).isEqualTo(0L);
	}

	@Test
	public void delete_a_dog_from_a_specific_id_and_returns_it() {
		// arrange
		Movie movie = new Movie();
		when(repo.findOne(0L)).thenReturn(movie);
		// act
		Movie actual = controller.delete(0L);

		// assert
		assertThat(actual).isSameAs(movie);
		verify(repo).findOne(0L);
		verify(repo).delete(0L);
	}

}
