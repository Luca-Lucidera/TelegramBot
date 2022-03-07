package telegram_bot;

import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.TimerTask;
import libapitelegram.*;


public class ThreadTelegramCall extends TimerTask {

    TelegramApi api = new TelegramApi();
    //esegue questo codice ogni 30 secondi
    public void run() {
        try {
            Result[] result = api.getMessaggio();
            if (result[0].getUpdate_id() == -1) {
                System.out.println("Nessun messaggio presente");
            } else {
                for (int i = 0; i < result.length; i++) {
                    String messaggio = result[i].getMessage().getText();
                    URLEncoder encoder = null;
                    String apiMappe = "https://nominatim.openstreetmap.org/search?q=";
                    String param = encoder.encode(messaggio, StandardCharsets.UTF_8);
                    apiMappe = apiMappe + param + "&format=xml&addressdetails=1";
                    URL url = new URL(apiMappe);
                    Scanner s = new Scanner(url.openStream());
                    s.useDelimiter("\u001a");
                    String xml = s.next();
                    PrintWriter out;
                    out = new PrintWriter("posto.xml");
                    out.write(xml);
                    out.close();
                    XmlParser parser = new XmlParser();
                    if (parser.checkCityFile("posto.xml")) {
                        parser.insertCity("posto.xml", result[0]);
                        api.sendMessage(result[i].getMessage().getChat().getId(), "Località inserita!");
                    } else {
                        api.sendMessage(result[i].getMessage().getChat().getId(), "Hai scritto na cazzata");
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
