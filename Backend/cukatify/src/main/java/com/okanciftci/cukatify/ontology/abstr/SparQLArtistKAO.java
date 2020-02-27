package com.okanciftci.cukatify.ontology.abstr;

import com.okanciftci.cukatify.entities.ontology.Artist;
import com.okanciftci.cukatify.models.ArtistRelatedThing;

public interface SparQLArtistKAO {

    Artist findByName(String name);


}
