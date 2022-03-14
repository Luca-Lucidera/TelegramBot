package xmlparser;

import java.io.File;
import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

/**
 *
 * @author lucid
 */
public class XmlParser {

    public XmlParser(){}
    public Document getDocumentFile(String path){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(path));
            doc.getDocumentElement().normalize();
            return doc;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public String getStringRoot(Document xml){
        return xml.getDocumentElement().getNodeName();
    }
    public NodeList getListaNodi(Document doc, String upperNode){
        try{
            doc.normalize();
            NodeList list = doc.getElementsByTagName(upperNode);
            return list;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }
    public Element getNodo(NodeList lista, int index) {
        try {
            Node nodo = lista.item(index);
            Element element = (Element) nodo;
            return element;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public String getTestoNodo(Element elemento, String tagName){
        return elemento.getElementsByTagName(tagName).item(0).getTextContent();
    }
    public String getTestoNodoDiretto(Document doc, String nodoPadre, String nodoDaPrendere, int posizioneNodo){
        String s = "";
        try{
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName(nodoPadre);
            Node node = list.item(posizioneNodo);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                s = element.getElementsByTagName(nodoDaPrendere).item(0).getTextContent();
            }
            return s;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
}
