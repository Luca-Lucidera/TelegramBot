package telegramapi;

import gestoreurl.GestoreUrl;
import java.io.IOException;
import java.net.MalformedURLException;
import jsonparser.JsonParser;
import org.json.*;

/**
 *
 * @author lucid
 */
public class TelegramApi {

    GestoreUrl gestore;
    JsonParser jsParser;
    
    public TelegramApi() {
        gestore = new GestoreUrl();
        jsParser = new JsonParser();
    }
    
    //restituisce un array di result (messaggi) 
    public Result[] getMessaggio(String urlApi) throws IOException{

        Result[] messaggi;
        JSONObject json;
        JSONArray result;
        
        String jsonStr = gestore.getStringResponseUrl(urlApi);
        json = jsParser.getFirstObject(jsonStr);
        result = json.getJSONArray("result");
        
        if(result.length() == 0){//se non ci sono messaggi termina l'esecuzione
            messaggi = new Result[1];
            messaggi[0] = new Result();
            return messaggi;
        }
        
        messaggi = new Result[result.length()];
        for (int i = 0; i < messaggi.length; i++) {
            messaggi[i] = getResultJSON(result.getJSONObject(i)); //passo l'array result
        }
        String strRisposta = gestore.getStringResponseUrl(urlApi + "?offset=" + (messaggi[messaggi.length - 1].getUpdate_id() + 1));
        
        return messaggi;
    }
    
    //restituisce tutto il JSON senza l' "OK"
    public Result getResultJSON(JSONObject json) {
        
        JSONObject message = jsParser.getObjectByKey(json, "message");
        
        Result result = new Result(
                jsParser.getInt(json, "update_id"),
                getMessageJSON(message)
        );
        return result;
    }
    
    //restituisce l'oggetto "message" presente nel json
    public Message getMessageJSON(JSONObject json) {
        String text = jsParser.getString(json, "text");
        if(text.contains("/citta ")){
            int indexofText = text.indexOf(" ");
            //il +1 è per non prendere lo spazio
            text = text.substring(indexofText + 1);
            Message message = new Message(
                jsParser.getInt(json, "message_id"),
                getFromJSON(jsParser.getObjectByKey(json, "from")),
                getChatJSON(jsParser.getObjectByKey(json, "chat")),
                jsParser.getInt(json, "date"),
                text
            );
            return message;
        }else{
            return new Message(getChatJSON(jsParser.getObjectByKey(json, "chat"))); //se il comando non è /citta allora ritorna null
        }
    }
    
    //restituisce oggetto "from" presente nel json
    public From getFromJSON(JSONObject json) {
        From from = new From(
                jsParser.getInt(json, "id"),
                json.getBoolean("is_bot"),
                jsParser.getString(json, "first_name")
        );
        
        if(json.has("last_name")){
            from.setLast_name(jsParser.getString(json,"last_name"));
        }
        if(json.has("username")){
            from.setUsername(jsParser.getString(json,"username"));
        }
        if(json.has("language_code")){
            from.setLanguage_code(jsParser.getString(json,"language_code"));
        }
        return from;

    }

    //restituisce l'oggetto "chat" presente nel json
    public Chat getChatJSON(JSONObject json) {
        Chat chat = new Chat(
                jsParser.getInt(json,"id"),
                jsParser.getString(json,"first_name"),
                jsParser.getString(json,"type")
        );
        
        if(json.has("last_name")){
            chat.setLast_name(jsParser.getString(json,"last_name"));
        }
        if(json.has("username")){
            chat.setUsername(jsParser.getString(json,"username"));
        }
        return chat;
    }
    
    //Manda un messaggio
    public void sendMessage(String api, int chat_id, String text) throws MalformedURLException, IOException {
        /*
        URL url = new URL("https://api.telegram.org/bot5260523883:AAGBdOFDWUl1_Enq4SYqbsVqxDrF5HqqXoM/sendMessage?chat_id=" + chat_id + "&text=" + text);
        Scanner s = new Scanner(url.openStream());
        s.useDelimiter("\u001a");
        String strJson = s.next();
        */
        String url = api + chat_id + "&text=" + text; 
        gestore = new GestoreUrl();
        String response = gestore.getStringResponseUrl(url);
        
    }  
}
