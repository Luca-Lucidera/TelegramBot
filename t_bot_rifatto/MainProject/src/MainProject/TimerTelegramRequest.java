package MainProject;

import gestoreurl.GestoreUrl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.TimerTask;
import telegramapi.*;
import xmlparser.*;

/**
 *
 * @author lucid
 */
public class TimerTelegramRequest extends TimerTask {

    TelegramApi telegram = new TelegramApi();
    GestoreUrl gestore = new GestoreUrl();
    StaxParser xmlParser = new StaxParser();

    @Override
    public void run() {
        try {
            //prendo i messaggi
            Result[] messaggi = telegram.getMessaggio("https://api.telegram.org/bot5260523883:AAGBdOFDWUl1_Enq4SYqbsVqxDrF5HqqXoM/getUpdates");
            //controllo per vedere se ci sono nuovi messaggi
            if (messaggi[0].getUpdate_id() == -1) {
                System.out.println("Nessun messaggio presente!");
            } else {
                for (int i = 0; i < messaggi.length; i++) {
                    //controllo del comando "/citta "
                    if (messaggi[i].getMessage().getText() == null) {
                        int chat_id = messaggi[i].getMessage().getChat().getId();
                        telegram.sendMessage(
                                "https://api.telegram.org/bot5260523883:AAGBdOFDWUl1_Enq4SYqbsVqxDrF5HqqXoM/sendMessage?chat_id=",
                                chat_id,
                                "Attenzione, usare il comando /citta nomeCitta");
                    } else {
                        int chat_id = messaggi[i].getMessage().getChat().getId();
                        String citta = messaggi[i].getMessage().getText();

                        String apiMappe = "https://nominatim.openstreetmap.org/search?q=";

                        //questo serve per non fare esplodere i link http dentro all'xml
                        String mappeXml = getMeppe(apiMappe, citta);
                        String path = "posto.xml";
                        scriviFile(path, mappeXml);
                        List<Place> places = xmlParser.parseXML(path);
                        if (!places.get(0).getCountry_code().equals("it")) {
                            telegram.sendMessage(
                                    "https://api.telegram.org/bot5260523883:AAGBdOFDWUl1_Enq4SYqbsVqxDrF5HqqXoM/sendMessage?chat_id=",
                                    chat_id,
                                    "Attenzione, la citta deve essere italiana!");
                        } else {
                            String lat = places.get(0).getLat();
                            String lon = places.get(0).getLon();
                            String csv = chat_id + ";" + lat + ";" + lon + "\n";
                            scriviFileAppend("people.csv", csv);
                            telegram.sendMessage(
                                    "https://api.telegram.org/bot5260523883:AAGBdOFDWUl1_Enq4SYqbsVqxDrF5HqqXoM/sendMessage?chat_id=",
                                    chat_id,
                                    "Città inserita correttamente");
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getMeppe(String api, String citta) throws IOException {
        String param = gestore.getParamFormatted(citta);
        String url = api + param + "&format=xml&addressdetails=1";
        String strXml = gestore.getStringResponseUrl(url);
        strXml.replace("&", "&&amp;");
        return strXml;
    }

    public void scriviFileAppend(String fileName, String text) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new FileOutputStream(new File(fileName), true));
        out.write(text);
        out.close();
    }

    public void scriviFile(String fileName, String text) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(fileName);
        out.write(text);
        out.close();
    }
}
