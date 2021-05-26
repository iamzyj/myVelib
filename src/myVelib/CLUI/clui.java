package myVelib.CLUI;

import java.util.*;

import myVelib.Exceptions.*;
import myVelib.core.*;

import java.io.*;
import java.text.ParseException;
//如果有时间，稍微自动化一点的CLUI
//加一个输入的设置，创建文件testSenario0，并自动载入ini文件，接下来输入命令，写到testScenario0文件中，输入runtest就是执行这个文件
//System其实还有面积的设置，应该在coordinates 那里修改
//ser文件可用来存取，并且下一次可以用来读取
public class clui {
	public static Map<String, VelibSystem> SystemList;
	public static void main(String args) {
		
	}
	public static VelibSystem loadingConfiguration() throws IOException, ClassNotFoundException, ParseException {
		Inifile.writeIniFile();
		VelibSystem v=Inifile.readIniFile();
		return v;
	}
	public static String parseCommand(String[] command) throws UnknownCommandException, InvalidIDException, VacancyException, NoneParkingSlotException, EndPriorToStartException, IOException, NoBikeToReturnException, RentMoreThanOneBikeException, ParseException, ClassNotFoundException{
		String returnvalue="";
		if (command[0].equals("runtest")) {
			runtest(command[1]);
		}
		if(command[0].equals("setup")) {
			VelibSystem v=SystemList.get(command[1]);
			if(v!=null) {
				if (command.length<=2) {
//					这几个数字要从ini文件那儿读取?
					returnvalue+=v.setup(10,10,75);
				}
				else {
					try {
					returnvalue=v.setup(Integer.parseInt(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
					System.err.println("the number entered can not be recognized, please follow the standard format");
				}
				}
			}
			else {
				v=new VelibSystem(command[1]);
				if (command.length<=2) {
//					这几个数字要从ini文件那儿读取?
					returnvalue+=v.setup(10,10,75);
				}
				else {
					try {
					returnvalue=v.setup(Integer.parseInt(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4]));
				} catch (NumberFormatException e) {
					System.err.println("Caught NumberFormatException: "+e.getMessage());
				}
				}
				SystemList.put(command[1], v);
			}
		}
		else if(command[0].equals("addUser")) {
			String username=command[1];
			VelibSystem v=SystemList.get(command[3]);
			if(command[2].equals("Vlibre")) {
				Vlibre card=new Vlibre();
				returnvalue+=v.addUser(username, card);
			}
			else if(command[2].equals("Vmax")) {
				Vmax card=new Vmax();
				returnvalue+=v.addUser(username, card);
			}
			else if(command[3].equals("null")){
				returnvalue+=v.addUser(username, null);
			}
		}
		else if(command[0].equals("online")) {
			VelibSystem v=SystemList.get(command[1]);
			try {
			returnvalue+=v.online(Integer.parseInt(command[2]));
			} catch (NumberFormatException e) {
				System.err.println("Caught NumberFormatException: "+e.getMessage());
			}
		}
		else if(command[0].equals("offline")) {
			VelibSystem v=SystemList.get(command[1]);
			try {
			returnvalue+=v.offline(Integer.parseInt(command[2]));
			} catch (NumberFormatException e) {
				System.err.println("Caught NumberFormatException: "+e.getMessage());
			}
		}
		else if(command[0].equals("rentbike")) {
			VelibSystem v=SystemList.get(command[1]);
			try {
			returnvalue+=v.rentbike(Integer.parseInt(command[2]), Integer.parseInt(command[3]));
			} catch (NumberFormatException e) {
				System.err.println("Caught NumberFormatException: "+e.getMessage());
			}
		}
		else if(command[0].equals("returnbike")) {
			VelibSystem v=SystemList.get(command[1]);
			try {
				returnvalue+=v.returnbike(Integer.parseInt(command[2]), Integer.parseInt(command[3]),command[4]);
			} catch (NumberFormatException | ParseException e) {
				System.err.println("Caught NumberFormatException: "+e.getMessage());
			}
		}
		else if(command[0].equals("sortStation")) {
			
		}
//		if save, SystemList will be write be into VelibSystems.ser
		else if(command[0].equals("save")) {
			save();
		}
		else {
			System.err.println("Command "+command[0]+" unknown,please enter a valid one!");
			throw new UnknownCommandException(command[0]);
		}
		return returnvalue;
	}
	public static void runtest(String filename) throws IOException, ClassNotFoundException, UnknownCommandException, ParseException{
		  SystemList=new HashMap<String,VelibSystem>();
		  File f=new File("VelibSystems.ser");
		      String test=readCommand.readTextFile(filename);
		      String [] commands=test.split("[\n]");
		   	  String output="output.txt";
			  String [] origin=filename.split("[.]");
			  String newfilename=origin[0]+output;
			  FileWriter fw = null;
			  File file=null;
			  if(f.exists()) {
				  SystemList=loadSystems();
			  }
			  else {
				  VelibSystem paris=loadingConfiguration();
				  SystemList.put("Paris",paris);  
			  }
			try{ 
				file=new File(newfilename);
				file.createNewFile();
				fw = new FileWriter(file);
				 for (String command:commands) {
					 String [] c=command.split("[ ]");
					 String str=parseCommand(c);
			    	 fw.write(str+"\n\n");
			      }
			}
			//	workspace need to be flushed to show the existence of the newly created file   	     
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
					fw.close();
		
			  }
				
	}
	public static void save() throws IOException{
			FileOutputStream fileOut = new FileOutputStream("VelibSystems.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(SystemList);
			out.close();
			fileOut.close();
			System.out.printf("Systems have been saved in the file \"saveFileVelib.ser\"\n");
	}
	@SuppressWarnings("unchecked")
	public static Map<String, VelibSystem> loadSystems() throws IOException, ClassNotFoundException{
		Map<String, VelibSystem> sysList=new HashMap<String, VelibSystem>();
		FileInputStream filein=new FileInputStream("VelibSystems.ser");
		ObjectInputStream in =new ObjectInputStream(filein);
		sysList=(HashMap<String, VelibSystem>)in.readObject();
		in.close();
		filein.close();
		return sysList;
	}
}
