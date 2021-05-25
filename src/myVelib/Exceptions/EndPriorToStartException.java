package myVelib.Exceptions;

import java.util.*;

public class EndPriorToStartException extends Exception {
	private static final long serialVersionUID = -3149321639489051551L;
private String date;
 public EndPriorToStartException(String date) {
	 super();
	 System.err.println("The endtime "+date+" is set before the starttime, please specify a correct endtime");
 }
}
