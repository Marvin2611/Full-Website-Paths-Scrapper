package website.scrapper;

import java.util.*;

public class Pathfinder {
    public Set<String> links = new HashSet<>();
    public Set<String> uncheckedLinks = new HashSet<>();

    //Traverses a Website using only the root exposed links
    //Adds the found links to the unchecked links set
    public void traverseSiteUsingSet(){
        Set<String> counter = new HashSet<>(links);
        System.out.println("The list used: " + counter);

        for (String count: counter){
            List<String> newList = Scrapper.getLinksFromURL(count);
            addFoundLinksToSet(newList);
        }
    }

    //Compare links list with the uncheckedLinks list and add missing
    public void updateLinksList(){
        for (String link: uncheckedLinks){
            if(URLValidator.validateTheURL(link)){
                links.add(link);
            }
        }
    }

    public void addFoundLinksToSet(List<String> list){
        uncheckedLinks.addAll(list);
        System.out.println("The unchecked links list is size: " + uncheckedLinks.size());
    }
}
