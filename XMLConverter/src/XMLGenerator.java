import java.io.*;
import java.util.List;
import javax.xml.parsers.*;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


public class XMLGenerator implements Conversion{
	
	private static String xml_FILENAME="newXMLfile.xml";
	private static String txt_filename="exempel.txt";
	private static Document xml_doc = null;
    private static Element rootElement = null;
    private static Element person = null;
    private static Element E = null;
    private static Element family = null;
    private static BufferedReader br = null;
    private static boolean familyExists = false;
    private static String[] Attributes = new String[3];
	private String[] row;
	private char initial; 

	public void Converse() throws ParserConfigurationException {
		readFile();	
	}
	
	@Override
	public boolean readFile() {
		try {
			InputStream in = getClass().getResourceAsStream(txt_filename);
			br = new BufferedReader(new InputStreamReader(in));
            CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
            CSVReader reader = new CSVReaderBuilder(br).withCSVParser(parser).build();
            xml_doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			rootElement = xml_doc.createElement("people");
		    xml_doc.appendChild(rootElement);
            List<String[]> all = reader.readAll();
            for (int a=0; a<all.size(); a++) {
            	row = all.get(a);
            	initial = row[0].charAt(0);
            	for (int b=1; b<row.length; b++) {
            		Attributes[b-1] = row[b];
            	}
    			switch (initial) {
    				case 'P':
    				    addPerson(person, "person");
    					addData("firstname", "lastname", row, person);
    				case 'T':
    					if (initial != 'P') {
    						addChild("phone");
    						addData("mobile", "landline", row, E);
    						familyExists = false;
    					}
    				case 'A':
    					if (initial != 'P' && initial !='T') {
    						addChild("address");
    						addData("street", "city", "zip", row, E);
    						familyExists = false;
    					}
    				case 'F':
    					if (initial !='T' && initial != 'P' && initial != 'A') { 
    						familyExists = true;
    						addFamily(family, "family");
    						addData("name", "born", row, family);
    					}
    				}
    	        }
            generateXMLFile();
        }
        catch (FileNotFoundException e){
			System.out.println("Error: Input File "+ txt_filename +" not found. Aborted.");
			return false;
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	        return false;
	    } 
		finally {    
			if(br != null){
				try{
					br.close();
	            } 
				catch(IOException e){
	            	e.printStackTrace();
	            }
	        }
	    }
		return true;	
	}
	
	public static void addPerson(Element E, String tag) {
		person = xml_doc.createElement(tag);
	    rootElement.appendChild(person);
	}
	
	public static void addChild(String tag) {
		E = xml_doc.createElement(tag);
		if (familyExists == false) {
			person.appendChild(E);
		}
		else if (familyExists == true) {
			family.appendChild(E);
		}
	}
	
	public static void addFamily(Element E, String tag) {
		family = xml_doc.createElement(tag);
		person.appendChild(family);
	}

	public static void addData(String tag1, String tag2, String[] row, Element e) {
		Element e1 = xml_doc.createElement(tag1);
		Element e2 = xml_doc.createElement(tag2);
		e1.appendChild(xml_doc.createTextNode(row[1]));
		e.appendChild(e1);
		e2.appendChild(xml_doc.createTextNode(row[2]));
		e.appendChild(e2);
	}
	
	public static void addData(String tag1, String tag2, String tag3, String[] row, Element e) {
		Element e1 = xml_doc.createElement(tag1);
		Element e2 = xml_doc.createElement(tag2);
		e1.appendChild(xml_doc.createTextNode(row[1]));
		e.appendChild(e1);
		e2.appendChild(xml_doc.createTextNode(row[2]));
		e.appendChild(e2);
		if (row.length > 3) {
			Element e3 = xml_doc.createElement(tag3);
			e3.appendChild(xml_doc.createTextNode(row[3]));
			e.appendChild(e3);
		}
	}

	@Override
	public void generateXMLFile() {
		DOMSource DS = new DOMSource(xml_doc);
        StreamResult SR = new StreamResult(new File(xml_FILENAME));
        try {
            TransformerFactory.newInstance().newTransformer().transform(DS, SR);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
		
	}
	
}