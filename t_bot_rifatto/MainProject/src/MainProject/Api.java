package MainProject;


import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import xmlparser.Place;
import xmlparser.StaxParser;

public class Api {

  
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        /*
        try {
            GestoreUrl url = new GestoreUrl();
            String strMessaggioJson = url.getStringResponseUrl("https://api.telegram.org/bot5260523883:AAGBdOFDWUl1_Enq4SYqbsVqxDrF5HqqXoM/getUpdates");
            JsonParser jsonParser = new JsonParser();
            JSONObject json = jsonParser.getFirstObject(strMessaggioJson);
            JSONArray jsArray = jsonParser.getArrayJsonByKey(json,"result");

        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        String fileName = "posto.xml";
        StaxParser xmlparser = new StaxParser();
        List<Place> placeList = xmlparser.parseXML(fileName);
        for (Place place : placeList) {
            System.out.println(place.toString());
        }
        
    }
    
}