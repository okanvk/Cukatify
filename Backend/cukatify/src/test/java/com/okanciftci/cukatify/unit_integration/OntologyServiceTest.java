package com.okanciftci.cukatify.unit_integration;

import com.okanciftci.cukatify.entities.ontology.Artist;
import com.okanciftci.cukatify.ontology.abstr.SparQLArtistKAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OntologyServiceTest {

    @Autowired
    private SparQLArtistKAO sparQLArtistKAO;

    @Test
    void findResourceGivenName(){

        // given
        String name = "Linkin_Park";

        // when
        Artist artist = sparQLArtistKAO.findByName(name);

        // then
        Assertions.assertEquals(artist.getName(),"Linkin_Park");
        Assertions.assertEquals(artist.getRelatedThingList().get(0).getName(),"Steve Aoki");
        Assertions.assertEquals(artist.getRelatedThingList().size(),2);

    }
}
