package MainProject;

import gestoreurl.*;
import java.io.IOException;
import jsonparser.JsonParser;

public class Api {

  
    public static void main(String[] args) {
        try {
            GestoreUrl url = new GestoreUrl();
            String strMessaggioJson = url.getStringResponseUrl("https://api.telegram.org/bot5260523883:AAGBdOFDWUl1_Enq4SYqbsVqxDrF5HqqXoM/getUpdates");
            JsonParser jsonParser = new JsonParser();
            JSONObject json = jsonParser.getFirstObject(strMessaggioJson);
            JSONArray jsArray = jsonParser.getArrayJsonByKey(json,"result");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
