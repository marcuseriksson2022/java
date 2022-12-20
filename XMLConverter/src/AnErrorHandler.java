import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class AnErrorHandler implements ErrorHandler {

	@Override
	public void error(SAXParseException arg0) throws SAXException {
	}

	@Override
	public void fatalError(SAXParseException arg0) throws SAXException {
	}

	@Override
	public void warning(SAXParseException arg0) throws SAXException {
	}

}
