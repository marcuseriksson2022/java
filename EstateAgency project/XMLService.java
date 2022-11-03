import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;


public class XMLService {
	
	private static String xml_FILENAME="Properties.xml";
	private static String[] Attributes = {null, null, null, null, null};

    public void xmlRead() {
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = getClass().getResourceAsStream(xml_FILENAME);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName().getLocalPart();
                    switch (localPart) {
                        case "SaleObject":
                            event = eventReader.nextEvent();
                            break;
                        case "sizeSqm":
                            Attributes[0] = getCharacters(event, eventReader);
                            break;
                        case "startingPrice":
                        	Attributes[1] = getCharacters(event, eventReader);
                            break;
                        case "city":
                        	Attributes[2] = getCharacters(event, eventReader);
                            break;
                        case "street":
                        	Attributes[3] = getCharacters(event, eventReader);
                            break;
                        case "floor":
                        	Attributes[4] = getCharacters(event, eventReader);
                            if (Attributes[4] == "") {
                            	EstateAgency.addNewProperty(Attributes, null);
                            }
                            else {
                            	EstateAgency.addNewProperty(Attributes);
                            }
                            break;
                    }
                } 
                else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == "SaleObject") {
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
        }
        catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    private String getCharacters(XMLEvent event, XMLEventReader eventReader) {
        String dataLine = "";
        try {
			event = eventReader.nextEvent();
			if (event instanceof Characters) {
	            dataLine = event.asCharacters().getData();
	        }    
		} 
        catch (XMLStreamException e) {
			e.printStackTrace();
		}
        return dataLine;
    }
    
}