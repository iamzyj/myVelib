package myVelib.Exceptions;

import java.util.*;

public class EndPriorToStartException extends Exception {
 private Date date;
 public EndPriorToStartException() {
	 super();
	 System.err.println("The endtime is set before the starttime, please specify a correct endtime");
 }
}
