import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandle {
	private String inputString;
	ArrayList<String> inputStringList = new ArrayList<String>();
	
	
	/*
	 * This method is to read the input file of a given name. It returns a String array.
	 * */
	
	public ArrayList<String> readFile(String fname) {
	
        String fileName = fname;
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	inputString=line;
            	inputStringList.add(inputString);
            } 
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
            ex.printStackTrace();
        }
        return inputStringList;
		    
	}
	
	/*This method is to write the output into a file for a given name.
	 * 
	 * */
	
	public void writeFile(String fname,ArrayList<String> lst) {
        String fileName = fname;
        ArrayList<String> outputLst=lst;

        try {
            FileWriter fileWriter =
                new FileWriter(fileName);
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
            
            for (int i=0; i<outputLst.size(); i++) {
            	String OutStr=outputLst.get(i);
            	bufferedWriter.write(OutStr+System.getProperty("line.separator"));
            	
            }
            
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '"+ fileName + "'");
        }
	}
}
