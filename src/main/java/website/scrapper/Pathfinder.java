package website.scrapper;

import java.util.*;

public class Pathfinder {
    public Set<String> links = new HashSet<>();
    Set<String> uncheckedLinks = new HashSet<>();

    //Traverses a Website using only the root exposed links
    //Adds the found links to the unchecked links set
    public void TraverseSiteUsingSet(){
        Set<String> counter = new HashSet<>(links);
        System.out.println("The list used: " + counter);

        for (String count: counter){
            List<String> newList = Scrapper.GetLinksFromURL(count);
            AddFoundLinksToSet(newList);
        }
    }

    //Compare links list with the uncheckedLinks list and add missing
    public void UpdateLinksList(){
        for (String link: uncheckedLinks){
            if(URLValidator.ValidateTheURL(link)){
                links.add(link);
            }
        }
    }

    public void AddFoundLinksToSet(List<String> list){
        for (String link: list){
            uncheckedLinks.add(link);
        }
        System.out.println("The unchecked links list is size: " + uncheckedLinks.size());
    }
}
