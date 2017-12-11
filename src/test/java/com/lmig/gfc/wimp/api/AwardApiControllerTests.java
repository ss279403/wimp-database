package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Award;
import com.lmig.gfc.wimp.services.ActorRepository;
import com.lmig.gfc.wimp.services.AwardRepository;

public class AwardApiControllerTests {

	private AwardApiController controller;
	private AwardRepository awards;
	private ActorRepository actors;

	@Before
	public void setUp() {

		awards = mock(AwardRepository.class);
		actors = mock(ActorRepository.class);
		controller = new AwardApiController(awards, actors);

	}

	@Test
	public void create_saves_an_award_to_an_actor_and_returns_the_actor() {
		// arrange
		Actor actor = new Actor();
		Award award = new Award();
		when(actors.findOne(0L)).thenReturn(actor);

		// act
		Actor actual = controller.create(0L, award);

		// assert
		assertThat(actual).isSameAs(actor);
		verify(awards).save(award);
		verify(actors).findOne(0L);

	}

}
