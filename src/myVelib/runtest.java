package myVelib;

import java.util.*;
import java.io.*;

public class runtest {
	public static String readTextFile(String fileName) {
		  
		  String returnValue = "";
		  FileReader file = null;
		  BufferedReader reader = null;
		  
		  try {
			  // open input stream pointing at fileName
			  file = new FileReader(fileName);
			  
			  // open input buffered reader to read file line by line
			  reader = new BufferedReader(file);
			  String line = "";
			  
			  // reading input file line by line
			  while ((line = reader.readLine()) != null) {
				  returnValue += line + "\n";
			  }
		  } catch (Exception e) {
		      throw new RuntimeException(e);
		  } finally {
		    if (file != null) {
		      try {
		        file.close();
		        reader.close();
		       
		      } catch (IOException e) {
		    	  System.out.println("File not found: " + fileName);
		        // Ignore issues during closing 
		      }
		    }
		  }
		  return returnValue;
		} 
	public static void parser() {
		
	}
	public static void main(final String...arguments){
		  if (arguments.length < 2)
		   {
		      System.out.println("\nXML file path/name and XSD file path/name not provided.\n");
		      System.out.println("\tUSAGE: TwoArgsMain <xmlFilePathAndName> <xsdFilePathAndName>");
		   }
		   else
		   {  String filename=arguments[1];
		   	  String output="output.txt";
			  String [] origin=filename.split("[.]");
			  String newfilename=origin[0]+output;
		      System.out.println("The command is "+arguments[0]+"The provided file is " + arguments[1]+newfilename);
		   }
		
	}
}
