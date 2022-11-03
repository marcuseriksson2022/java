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
	
	public static StringBuilder removePriceDots(String price) {
		StringBuilder sb = new StringBuilder(price);
		for (int z=0; z<sb.length(); z++){
			if (sb.charAt(z)=='.')
				sb.deleteCharAt(z);				//tar bort oönskade punkter	
		}
		return sb;
	}
	
}