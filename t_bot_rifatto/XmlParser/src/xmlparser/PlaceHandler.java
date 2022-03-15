package xmlparser;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author lucid
 */
public class PlaceHandler extends DefaultHandler {

    private static final String SEARCHRESULT = "searchresult";
    private static final String PLACE = "place";
    private static final String CITY = "city";
    private static final String COUNTY = "county";
    private static final String STATE = "state";
    private static final String POSTCODE = "postcode";
    private static final String COUNTRY = "country";
    private static final String COUNTRY_CODE = "country_code";

    private SearchResult searchResult;
    private StringBuilder elementValue;

    public PlaceHandler(){
        
    }
    
    @Override //Serve per convertire i caratteri singoli in stringa
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override //Invocato quando si inizia a fare il parsing del documento, serve per stanziare l'istanza della lista <Place>
    public void startDocument() throws SAXException {
        searchResult = new SearchResult();
    }

    @Override //Invocato quando si deve parsare un elemento, si userà per costruire SearchResult (List<Place>) e i Place singoli
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case SEARCHRESULT:
                searchResult.places = new ArrayList<>();
                break;
            case PLACE:
                searchResult.places.add(new Place());
                break;
            case CITY:
                elementValue = new StringBuilder();
                break;
            case COUNTY:
                elementValue = new StringBuilder();
                break;
            case STATE:
                elementValue = new StringBuilder();
                break;
            case POSTCODE:
                elementValue = new StringBuilder();
                break;
            case COUNTRY:
                elementValue = new StringBuilder();
                break;
            case COUNTRY_CODE:
                elementValue = new StringBuilder();
                break;
        }
    }

    @Override //Invocato quando ha finito di parsare l'elemento, qui si deve assegnare l'elemento parsato alla variabile/oggetto
    public void endElement(String uri, String localName,
            String qName) throws SAXException {
        int pos = searchResult.places.size() - 1;
        switch (qName) {
            case CITY:
                searchResult.places.get(pos).setCity(elementValue.toString());
                break;
            case COUNTY:
                searchResult.places.get(pos).setCountry(elementValue.toString());
                break;
            case STATE:
                searchResult.places.get(pos).setState(elementValue.toString());
                break;
            case POSTCODE:
                searchResult.places.get(pos).setPostcode(elementValue.toString());
                break;
            case COUNTRY:
                searchResult.places.get(pos).setCountry(elementValue.toString());
                break;
            case COUNTRY_CODE:
                searchResult.places.get(pos).setCountry_code(elementValue.toString());
                break;
        }
    }
    public SearchResult getXmlClasse(){
        return searchResult;
    }
}
