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
	public static VelibSystem loadingConfiguration() throws IOException, ClassNotFoundException {
		Inifile.writeIniFile();
		VelibSystem v=Inifile.readIniFile();
		return v;
	}
	public static void main(final String...arguments) throws IOException, ParseException, ClassNotFoundException{
		  if (arguments.length < 2)
		   {
		      System.out.println("filename is not provided");
		   }
		   else
		   {  String filename=arguments[1];
		      String test=readCommand.readTextFile(filename);
		      String [] commands=test.split("[\n]");
		   	  String output="output.txt";
			  String [] origin=filename.split("[.]");
			  String newfilename=origin[0]+output;
			  FileWriter fw = null;
			  File file=null;
			  HashMap<String,VelibSystem> SystemList=new HashMap<String,VelibSystem>();
			  VelibSystem paris=loadingConfiguration();
			  SystemList.put("Paris",paris);
			  VelibSystem v=new VelibSystem("v");
			try{ 
				file=new File(newfilename);
				file.createNewFile();
				fw = new FileWriter(file);
				 for (String command:commands) {
					 String [] c=command.split("[ ]");
					 String str=parseCommand.parserCommand(c, v);
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
