public class Converter {
	public static int option;
	public static void main(String[] args) throws Exception {
		new Menu();
		while (option != 0) {
			new Menu();
		}
		Menu.optionInput.close();
		System.exit(0);
	}
	
}