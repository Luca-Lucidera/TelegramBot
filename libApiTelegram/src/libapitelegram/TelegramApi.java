/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template StrJson, choose Tools | Templates
 * and open the template in the editor.
 */
package libapitelegram;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author lucid
 */
public class TelegramApi {

    public void TelegramApi() {
    }

    public Result[] getMessaggio() throws IOException {
        //faccio la chiamata alle API per prendere un nuovo messaggio
        URL url = new URL("https://api.telegram.org/bot5260523883:AAGBdOFDWUl1_Enq4SYqbsVqxDrF5HqqXoM/getUpdates");
        Scanner s = new Scanner(url.openStream());
        s.useDelimiter("\u001a");
        String strJson = s.next();

        //trasformo la stringa strJson in un JSONObject
        JSONObject obj = new JSONObject(strJson);
        Result[] temp;
        int nMessaggi = obj.getJSONArray("result").length();
        if (nMessaggi == 0) {
            temp = new Result[1];
            temp[0] = new Result();
            return temp;
        }
        temp = new Result[nMessaggi];
        for (int i = 0; i < nMessaggi; i++) {
            temp[i] = parseJson(obj.getJSONArray("result").getJSONObject(i));
        }

        //faccio una seconda chiamta alle API per confermare la lettura del messaggio
        url = new URL("https://api.telegram.org/bot5260523883:AAGBdOFDWUl1_Enq4SYqbsVqxDrF5HqqXoM/getUpdates?offset=" + (temp[temp.length - 1].getUpdate_id() + 1));
        s = new Scanner(url.openStream());
        s.useDelimiter("\u001a");
        s.next();
        //ritorno il messaggio scritto dall'utente
        return temp;
    }

    //restituisce tutto il JSON senza l' "OK"
    public Result parseJson(JSONObject json) {
        JSONObject mess = json.getJSONObject("message");
        Result result = new Result(
                json.getInt("update_id"),
                getMessageJSON(json.getJSONObject("message"))
        );
        return result;
    }

    //restituisce l'oggetto message presente nel json
    public Message getMessageJSON(JSONObject json) {
        Message message = new Message(
                json.getInt("message_id"),
                getFromJSON(json.getJSONObject("from")),
                getChatJSON(json.getJSONObject("chat")),
                json.getInt("date"),
                json.getString("text")
        );
        return message;
    }

    //restituisce l'oggeto From presente nel json
    public From getFromJSON(JSONObject json) {
        From from = new From(
                json.getInt("id"),
                json.getBoolean("is_bot"),
                json.getString("first_name")
        );
        if(json.has("last_name")){
            from.setLast_name(json.getString("last_name"));
        }
        if(json.has("username")){
            from.setUsername(json.getString("username"));
        }
        if(json.has("language_code")){
            from.setLanguage_code(json.getString("language_code"));
        }
        return from;

    }

    //restituisce l'oggetto chat presente nel json
    public Chat getChatJSON(JSONObject json) {
        Chat chat = new Chat(
                json.getInt("id"),
                json.getString("first_name"),
                json.getString("type")
        );
        
        if(json.has("last_name")){
            chat.setLast_name(json.getString("last_name"));
        }
        if(json.has("username")){
            chat.setUsername(json.getString("username"));
        }
        return chat;
    }

    //Manda un messaggio
    public void sendMessage(int chat_id, String text) throws MalformedURLException, IOException {
        URL url = new URL("https://api.telegram.org/bot5260523883:AAGBdOFDWUl1_Enq4SYqbsVqxDrF5HqqXoM/sendMessage?chat_id=" + chat_id + "&text=" + text);
        Scanner s = new Scanner(url.openStream());
        s.useDelimiter("\u001a");
        String strJson = s.next();
    }

}
