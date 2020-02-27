package com.okanciftci.cukatify.models;

import com.okanciftci.cukatify.entities.ontology.Artist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArtistRelatedThing {

    private String name;
    private String url;

    public ArtistRelatedThing(String name,String url){
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object obj) {
        String name = ((ArtistRelatedThing)obj).name;
        return this.name.equals(name);
    }
}
