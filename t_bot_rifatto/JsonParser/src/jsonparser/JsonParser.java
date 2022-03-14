package jsonparser;

import org.json.*;

/**
 *
 * @author lucid
 */
public class JsonParser {
    public void JsonParser(){}

    public JSONObject getFirstObject(String jsonStr){
        JSONObject json = new JSONObject(jsonStr);
        return json;
    }
    public JSONArray getArrayJsonByKey(JSONObject json, String key){
        try{
            JSONArray jsonArray = json.getJSONArray(key); //vado a prendere l'array data la chiave in input
            return jsonArray;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public JSONObject getObjectByKey(JSONObject json, String key){
        try{
            return json.getJSONObject(key);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public int getInt(JSONObject json, String key){
        try{
            return json.getInt(key);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return -9999999;
        }
    }
    public String getString(JSONObject json, String key){
        try{
            return json.getString(key);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
