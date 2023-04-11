import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


public interface PropertyTransaction {
  
    PriorityOrderAttribute getPriorityOrderAttribute();
    	
    void startPropertyTransaction() throws ParserConfigurationException, SAXException, IOException;
  
    void reportProperty(int squareMeters, String pricePerSquareMeter, String city, String street, Integer floor);

    void endPropertyTransaction();

    enum PriorityOrderAttribute {
        City,
        SquareMeters,
        PricePerSquareMeter;
    }

}
