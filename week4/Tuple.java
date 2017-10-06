import java.util.*;

public class Tuple {

  private ArrayList<String> attributes;
  private ArrayList<String> domains;
  private ArrayList<Comparable> tuple;

  // METHODS

  // Week 1
  
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

  // Week 2

  // Return true if this tuple is
  // equal to compareTuple; false otherwise
  public boolean equals(Tuple compareTuple) {
  	if (compareTuple.tuple.size() != this.tuple.size())
		return false;
	for (int i = 0; i < this.tuple.size(); i++) {
		if (!this.tuple.get(i).equals(compareTuple.tuple.get(i)))
			return false;
	}
	return true;
  }

  // Week 4

  // creates a new copy of the Tuple and returns it
  public Tuple clone(ArrayList<String> attr) {
	Tuple copy = new Tuple(attr, domains);
	for (int i = 0; i < tuple.size(); i++) {
		switch(this.domains.get(i)) {
			case "VARCHAR":
				String s = new String((String) tuple.get(i));
				copy.addStringComponent(s);
				break;
			case "DECIMAL":
				Double d = new Double((Double) tuple.get(i));
				copy.addDoubleComponent(d);
				break;
			case "INTEGER":
				Integer n = new Integer((Integer) tuple.get(i));
				copy.addIntegerComponent(n);
				break;
		}
	}
	return copy;
  }
  
}
