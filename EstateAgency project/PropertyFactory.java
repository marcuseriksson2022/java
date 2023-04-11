public class PropertyFactory {

	public static Property getProperty(String[] Attributes, char type) {
		String price = removePriceDots(Attributes[1]).toString();
		switch (type) {
			case 'A':
				return new Apartment(Integer.parseInt(Attributes[0]), price, Attributes[2], Attributes[3], Integer.parseInt(Attributes[4]));
			case 'H':
				return new House(Integer.parseInt(Attributes[0]), price, Attributes[2], Attributes[3], null);
		}
		return null;
	}
	
	public static String removePriceDots(String price) {
		String number = price.replace(".", "");
		return number;
	}
	
}