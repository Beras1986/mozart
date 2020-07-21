package netcore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Piotr Berger E5109181
 * version 1.0
 * June 2019
 */
public class DataSet {

    // Instance variables
    private String path;
    private String fileName;
    
    // Constructor
    public DataSet(String aPath, String aFileName){
    
        this.path = aPath;
        this.fileName = aFileName;
    }
    
    // Methods
    
    public double[][] csvTo2dTable(DataSet csv) throws IOException {

	String thePath = getAbsolutePath(csv);

	BufferedReader bufferedReader = new BufferedReader(new FileReader(thePath));
		
	try {
		StringBuilder stringBuilder = new StringBuilder();
		String line = bufferedReader.readLine();
                line = bufferedReader.readLine();
                
		int columns = line.split(",").length;
		int rows = 0;
                        
		while (line != null) {
			stringBuilder.append(line);
			stringBuilder.append(System.lineSeparator());
			line = bufferedReader.readLine();
			rows++;
		}
			
		double table[][] = new double[rows][columns];
		String everything = stringBuilder.toString();
			
		Scanner scanner = new Scanner(everything);
		rows = 0;
		while(scanner.hasNextLine()){
			String[] strVector = scanner.nextLine().split(",");
			for (int i = 0; i < strVector.length; i++) {
                            try{
                               table[rows][i] = Double.parseDouble(strVector[i]); 
                            }catch(Exception anException){
                                //
                            }
			}
			rows++;
		}
		scanner.close();
			
		return table;

	} finally {
		bufferedReader.close();
	}

    }
    
    private String getAbsolutePath(DataSet csv){
        
        String absolutePath = "";
        String directory = System.getProperty("user.dir");
        absolutePath = directory + "\\" + csv.getPath() + "\\" + csv.getFileName();
        
        return absolutePath;
    }
    
    public double[][] scalTable(double[][] table) {
		
	int rows = table.length;
	int cols = table[0].length;
		
	double[][] scaledTab = new double[rows][cols];
		
	for (int j = 0; j < cols; j++) {
			
		ArrayList<Double> listColumn = new ArrayList<>();
			
		for (int i = 0; i < rows; i++) {
			listColumn.add( table[i][j] );
		}
			
		double minValue = Collections.min(listColumn);
		double maxValue = Collections.max(listColumn);
			
			
		for (int i = 0; i < rows; i++) {
				
                    scaledTab[i][j] = (table[i][j] - minValue) / (maxValue - minValue);
                }
				
	}  
	return scaledTab;	
    }   
    
    public double[][] unscalTable(double[][] table, double[][] scaledTab) {
		
	int rows = scaledTab.length;
	int cols = scaledTab[0].length;
		
	double[][] unscaledTab = new double[rows][cols];
		
	for (int j = 0; j < cols; j++) {
			
		ArrayList<Double> listColumn = new ArrayList<>();
			
		for (int i = 0; i < rows; i++) {
			listColumn.add( table[i][j] );
		}
			
		double minValue = Collections.min(listColumn);
		double maxValue = Collections.max(listColumn);
			
		for (int i = 0; i < rows; i++) {
			
                    unscaledTab[i][j] = (scaledTab[i][j] * (maxValue - minValue)) + minValue;	
		}
			
	}
		
	return unscaledTab;
		
    }
    
    
    public String getPath(){
        
        return this.path;
    }
    
    public void setPath(String aPath){
        
        this.path = aPath;
    }
    
    public String getFileName(){
        
        return this.fileName;
    }
    
    public void setFileName(String aFileName){
        
        this.fileName = aFileName;
    }
    
}
