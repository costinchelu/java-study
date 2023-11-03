package sec20d_highlevelapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class URLandURI {

    public static void main(String[] args) {
        try {
            URI uri = new URI("db://username:pasword@myserver.com:5000/catalogue/phones?os=android#samsung");
            URI absoluteUri = new URI("http://username:pasword@myserver.com:5000/catalogue/phones?os=android#samsung");

            URI baseUri = new URI("http://username:pasword@mynewserver.com:5000");
            URI relativeUri = new URI("/catalogue/phones?os=android#samsung");
            URI resolvedUri = baseUri.resolve(relativeUri); // binds relativeUri to baseUri

            URI relativeUri2 = new URI("/catalogue/tvs?manufacturer=samsung");
            URI relativeUri3 = new URI("/stores/locations?zip=12345");
            URI resolvedUri2 = baseUri.resolve(relativeUri2);
            URI resolvedUri3 = baseUri.resolve(relativeUri3);

            System.out.println("URI = " + uri.toString());
            System.out.println("Scheme = " + uri.getScheme());
            System.out.println("Scheme-specific part = " + uri.getSchemeSpecificPart());
            System.out.println("Authority = " + uri.getAuthority());
            System.out.println("User info = " + uri.getUserInfo());
            System.out.println("Host = " + uri.getHost());
            System.out.println("Port = " + uri.getPort());
            System.out.println("Path = " + uri.getPath());
            System.out.println("Query = " + uri.getQuery());
            System.out.println("Fragment " + uri.getFragment());

            URL uriToUrl = absoluteUri.toURL();
            System.out.println("URL = " + uriToUrl);
            // only absolute URI's can be converted to an URL

            URL url1 = resolvedUri.toURL();
            System.out.println("URL 1 = " + url1);
            URL url2 = resolvedUri2.toURL();
            System.out.println("URL 2 = " + url2);
            URL url3 = resolvedUri3.toURL();
            System.out.println("URL 3 = " + url3);

            URI relativizedURI = baseUri.relativize(resolvedUri2);
            System.out.println("Relative URI = " + relativizedURI);

            URL url = new URL("http://example.org");
            URI urlToUri = url.toURI();
            // URL's can be converted to URI's

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            while(line != null) {
                line = inputStream.readLine();
                System.out.println(line);
                // we will get the html
            }
            inputStream.close();

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
