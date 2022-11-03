import java.io.IOException;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class OptionMenu {
		
		private static boolean answer;
		
		public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassCastException {
			EstateAgency estateAgency = new EstateAgency();
			estateAgency.startPropertyTransaction();
			estateAgency.sortProperties();
			while (continued()) {
				estateAgency.sortProperties();
			}
			estateAgency.endPropertyTransaction();
		}
		
		public static boolean continued(){
			int choice = JOptionPane.showConfirmDialog(null,
				"Continue reporting sale objects?",
				"Continue",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
			switch (choice){
				case JOptionPane.YES_OPTION: 
					answer= true;
					return answer;
				case JOptionPane.NO_OPTION: 
					answer= false;
					return answer;
			}
			return answer;
		}
    		
}
