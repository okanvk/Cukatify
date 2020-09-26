package com.okanciftci.cukatify.ontology.impl;

import com.okanciftci.cukatify.entities.ontology.Artist;
import com.okanciftci.cukatify.exceptions.ArtistNotFoundException;
import com.okanciftci.cukatify.exceptions.ArtistNotFoundResponse;
import com.okanciftci.cukatify.models.ArtistRelatedThing;
import com.okanciftci.cukatify.ontology.abstr.SparQLArtistKAO;
import lombok.extern.slf4j.Slf4j;
import org.apache.jena.base.Sys;
import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
@Slf4j
public class SparQLArtistKAOImpl implements SparQLArtistKAO {

    @Override
    public Artist findByName(String artistName)  throws ArtistNotFoundException {


        while (true) {
            String queryStr = "prefix dbpedia-owl:<http://dbpedia.org/ontology/>\n" +
                    "\n" +
                    "SELECT ?page ?abstract ?imageUrl ?relatedTopic\n" +
                    "WHERE {\n" +
                    "    <http://dbpedia.org/resource/"+ artistName + "> dbpedia-owl:wikiPageExternalLink ?page.\n" +
                    "    <http://dbpedia.org/resource/"+ artistName + "> dbpedia-owl:abstract?abstract.\n" +
                    "    OPTIONAL { <http://dbpedia.org/resource/"+ artistName + "> dbpedia-owl:thumbnail ?imageUrl. } \n" +
                    "   OPTIONAL { <http://dbpedia.org/resource/"+ artistName + "> dbpedia-owl:associatedBand ?relatedTopic. } \n"  +
                    "\n" +
                    "FILTER (lang(?abstract) = 'en')\n" +

                    "}";


            Query query = QueryFactory.create(queryStr);

            try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
                ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;

                ResultSet rs = qexec.execSelect();
                QuerySolution solution = rs.next();

                Artist artist = new Artist();
                artist.setId(artistName);
                artist.setName(artistName);
                if(solution.contains("page")) {
                    artist.setPage(solution.getResource("page").toString());
                }
                artist.setDescription(solution.getLiteral("abstract").toString());
                if(solution.contains("imageUrl")) {
                    artist.setImageUrl(solution.getResource("imageUrl").toString());
                }
                String artistResource = "";
                if(solution.contains("relatedTopic")) {
                    artistResource = solution.getResource("relatedTopic").toString();
                    addRelatedThing(artistResource,artist);
                    while(rs.hasNext()){
                        artistResource = rs.next().getResource("relatedTopic").toString();
                        addRelatedThing(artistResource,artist);
                    }
                }



                return artist;

            } catch (Exception e) {
                if(artistName.contains("_(singer)")){
                    throw new ArtistNotFoundException("Artist not found");
                }
                artistName += "_(singer)";
                continue;
            }

        }
    }

    private void addRelatedThing(String url,Artist artist){
        String relatedThing = url.split("/")[4];
        String name = relatedThing.replace("_"," ");
        ArtistRelatedThing newThing = new ArtistRelatedThing(name,url,relatedThing);
        if(!artist.getRelatedThingList().contains(newThing)){
            findRelatedThingImage(newThing);
            artist.addThing(newThing);
        }
    }

    private void findRelatedThingImage(ArtistRelatedThing artistRelatedThing){
        String relatedThingImages = "prefix dbpedia-owl:<http://dbpedia.org/ontology/>\n" +
                "\n" +
                "SELECT ?imageUrl\n" +
                "WHERE {\n" +
                "    <http://dbpedia.org/resource/"+ artistRelatedThing.getResource() + "> dbpedia-owl:thumbnail ?imageUrl. \n"+
                "\n" +
                "}";

        Query query = QueryFactory.create(relatedThingImages);

        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
            ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;

            ResultSet rs = qexec.execSelect();
            QuerySolution solution = rs.next();
            if(solution.contains("imageUrl")) {
                artistRelatedThing.setImage(solution.getResource("imageUrl").toString());
            }


        } catch (Exception e) {
           log.error(e.getMessage());
        }


    }


}
