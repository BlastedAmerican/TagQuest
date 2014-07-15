package csvLoader;

import java.util.*;
import java.io.*;

public class FileLoader {

	public FileLoader()
	{
		// TODO Auto-generated constructor stub
	}
	/*
	 * Reads a CSV File and attempts to 
	 */
	public Vector<Vector<String>> readNewCSV( String fileName )
	{
		//Make a new file from filename
		File file = new File( fileName );
		//Make a new Vector of Vectors.
        Vector<Vector<String>> csvValues = new Vector<Vector<String>>();
		//Make a new scanner.
        Scanner scanFile;
        Scanner scanLine;	
        try
        {
        	//Try to scan the file.
            scanFile = new Scanner( file ).useDelimiter("\t");
        } 
        catch ( IOException e )
        {
            System.err.println( "LineInput Error: " + e.getMessage() );
            return null;
        }
        while ( scanFile.hasNextLine() )
        {
        	String nextLine = scanFile.nextLine();
        	scanLine = new Scanner(nextLine);
        	//Make a new Vector of strings.
        	Vector<String> csvLine = new Vector<String>();
        	
        	csvValues.add(csvLine);
        	
        	while ( scanLine.hasNext())
        	{
        		csvLine.add(scanLine.next());
        		
        	}
        	scanLine.close();
        	
        }  
        
		
		return csvValues;
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		FileLoader testLoad = new FileLoader();
		Vector<Vector<String>> output = testLoad.readNewCSV("Data/tagQuestTestExport1.csv");
		for( int x = 0; x < output.size(); x++ )
		{
			Vector<String> line = output.get(x);
			//System.out.println("NEXT");
			for( int y = 0; y < line.size(); y++ )
			{
				System.out.println(line.get(y));
			}
		}
		

	}
	
	

}
