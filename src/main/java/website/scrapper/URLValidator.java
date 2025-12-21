package website.scrapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class URLValidator {

    //Checks, if the link is secure and is reachable
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
}
