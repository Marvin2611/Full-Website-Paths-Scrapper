package org.example;

import java.util.List;

public class Pathfinder {
    public List<String> paths;

    public void FindAllPaths(String mainURL){
        //Initialize finding all paths by using the main url of the site
        //Trys to find all paths of the website
        //Depth of undersites should be adjustable (3)

        //Pseudo Code
        // Condition: Only go 3 deep into each link

        // Get all hyperlinks from scrapper
        // Remove all hyperlinks from list that dont have the matching url premise
        // Compare existing hyperlinks with the new hyperlinks (no doubles)
        // Take the first item from the list that was not searched yet and repeat the process

    }
}
