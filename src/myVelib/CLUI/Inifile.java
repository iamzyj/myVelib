package myVelib.CLUI;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import myVelib.core.*;

public class Inifile {
	public static void writeIniFile() throws IOException, ParseException {
		String filename="my_velib.ini";
		VelibSystem v=velibConfiguraiton();
		File f=new File(filename);
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileOutputStream file = new FileOutputStream("my_velib.ini");
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(v);
		out.close();
	}
	public static VelibSystem velibConfiguraiton() throws ParseException {
		VelibSystem v=new VelibSystem("Paris");
		v.setup(10, 10, 70);
		return v;
	}
	public static VelibSystem readIniFile() throws IOException, ClassNotFoundException {
		VelibSystem v=null;
		FileInputStream fileIn = new FileInputStream("my_velib.ini");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		v = (VelibSystem) in.readObject(); // requires casting into Employee type
		in.close();
		fileIn.close();
		return v;
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
		Inifile.writeIniFile();
		VelibSystem v=readIniFile();
		System.out.println(v.getBicycles().get(1));
	}
	

}
