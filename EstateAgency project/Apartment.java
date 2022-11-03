final class Apartment extends Property{

    public Apartment(int squareMeters, String pricePerSquareMeter, String city, String street, Integer floor){ 
    	super(squareMeters, pricePerSquareMeter, city, street, floor);                                  
    }
    
    @Override
    public Integer getFloor() {
    	return super.getFloor();
    }
  
}