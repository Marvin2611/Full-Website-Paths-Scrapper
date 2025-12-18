package website.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.*;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Scrapper {

    //Return the full html site document
    public static Document TryGetDocument(String url){
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            return doc;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean ValidateTheURL(String url){
        URI uri = validateUrl(url);

        if (uri == null) {
            return false;
            //throw new IllegalArgumentException("Invalid URL");
        }

        if (!isSafeHost(uri)) {
            return false;
            //throw new SecurityException("Unsafe URL");
        }

        if (!isReachable(uri)) {
            return false;
            //throw new IllegalStateException("URL not reachable");
        }
        System.out.println("Link is reachable! Link is: " + uri);
        return true;
    }

    //Take an url and validate it as a possible and safe URI
    private static URI validateUrl(String url) {
        try {
            URI uri = new URI(url);

            // Must be absolute and use http/https
            if (!uri.isAbsolute()) return null;

            String scheme = uri.getScheme();
            if (!"http".equalsIgnoreCase(scheme) &&
                    !"https".equalsIgnoreCase(scheme)) {
                return null;
            }

            return uri;
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private static boolean isReachable(URI uri) {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .connectTimeout(Duration.ofSeconds(5))
                    .build();

            HttpRequest request = HttpRequest.newBuilder(uri)
                    .method("HEAD", HttpRequest.BodyPublishers.noBody())
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<Void> response =
                    client.send(request, HttpResponse.BodyHandlers.discarding());

            int status = response.statusCode();
            return status >= 200 && status < 400;

        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isSafeHost(URI uri) {
        String host = uri.getHost();
        if (host == null) return false;

        // Block localhost and internal networks
        return !(
                host.equalsIgnoreCase("localhost") ||
                        host.startsWith("127.") ||
                        host.startsWith("10.") ||
                        host.startsWith("192.168.") ||
                        host.endsWith(".internal")
        );
    }

    //----------

    //List can still contain fragments of css code
    public static List<String> GetAbsoluteLinks(Document doc){
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
}
