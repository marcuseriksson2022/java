public class Property {

    private int squareMeters;
    private String pricePerSquareMeter;
    private String city;
    private String street;
    private Integer floor;

    public Property(int squareMeters, String pricePerSquareMeter, String city, String street, Integer floor){ 
    	this.squareMeters = squareMeters;                                  
    	this.pricePerSquareMeter = pricePerSquareMeter;
    	this.city = city;
    	this.street = street;
    	this.floor = floor;
    }

    public int getSquareMeters() {
    	return squareMeters;
    }

    public String getPricePerSquareMeter() {
    	return pricePerSquareMeter;
    }

    public String getCity() {
    	return city;
    }

    public String getStreet() {
    	return street;
    }

    public Integer getFloor() {
    	return floor;
    }

}