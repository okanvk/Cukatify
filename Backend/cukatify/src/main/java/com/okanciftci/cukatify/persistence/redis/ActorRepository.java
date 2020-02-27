package com.okanciftci.cukatify.persistence.redis;

import com.okanciftci.cukatify.entities.ontology.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Artist,String> {


}