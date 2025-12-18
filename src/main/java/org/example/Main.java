package org.example;

import org.jsoup.nodes.Document;
import website.scrapper.Scrapper;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Document doc = Scrapper.TryGetDocument("https://books.toscrape.com/index.html");

        List<String> fullLinkList = Scrapper.GetAbsoluteLinks(doc);

        //Remove all the double entries from the list
        Set<String> set = new HashSet<>();
        for (String link: fullLinkList){
            if(Scrapper.ValidateTheURL(link)){
                set.add(link);
            }
        }
        System.out.println("Link list size is: " + set.size());

        Pathfinder pathfinder = new Pathfinder();
        pathfinder.links = set;
        pathfinder.TraverseSiteUsingSet();
        System.out.println("The finished list looks like this so far: " + pathfinder.links);

        /*
        //Can be changed, depending on the site that needs to be loaded
        String siteTrunk = "w3schools.com";
        String query = String.format("a[href*=%s]", siteTrunk);

        //Only get the links that include the siteTrunk
        //IMPORTANT: Missing the relative paths!
        Elements linkTagsFull = doc.select(query);

        //Turn all the jsoup elements href into a string list
        List<String> linkTexts = new ArrayList<String>();
        for (Element element: linkTagsFull){
            String link = element.text();
            linkTexts.add(link);
            //System.out.println("Links Full: " + linkTexts.getLast());
        }

        System.out.println("List length: " + linkTexts.size()); */
    }
}