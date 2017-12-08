package com.lmig.gfc.wimp.api;

import java.util.ArrayList;
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

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.services.ActorRepository;

@RestController
@RequestMapping("/api/actors")
public class ActorApiController {
	
	private ActorRepository actors;
	
	public ActorApiController(ActorRepository actors) {
		this.actors = actors;

	}
	
	@GetMapping("")
	public List<Actor> getAll() {
		return actors.findAll();
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Actor create(@RequestBody Actor actor) {
		return actors.save(actor);
	}

	@GetMapping("{id}")
	public List<ActorView> getOne(@PathVariable Long id) {
		List<Actor> stars = actors.findAll();
		ArrayList<ActorView> actorViews = new ArrayList<ActorView>();
			for (Actor actor : stars) {
				actorViews.add(new ActorView(actor));
				}
			return actorViews;
	}

	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable Long id) {
		actor.setId(id);
		return actors.save(actor);
	}

	@DeleteMapping("{id}")
	public Actor delete(@PathVariable Long id) {
		// Get the actor from the database so I can return it later.
			Actor actor = actors.findOne(id);
		// Delete the dog from the database (in this case, just set the value in the
		// array list to null).
			actors.delete(id);

		// return the dog I just deleted.
			return actor;
	}

}
