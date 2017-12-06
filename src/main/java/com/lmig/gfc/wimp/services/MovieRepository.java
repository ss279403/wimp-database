package com.lmig.gfc.wimp.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmig.gfc.wimp.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
