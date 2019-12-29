package com.okanciftci.cukatify.ontology.abstr;

import com.okanciftci.cukatify.entities.ontology.Artist;

public interface SparQLArtistKAO {

    Artist findByName(String name);

}
