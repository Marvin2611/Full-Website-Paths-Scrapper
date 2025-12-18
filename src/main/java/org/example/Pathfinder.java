package org.example;

import org.jsoup.nodes.Document;
import website.scrapper.Scrapper;

import java.util.*;

public class Pathfinder {
    public Set<String> links = new HashSet<>();

    //Traverses a Website using only the root exposed links
    public void TraverseSiteUsingSet(){
        Set<String> counter = new HashSet<>(links);
        System.out.println("The list used: " + counter);

        for (String count: counter){
            List<String> newList = GetLinksFromURL(count);
            AddNewFoundLinksToSet(newList);
        }
    }


    public List<String> GetLinksFromURL(String url){

        Document doc = Scrapper.TryGetDocument(url);
        return Scrapper.GetAbsoluteLinks(doc);
    }

    public void AddNewFoundLinksToSet(List<String> list){
        for (String link: list){
            if(Scrapper.ValidateTheURL(link)){
                links.add(link);
                System.out.println("New Links List is size: " + links.size());
            }
        }
    }
}
