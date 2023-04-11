import java.io.*;
import java.util.List;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


public class CSVService {
	
	private static String csv_FILENAME="Properties.csv";
	private static BufferedReader br = null;
	private static String[] Attributes = new String[5];
	private String[] row;
	private char initial; 
	
	public boolean csvRead() {
		try {
			InputStream in = getClass().getResourceAsStream(csv_FILENAME);
			br = new BufferedReader(new InputStreamReader(in));
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader reader = new CSVReaderBuilder(br).withCSVParser(parser).build();
            List<String[]> all = reader.readAll();
            for (int a=1; a<all.size()-1; a++) {
            	row = all.get(a);
            	initial = row[0].charAt(0);
            	for (int i=1; i<row.length-1; i++) {
            		Attributes[i-1] = row[i];
            	}
            	switch (initial) {
	     	        case 'A':
	     	        	EstateAgency.addNewProperty(Attributes);
	     	        	break;
	     	        case 'H':
	     	        	EstateAgency.addNewProperty(Attributes, null);
	     	        	break;
            	}
            }
        }
        catch (FileNotFoundException e){
			System.out.println("Error: Input File "+ csv_FILENAME +" not found. Aborted.");
			return false;
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	        return false;
	    } 
		finally {    
			if(br != null){
				try{
					br.close();
	            } 
				catch(IOException e){
	            	e.printStackTrace();
	            }
	        }
	    }
		return true;	
	}

}