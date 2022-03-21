
package gestoreurl;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class GestoreUrl {

    public void GestoreUrl(){}

    //dai l'url ritorna la stringa di risposta del WS
    public String getStringResponseUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        Scanner scanner = new Scanner(url.openStream());
        scanner.useDelimiter("\u001a"); //delimiter per non far esplodere i file XML e JSON
        String risposta = scanner.next();
        return risposta;
    }
    //data una stringa ritorna la stringa in formato adatto per l'url
    public String getParamFormatted(String param){
        URLEncoder encoder = null;
        return encoder.encode(param, StandardCharsets.UTF_8);
    }
}
