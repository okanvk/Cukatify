package com.okanciftci.cukatify.ontology.impl;

import com.okanciftci.cukatify.entities.ontology.Artist;
import com.okanciftci.cukatify.ontology.abstr.SparQLArtistKAO;
import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.springframework.stereotype.Repository;

@Repository
public class SparQLArtistKAOImpl implements SparQLArtistKAO {

    @Override
    public Artist findByName(String artistName) {

        String queryStr = "prefix dbpedia-owl:<http://dbpedia.org/ontology/>\n" +
                "\n" +
                "SELECT ?page ?abstract ?imageUrl\n" +
                "WHERE {\n" +
                "    <http://dbpedia.org/resource/"+ artistName + "> dbpedia-owl:wikiPageExternalLink ?page.\n" +
                "    <http://dbpedia.org/resource/"+ artistName + "> dbpedia-owl:abstract?abstract.\n" +
                "    <http://dbpedia.org/resource/"+ artistName + "> dbpedia-owl:thumbnail ?imageUrl\n" +
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
            artist.setPage(solution.getResource("page").toString());
            artist.setDescription(solution.getLiteral("abstract").toString());
            artist.setImageUrl(solution.getResource("imageUrl").toString());

            return artist;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
