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
        
        String jsonStr = gestore.getStringResponseUrl(urlApi); //prendo i messaggi presenti
        json = jsParser.getFirstObject(jsonStr); //converte il la stringa json in un JSONObject
        result = json.getJSONArray("result"); //dall'oggetto prendo il vettore result (JSONArray)
        
        if(result.length() == 0){ //se non ci sono messaggi termina l'esecuzione
            messaggi = new Result[1]; //creo un array di risultati di 1 elemento
            messaggi[0] = new Result(); //aggiungo un risultato vuoto (update_id = -1)
            return messaggi;
        }
        
        messaggi = new Result[result.length()]; //creo il vettore di messaggi (Result) della lunghezza dei messaggi
        
        //per ogni messaggi vado a parsare l'oggetto Result (da JSONObject a Object)
        for (int i = 0; i < messaggi.length; i++) { 
            messaggi[i] = getResultJSON(result.getJSONObject(i)); //passo l'oggetto che sta nel vettore
        }
        
        //chiamo le api per dire che ho l'etto fino all'ultimo messaggio (update_id + 1)
        String strRisposta = gestore.getStringResponseUrl(urlApi + "?offset=" + (messaggi[messaggi.length - 1].getUpdate_id() + 1));
        
        return messaggi;
    }
    
    //restituisce tutto il JSON senza l' "OK"
    public Result getResultJSON(JSONObject json) {
        
        JSONObject message = jsParser.getObjectByKey(json, "message"); //prendo l'oggetto messaggio
        
        Result result = new Result(
                jsParser.getInt(json, "update_id"),
                getMessageJSON(message)
        );
        return result;
    }
    
    //restituisce l'oggetto "message" presente nel json
    public Message getMessageJSON(JSONObject json) {
        String text = jsParser.getString(json, "text"); //vado a prendere il testo
        if(text.contains("/citta ")){ //controllo se è presente la scritta /citta 
            int indexofText = text.indexOf(" "); //vado a prendere la posizione dello spazio (quello dopo /citta)
            //il +1 è per non prendere lo spazio
            text = text.substring(indexofText + 1); //vado a predenre solo la parte della citta
            Message message = new Message(
                jsParser.getInt(json, "message_id"), //message id
                getFromJSON(jsParser.getObjectByKey(json, "from")), //Oggetto "From"
                getChatJSON(jsParser.getObjectByKey(json, "chat")), //Oggetto "Chat"
                jsParser.getInt(json, "date"), //data
                text //testo
            );
            return message;
        }else{
            //se non ha scritto /citta allora vado l'oggetto Chat e poi lo ritorno (per mandare poi una risposta, il campo text = null)
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
