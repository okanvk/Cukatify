package com.okanciftci.cukatify.persistence.ontology;

import com.okanciftci.cukatify.entities.ontology.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor,String> { }