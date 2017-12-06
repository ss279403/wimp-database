package com.lmig.gfc.wimp.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmig.gfc.wimp.models.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

}
