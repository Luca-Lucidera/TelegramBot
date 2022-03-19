package xmlparser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author lucid
 */
public class StaxParser { //se da un errore, metti questo negli url dell'xml &amp;
    public StaxParser(){
        
    }
    public List<Place> parseXML(String path){
        List<Place> searchResult = new ArrayList<>();
        Place place = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "place" -> {
                            place = new Place();
                            Attribute lat = startElement.getAttributeByName(new QName("lat"));
                            Attribute lon = startElement.getAttributeByName(new QName("lon"));
                            if (lat != null) {
                                place.setLat(lat.getValue());
                            }
                            if(lon != null){
                                place.setLon(lon.getValue());
                            }
                        }
                        case "city" -> {
                            xmlEvent = xmlEventReader.nextEvent();
                            place.setCity(xmlEvent.asCharacters().getData());
                        }
                        case "county" -> {
                            xmlEvent = xmlEventReader.nextEvent();
                            place.setCounty(xmlEvent.asCharacters().getData());
                        }
                        case "state" -> {
                            xmlEvent = xmlEventReader.nextEvent();
                            place.setState(xmlEvent.asCharacters().getData());
                        }
                        case "postcode" -> {
                            xmlEvent = xmlEventReader.nextEvent();
                            place.setPostcode(xmlEvent.asCharacters().getData());
                        }
                        case "country" -> {
                            xmlEvent = xmlEventReader.nextEvent();
                            place.setCountry(xmlEvent.asCharacters().getData());
                        }
                        case "country_code" -> {
                            xmlEvent = xmlEventReader.nextEvent();
                            place.setCountry_code(xmlEvent.asCharacters().getData());
                        }
                        default -> {
                        }
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("place")) {
                        searchResult.add(place);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return searchResult;
    }
}
