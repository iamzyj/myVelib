package myVelib.CLUI;

import java.text.ParseException;

import myVelib.core.VelibSystem;
import myVelib.core.Vlibre;
import myVelib.core.Vmax;

public class parseCommand {
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
}
