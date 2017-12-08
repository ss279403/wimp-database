package com.lmig.gfc.wimp.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Award;
import com.lmig.gfc.wimp.services.ActorRepository;
import com.lmig.gfc.wimp.services.AwardRepository;

@RestController
@RequestMapping("/api/actors/{actorId}/awards")
public class AwardApiController {

	private AwardRepository awards;
	private ActorRepository actors;

	public AwardApiController(AwardRepository awards, ActorRepository actors) {
		this.awards = awards;
		this.actors = actors;
	}

	@PostMapping("")
	public Actor create(@PathVariable Long actorId, @RequestBody Award award) {
		Actor actor = actors.findOne(actorId);
		if (!actor.getAwards().contains(award)) {
			actor.getAwards().add(award);
			award.setActor(actor);
			awards.save(award);
		}
		return actor;
	}
}
