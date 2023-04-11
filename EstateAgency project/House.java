final class House extends Property{

    public House(int squareMeters, String pricePerSquareMeter, String city, String street, Integer floor){ 
    	super(squareMeters, pricePerSquareMeter, city, street, floor);                                  
    }

    @Override
    public Integer getFloor() {
    	return null;
    } 
    
}