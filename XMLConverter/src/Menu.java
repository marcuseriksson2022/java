import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

public class Menu {
	
	static Scanner optionInput = new Scanner(System.in);
	
	public Menu() throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
		System.out.println("----------------------");
		System.out.println("Converter Menu: ");
		System.out.println("----------------------");
		System.out.println("1) Text file to XML");
		System.out.println("2) XML to text file");
		System.out.println("0) Exit");
		Converter.option = optionInput.nextInt();
		switch (Converter.option) {
		case 1:
			System.out.println("CONVERT TEXT FILE TO XML");
			XMLGenerator xmlCreator = new XMLGenerator();
			xmlCreator.Converse();
			System.out.println("XML file generated." + "\n");
			break;
		case 2:
			System.out.println("CONVERT XML TO TEXT FILE");
			TextFileGenerator textfileCreator = new TextFileGenerator();
			textfileCreator.Converse();
			System.out.println("Text file generated." + "\n");
			break;
		case 0:
			System.out.println("EXIT");
			System.out.println("Thanks for using XML Converter.");
			break;
		default:
			System.out.println("\nNot an option.");
			break;
		}
	}

	
}