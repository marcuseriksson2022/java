import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class CSVService {
	
	private static String csv_FILENAME="Properties.csv";
	private static BufferedReader br = null;
	private String splitter = ";";
	private static String[] Attributes = {null, null, null, null, null};
	
	public boolean csvRead() {
		try {
			InputStream in = getClass().getResourceAsStream(csv_FILENAME);
			if(in == null){
                throw new FileNotFoundException();
            }
			br = new BufferedReader(new InputStreamReader(in));
			String rowOne = br.readLine();
			
			if (rowOne != null) {
				addToPeople(rowOne);
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
	
	public void addToPeople(String fr) {
		try {
		    String fileContent;
		    while ((fileContent = br.readLine()) != null) {
		    	addElements(fileContent);
		    }
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addElements(String fileContent) {
		ArrayList<String> nodes = new ArrayList<String>(Arrays.asList(fileContent.split(splitter)));
		char initial;
        int i = 0;
        initial = nodes.get(i).charAt(0);
        switch (initial) {
	        case 'A':
	        	for (int a=0; a<Attributes.length; a++) {
	        		Attributes[a] = nodes.get(a + 1);
	        	}
	        	EstateAgency.addNewProperty(Attributes);
	        	break;
	        case 'H':
	        	for (int a=0; a<Attributes.length-1; a++) {
	        		Attributes[a] = nodes.get(a + 1);
	        	}
	        	EstateAgency.addNewProperty(Attributes, null);
	        	break;
        }
		i++;	
	}

}