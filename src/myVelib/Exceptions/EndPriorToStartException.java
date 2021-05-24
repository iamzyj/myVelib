package myVelib.Exceptions;

import java.util.*;

public class EndPriorToStartException extends Exception {
 private String date;
 public EndPriorToStartException(String date) {
	 super();
	 System.err.println("The endtime "+date+" is set before the starttime, please specify a correct endtime");
 }
}
