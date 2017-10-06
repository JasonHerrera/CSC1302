import java.util.*;

public class Tuple {

  private ArrayList<String> attributes;
  private ArrayList<String> domains;
  private ArrayList<Comparable> tuple;

  // METHODS
  
  // Constructor; set instance variables
  public Tuple (ArrayList<String> attr, ArrayList<String> dom) {
	tuple = new ArrayList<Comparable>();
	attributes = attr;
	domains = dom;
  }

  // Add String s at the end of the tuple
  public void addStringComponent(String s) {
	tuple.add(s);
  }

  // Add Double d at the end of the tuple
  public void addDoubleComponent(Double d) {
	tuple.add(d);
  }

  // Add Integer i at the end of the tuple
  public void addIntegerComponent(Integer i) {
	tuple.add(i);
  }

  // return String representation of tuple; See output of run for format.
  public String toString() {
	String output = "";
	for (Comparable c : tuple) {
		output = output + c + ":";
	}
	return output;
  }

}
