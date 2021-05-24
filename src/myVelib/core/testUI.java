package myVelib.core;

public class testUI {
	public static void main(final String[] arguments)
	{
	   if (arguments.length < 1)
	   {
	      System.out.println("\nNo file path/name provided; please provide a file path/name.\n");
	      System.out.println("\tUSAGE: SeriesExample <filePathAndName> [-v|--verbose]");
	   }
	   else
	   {
	      final String file = arguments[0];
	      final String verboseString = arguments.length > 1 ? arguments[1] : "";
	      final boolean verbose = verboseString.equals("-v") || verboseString.equals("--verbose");
	      System.out.println("File path/name is '" + file + "' and verbosity is " + verbose);
	   }
	}
}
