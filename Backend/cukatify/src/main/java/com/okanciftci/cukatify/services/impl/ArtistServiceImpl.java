package com.okanciftci.cukatify.services.impl;

import com.okanciftci.cukatify.common.ResponseEnum;
import com.okanciftci.cukatify.common.ResponsePayload;
import com.okanciftci.cukatify.entities.ontology.Artist;
import com.okanciftci.cukatify.ontology.abstr.SparQLArtistKAO;
import com.okanciftci.cukatify.persistence.redis.ActorRepository;
import com.okanciftci.cukatify.services.abstr.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private SparQLArtistKAO sparQLArtistKAO;

    @Override
    public Artist bringArtist(String name) {

        Artist redisArtist = this.actorRepository.findById(name).orElse(null);
        if(redisArtist != null){
            return redisArtist;
        }else{
            Artist actor = this.sparQLArtistKAO.findByName(name);
            if(actor != null) {
                this.actorRepository.save(actor);
            }
            return actor;
        }

    }
}
