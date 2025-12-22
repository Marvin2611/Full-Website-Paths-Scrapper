package website.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scrapper {

    //Return the full html site document
    public static Document tryGetDocument(String url){
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            return doc;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Trys to retrieve a list with all absolute links with the given link
    public static List<String> getLinksFromURL(String url){

        Document doc = tryGetDocument(url);
        return getAbsoluteLinks(doc);
    }

    //Trys to retrieve a list with all absolute links of the html document
    public static List<String> getAbsoluteLinks(Document doc){
        //Gets all the absolute links from this site
        List<String> fullLinkList = new ArrayList<>();
        Elements links = doc.select("a[href]");
        for (Element link: links){
            String absHref = link.attr("abs:href");
            fullLinkList.add(absHref);
            //System.out.println("The Element Link: " + link);
            //System.out.println("Abs HREF: " + absHref);
        }
        System.out.println("Links found: " + fullLinkList.size());
        return fullLinkList;
    }

    //Trys to retrieve a list with all absolute links that contain a certain root
    public static List<String> getAbsoluteLinksFromRoot(Document doc){
        //Gets all the absolute links from this site
        String siteTrunk = "ihk.de";
        String query = String.format("a[href*=%s]", siteTrunk);

        List<String> fullLinkList = new ArrayList<>();
        Elements links = doc.select(query);
        for (Element link: links){
            String absHref = link.attr("abs:href");
            fullLinkList.add(absHref);
        }
        System.out.println("Links found: " + fullLinkList.size());
        return fullLinkList;
    }

}
