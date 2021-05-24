package myVelib.CLUI;

import java.io.*;
import java.util.*;
import myVelib.core.*;

public class Inifile {
	public static void writeIniFile() throws IOException {
		String filename="myVelib.ini";
		VelibSystem v=velibConfiguraiton();
		File f=new File(filename);
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileOutputStream file = new FileOutputStream("myVelib.ini");
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(v);
		out.close();
	}
	public static VelibSystem velibConfiguraiton() {
		VelibSystem v=new VelibSystem("Paris");
		v.setup(10, 10, 70);
		return v;
	}
	public static VelibSystem readIniFile() throws IOException, ClassNotFoundException {
		VelibSystem v=null;
		FileInputStream fileIn = new FileInputStream("myVelib.ini");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		v = (VelibSystem) in.readObject(); // requires casting into Employee type
		in.close();
		fileIn.close();
		return v;
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Inifile.writeIniFile();
		VelibSystem v=readIniFile();
		System.out.println(v.getBicycles().get(1));
	}
	

}
