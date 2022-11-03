import java.io.IOException;
import java.util.ArrayList;
//import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class EstateAgency implements PropertyTransaction {
	
	 private static ArrayList<Property> objectList = new ArrayList<Property>();
	 private PriorityOrderAttribute Attr;
	 private static JFrame f = new JFrame();
	 private static XMLService xmlService = new XMLService(); 
	 private static CSVService csvService = new CSVService();
	 
	 public static void addNewProperty(String[] Attributes) {
		 Property apartment = PropertyFactory.getProperty(Attributes, 'A');
		 objectList.add(apartment);
	 }
	 
	 public static void addNewProperty(String[] Attributes, String floor) {
		 Property house = PropertyFactory.getProperty(Attributes, 'H');
		 objectList.add(house);
	 }
	 
	 public void sortProperties() throws ParserConfigurationException, SAXException, IOException{
		Attr=getPriorityOrderAttribute();
		orderByAttribute();
		for (Property o : objectList) {
			reportProperty(o.getSquareMeters(), o.getPricePerSquareMeter(), o.getCity(), o.getStreet(), o.getFloor());
		}
		//alternative solution:
		/*Iterator<SaleObject> iter = objectList.iterator();
		while (iter.hasNext()) {
			SaleObject o = iter.next();
			reportSaleObject(o.getSquareMeters(), o.getPricePerSquareMeter(), o.getCity(), o.getStreet(), o.getFloor());
		}*/
	}

	@Override
	public PriorityOrderAttribute getPriorityOrderAttribute() {
		PriorityOrderAttribute [] alt={PriorityOrderAttribute.City,PriorityOrderAttribute.SquareMeters,PriorityOrderAttribute.PricePerSquareMeter};
		PriorityOrderAttribute POA = (PriorityOrderAttribute) JOptionPane.showInputDialog
				(f, "Choose an attribute to order by", "Choose attribute", JOptionPane.PLAIN_MESSAGE, null, alt, alt[0]);
			System.out.println("------------------------------------------------");
			System.out.println("PriorityOrderAttribute: " + POA);
			return POA;
	}

	@Override
	public void startPropertyTransaction() throws ParserConfigurationException, SAXException, IOException {
		xmlService.xmlRead();
		csvService.csvRead();
	}

	@Override
	public void reportProperty(int squareMeters, String pricePerSquareMeter, String city, String street,
			Integer floor) {
		System.out.println("------------------------------------------------");
		System.out.println("Size in squaremeters: " + "\t" + squareMeters);
		System.out.println("Price per squaremeter: " + "\t" + pricePerSquareMeter);
		System.out.println("City: "  + "\t\t\t" + city);
		System.out.println("Street: " + "\t\t" + street);
		System.out.println("Floor: " + "\t\t\t" + floor);
	}

	@Override
	public void endPropertyTransaction() {
		objectList.clear();
	}
	
	public void orderByAttribute() {
		for (int i=0; i < objectList.size(); i++) {	   
			for (int j= i+1; j < objectList.size(); j++)  {		
				Property anObject = (Property)objectList.get(i);
				Property anotherObject = (Property)objectList.get(j);
				String st1=anObject.getStreet();
				String st2=anotherObject.getStreet();
				
				if (Attr==PriorityOrderAttribute.City){
					String c1=anObject.getCity();
					String c2=anotherObject.getCity();
					if (c1.compareToIgnoreCase(c2) > 0) {			    	//städerna jämförs
						swapElementsInArray(anObject, anotherObject, i, j);
					}
					else if(c1.compareToIgnoreCase(c2) == 0)            	//om städerna är lika
						if (st1.compareToIgnoreCase(st2) > 0) {         	//jämförs gatunamnen 
							swapElementsInArray(anObject, anotherObject, i, j);		    
						}
				}
				if (Attr==PriorityOrderAttribute.SquareMeters){
					int size1i=anObject.getSquareMeters();
					int size2i=anotherObject.getSquareMeters();
					String getPrice1=anObject.getPricePerSquareMeter();
					String getPrice2=anotherObject.getPricePerSquareMeter();
					if (size1i > size2i) {			    						//kvadratmeterna jämförs
						swapElementsInArray(anObject, anotherObject, i, j);
					}
					else if(size1i==size2i)            							//om kvadratmeterna är lika
						if (getPrice1.compareToIgnoreCase(getPrice2) > 0) { //jämförs priserna 
							swapElementsInArray(anObject, anotherObject, i, j);	    
						}
				}
				if (Attr==PriorityOrderAttribute.PricePerSquareMeter) {
					String price1=anObject.getPricePerSquareMeter();
					String price2=anotherObject.getPricePerSquareMeter();
					int price1i=Integer.parseInt(price1);
					int price2i=Integer.parseInt(price2);
					if (price1i > price2i) {			    				//priset jämförs
						swapElementsInArray(anObject, anotherObject, i, j);
					}
					else if(price1i==price2i)           					//om priserna är lika
						if (st1.compareToIgnoreCase(st2) > 0) {         	//jämförs gatunamnen
							swapElementsInArray(anObject, anotherObject, i, j);		    
						}
				}
			} 
		}
	}	

	public void swapElementsInArray(Property anObject, Property anotherObject, int i, int j){
		objectList.set(i, anotherObject);
		objectList.set(j, anObject);
	}

}