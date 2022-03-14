
package gestoreurl;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


public class GestoreUrl {

    public void GestoreUrl(){}

    public String getStringResponseUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        Scanner scanner = new Scanner(url.openStream());
        scanner.useDelimiter("\u001a");
        String risposta = scanner.next();
        return risposta;
    }
    
}
