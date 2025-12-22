package org.model;

import org.jsoup.nodes.Document;
import website.scrapper.Pathfinder;
import website.scrapper.Scrapper;
import website.scrapper.SetURLModificator;
import website.scrapper.URLValidator;

import java.util.*;

//1. Retrieve html document from root url
//2. Get initial set of links from the document
//3. Retrieve every link from the html documents inside the set
//4. Remove unwanted links (social media, third party)
//5. Validate links for security and reachability
//6. Try to place links inside the initial set
//7. Save links list in json/txt file

public class Main {
    public static void main(String[] args) {
        //1.
        Document doc = Scrapper.tryGetDocument("https://books.toscrape.com/index.html?");
        List<String> initialLinkList = Scrapper.getAbsoluteLinks(doc);

        //2.
        Set<String> initialSet = new HashSet<>();
        for (String link: initialLinkList){
            if(URLValidator.validateTheURL(link)){
                initialSet.add(link);
            }
        }

        //3.
        Pathfinder pathfinder = new Pathfinder();
        pathfinder.links = initialSet;
        pathfinder.traverseSiteUsingSet();

        //4.
        Set<String> socialFreeSet = SetURLModificator.removeSocialMediaLinks(pathfinder.uncheckedLinks);

        //5.
        Set<String> finalSet = URLValidator.validateURLs(socialFreeSet);

        //6.
        finalSet.addAll(initialSet);
        System.out.println("The Set has size: " + finalSet.size());
        System.out.println("The final set is: " + finalSet);

    }
}