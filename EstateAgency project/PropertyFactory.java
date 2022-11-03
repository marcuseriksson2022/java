public class PropertyFactory {

	public static Apartment getApartment(String[] Attributes) {
		String price = removePriceDots(Attributes[1]).toString();
		return new Apartment(Integer.parseInt(Attributes[0]), price, Attributes[2], Attributes[3], Integer.parseInt(Attributes[4]));
	}
	
	public static House getHouse(String[] Attributes) {
		String price = removePriceDots(Attributes[1]).toString();
		return new House(Integer.parseInt(Attributes[0]), price, Attributes[2], Attributes[3], null);
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