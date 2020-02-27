package com.okanciftci.cukatify.controllers;

import com.okanciftci.cukatify.common.ResponseEnum;
import com.okanciftci.cukatify.common.ResponsePayload;
import com.okanciftci.cukatify.entities.mongo.Category;
import com.okanciftci.cukatify.entities.ontology.Artist;
import com.okanciftci.cukatify.services.abstr.ArtistService;
import com.okanciftci.cukatify.services.impl.ArtistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @RequestMapping(value = "/findArtistByName/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> findArtistByName (@PathVariable String name) {

        Artist artist = artistService.bringArtist(name);
        return new ResponseEntity(artist, HttpStatus.OK);

    }

}
