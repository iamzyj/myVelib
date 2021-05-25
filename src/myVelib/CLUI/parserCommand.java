package myVelib.CLUI;

import java.text.ParseException;

import myVelib.Exceptions.EndPriorToStartException;
import myVelib.Exceptions.InvalidIDException;
import myVelib.Exceptions.NoBikeToReturnException;
import myVelib.Exceptions.NoneParkingSlotException;
import myVelib.Exceptions.RentMoreThanOneBikeException;
import myVelib.Exceptions.VacancyException;
import myVelib.core.VelibSystem;
import myVelib.core.Vlibre;
import myVelib.core.Vmax;
public class parserCommand {
	public static String parseCommand(String[] command,VelibSystem v) throws ParseException, NumberFormatException, InvalidIDException, VacancyException, NoneParkingSlotException, EndPriorToStartException, RentMoreThanOneBikeException, NoBikeToReturnException {
		String returnvalue="";
		if(command[0].equals("setup")) {
			VelibSystem v1=runtest.SystemList.get(command[1]);
			if (command.length<=2) {
//				这几个数字要从ini文件那儿读取?
				returnvalue+=v1.setup(10,10,75);
//				returnvalue+="\n";
			}
			else {
				returnvalue=v1.setup(Integer.parseInt(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4]));
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
