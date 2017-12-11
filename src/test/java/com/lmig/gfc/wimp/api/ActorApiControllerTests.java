package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.services.ActorRepository;

public class ActorApiControllerTests {

	private ActorApiController controller;
	private ActorRepository repo;

	@Before
	public void setUp() {
		repo = mock(ActorRepository.class);
		controller = new ActorApiController(repo);
	}

	@Test
	public void getAll_actors_from_a_list_then_returns_the_list() {
		// arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		when(repo.findAll()).thenReturn(actors);
		// act
		List<Actor> actual = controller.getAll();
		// assert
		assertThat(actual).isSameAs(actors);
		verify(repo).findAll();
	}

	@Test
	public void create_saves_an_actor_then_returns_it() {
		// arrange
		Actor actor = new Actor();
		when(repo.save(actor)).thenReturn(actor);

		// act
		Actor actual = controller.create(actor);

		// assert
		assertThat(actual).isSameAs(actor);
		verify(repo).save(actor);
	}

	@Test
	public void getOne_finds_and_actor_from_actorView_at_a_valid_id_then_returns_that_actor() {
		// arrange
		Actor actor = new Actor();
		actor.setId(0L);
		when(repo.findOne(0L)).thenReturn(actor);
		// act
		ActorView actual = controller.getOne(0L);

		// assert
		assertThat(actual.getId()).isEqualTo(0L);
		verify(repo).findOne(0L);

	}

	@Test
	public void update_sets_actors_id_then_returns_the_actor() {
		// arrange
		Actor actor = new Actor();
		when(repo.save(actor)).thenReturn(actor);
		// act
		Actor actual = controller.update(actor, 0L);
		// assert
		assertThat(actual).isSameAs(actor);
		verify(repo).save(actor);
		assertThat(actor.getId()).isEqualTo(0L);
	}

	@Test
	public void delete_an_actor_from_id_and_return_it() {
		// arrange
		Actor actor = new Actor();
		when(repo.findOne(0L)).thenReturn(actor);
		// act
		Actor actual = controller.delete(0L);
		// assert
		assertThat(actual).isSameAs(actor);
		verify(repo).delete(0L);
		verify(repo).findOne(0L);
	}

}
