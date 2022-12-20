import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;


public class TextFileGenerator {
	
	private String xml_FILENAME="newXMLfile.xml";
	private static String txt_filename="newTXTfile.txt";
	private static FileWriter file;
	
	public void Converse() {
		try {
			file = new FileWriter(txt_filename);
			readXML();
			file.close();
		}
		catch (XMLStreamException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public void readXML() throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream in = getClass().getResourceAsStream(xml_FILENAME);
		XMLEventReader eventReader;
		eventReader = inputFactory.createXMLEventReader(in);
		while (eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();
		    if (event.isStartElement()) {
		    	String localPart = event.asStartElement().getName().getLocalPart();
		        switch (localPart) {
		        	case "person":
		        		event = eventReader.nextEvent();
		        		getCharacters(event, eventReader, 'P');
		                break;
		            case "firstname":
		            	getCharacters(event, eventReader, 'P');
		                break;
		            case "lastname":
		            	getCharacters(event, eventReader, '1');
		                break;
		            case "mobile":
		                getCharacters(event, eventReader, 'T');
		                break;
		            case "landline":
		                getCharacters(event, eventReader, '1');
		                break;
		            case "street":
		                getCharacters(event, eventReader, 'A');
		                break;
		            case "city":
		                getCharacters(event, eventReader, '1');
		                break;
		            case "zip":
		                getCharacters(event, eventReader, '2');
		                break;
		            case "name":
		                getCharacters(event, eventReader, 'F');
		                break;
		            case "born":
		                getCharacters(event, eventReader, '1');
		                break;
		        }
		    } 
		    else if (event.isEndElement()) {
		    	if (event.asEndElement().getName().getLocalPart() == "person") {
		    		event = eventReader.nextEvent();
		            continue;
		        }
		    }
		}
    }
 
    private String getCharacters(XMLEvent event, XMLEventReader eventReader, char initial) {
        String dataLine = "";
        try {
			event = eventReader.nextEvent();
			if (event instanceof Characters) {
	            dataLine = event.asCharacters().getData();
	            if (initial == '1') {
	            	writeFile(file, dataLine);
	            }
	            else if (initial == '2') {
	            	writeFile(file, dataLine);
	            }
	            else {
	            	writeFile(initial, file, dataLine);
	            }
	        }
		} 
        catch (XMLStreamException e) {
			e.printStackTrace();
		}
        return dataLine;
    }
    
    public static void writeFile(char initial, FileWriter file, String dataLine) {	
		try {
			file.write("\n" + initial + "|" + dataLine);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFile(FileWriter file, String dataLine) {	
		try {
			file.write("|" + dataLine);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}