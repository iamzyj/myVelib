package myVelib.CLUI;

import java.util.*;
import myVelib.core.*;

import java.io.*;
import java.text.ParseException;
//如果有时间，稍微自动化一点的CLUI
//加一个输入的设置，创建文件testSenario0，并自动载入ini文件，接下来输入命令，写到testScenario0文件中，输入runtest就是执行这个文件
//System其实还有面积的设置，应该在coordinates 那里修改
//addUser应该来一个coordinates
public class runtest {
	public static String parserCommand(String[] command,VelibSystem v) throws ParseException {
		String returnvalue="";
		if(command[0].equals("setup")) {
			if (command.length<2) {
//				这几个数字要从ini文件那儿读取?
				returnvalue+=v.setup(10,10,75);
//				returnvalue+="\n";
			}
			else {
				returnvalue=v.setup(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Integer.parseInt(command[3]));
			}
		}
		if(command[0].equals("addUser")) {
			String username=command[1];
			if(command[2].equals("Vlibre")) {
				Vlibre card=new Vlibre();
				returnvalue+=v.addUser(username, card);
			}
			else if(command[2].equals("Vmax")) {
				Vmax card=new Vmax();
				returnvalue+=v.addUser(username, card);
			}
			else {
				returnvalue+=v.addUser(username, null);
			}
		}
		if(command[0].equals("online")) {
			returnvalue+=v.online(Integer.parseInt(command[1]));
		}
		if(command[0].equals("offline")) {
			returnvalue+=v.offline(Integer.parseInt(command[1]));
		}
		if(command[0].equals("rentbike")) {
			returnvalue+=v.rentbike(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
		}
		if(command[0].equals("returnbike")) {
			returnvalue+=v.returnbike(Integer.parseInt(command[1]), Integer.parseInt(command[2]),command[3]);
		}
		
		return returnvalue;
	}
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
	public static void main(final String...arguments) throws IOException, ParseException{
		  if (arguments.length < 2)
		   {
		      System.out.println("filename is not provided");
		   }
		   else
		   {  String filename=arguments[1];
		      String test=runtest.readTextFile(filename);
		      String [] commands=test.split("[\n]");
		   	  String output="output.txt";
			  String [] origin=filename.split("[.]");
			  String newfilename=origin[0]+output;
			  FileWriter fw = null;
			  File file=null;
			  VelibSystem v=new VelibSystem();
			try{ 
				file=new File(newfilename);
				file.createNewFile();
				fw = new FileWriter(file);
				 for (String command:commands) {
					 String [] c=command.split("[ ]");
					 String str=runtest.parserCommand(c, v);
			    	 fw.write(str+"\n\n");
			      }
			}
			//	workspace need to be flushed to show the existence of the newly created file   	     
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				try{
					fw.close();
				} 
				catch(IOException e){
					e.printStackTrace();
				}
			  }
			}
				
	}
}
