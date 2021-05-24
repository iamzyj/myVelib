package myVelib.CLUI;

import java.util.*;
import myVelib.core.*;

import java.io.*;
import java.text.ParseException;
//�����ʱ�䣬��΢�Զ���һ���CLUI
//��һ����������ã������ļ�testSenario0�����Զ�����ini�ļ����������������д��testScenario0�ļ��У�����runtest����ִ������ļ�
//System��ʵ������������ã�Ӧ����coordinates �����޸�
//addUserӦ����һ��coordinates
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
