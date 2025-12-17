package website.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Scrapper {
    String url;

    public void Extract_Data(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //TIP We only need the p texts from the site
        // Loop through all ps, and list them in a string array

        // Information about the company
        Elements linkText = doc.select("a");
        for (Element element: linkText){
            System.out.println("Text: " + element);
        }
    }
}
