package telegram_bot;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import libapitelegram.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.xml.XMLConstants;
import org.xml.sax.InputSource;

/**
 *
 * @author lucid
 */
public class XmlParser {

    private Document document;

    public XmlParser() {
    }

    public boolean checkCityFile(String path) throws ParserConfigurationException, SAXException, IOException {
        NodeList placeList = getDocumentFromFile(path, "place");
        Node nodo = placeList.item(0); //vado a prendere il primo elemento
        Element element = (Element) nodo;
        if(element == null){
            return false;
        }
        String country_code = element.getElementsByTagName("country_code").item(0).getTextContent();
        if (placeList.getLength() > 0 && country_code.equals("it")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkCitySTR(String xml) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);
        NodeList placeList = doc.getElementsByTagName("place");
        Node nodo = placeList.item(0);
        Element element = (Element) nodo;
        String country_code = element.getElementsByTagName("country_code").item(0).getTextContent();
        if (placeList.getLength() > 0 && country_code.equals("it")) {
            return true;
        } else {
            return false;
        }
    }

    public void insertCity(String path, Result messaggio) throws ParserConfigurationException, SAXException, IOException {
        NodeList placeList = getDocumentFromFile(path, "place"); //como -> 1 | cesano maderno
        Node nodo = placeList.item(0); //vado a prendere il primo elemento
        Element element = (Element) nodo;
        String county = element.getElementsByTagName("county").item(0).getTextContent();
        String lat = element.getAttribute("lat");
        String lon = element.getAttribute("lon");
        int chat_id = messaggio.getMessage().getChat().getId();
        PrintWriter out = new PrintWriter(new FileOutputStream(new File("people.csv"), true));
        String csv = chat_id + ";" + lat + ";" + lon + ";" + county + "\n";
        out.write(csv);
        out.close();
    }

    public NodeList getDocumentFromFile(String path, String nodo) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(path));
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodo);
    }

}
