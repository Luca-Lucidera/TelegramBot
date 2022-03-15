package MainProject;


import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import xmlparser.PlaceHandler;
import xmlparser.SearchResult;

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
        SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
        SAXParser saxParser = factory.newSAXParser();
        PlaceHandler placeHandler = new PlaceHandler();
        saxParser.parse("posto.xml", placeHandler);
        SearchResult searchResult = placeHandler.getXmlClasse();
        System.out.println(searchResult);
        
    }
    
}