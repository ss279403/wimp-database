package com.lmig.gfc.wimp.config;

import org.springframework.context.annotation.Configuration;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Award;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.services.ActorRepository;
import com.lmig.gfc.wimp.services.AwardRepository;
import com.lmig.gfc.wimp.services.MovieRepository;

@Configuration
public class SeedData {

	SeedData(ActorRepository actors, MovieRepository movies, AwardRepository awards) {
		movies.save(new Movie("TopGun", null, 30000000L, "Universal"));
		movies.save(new Movie("I Lost My Shoe", null, 450000L, "Universal"));
		actors.save(new Actor("Denzel", "Washington", 1987L, null));
		actors.save(new Actor("Brian", "Blake", 2002L, null));
		awards.save(new Award("Best Supporting Actor", "OSCARS", 2003));
 
	}
}
