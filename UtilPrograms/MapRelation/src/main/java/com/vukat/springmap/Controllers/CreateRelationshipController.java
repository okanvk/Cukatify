package com.vukat.springmap.Controllers;

import com.vukat.springmap.Services.RsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/artists")
public class CreateRelationshipController {

    @Autowired
    private RsService rsService;

    @RequestMapping(value = "/createMapToDb/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findArtistByName (@PathVariable String id) {

        rsService.createRsByCategory(id);
        return new ResponseEntity(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/createRatings/{type}", method = RequestMethod.GET)
    public ResponseEntity<?> createRatings (@PathVariable int type) {

        rsService.createRatings(type);
        return new ResponseEntity(null, HttpStatus.OK);

    }

}
