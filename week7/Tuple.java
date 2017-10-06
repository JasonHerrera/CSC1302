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
      copy.tuple.addAll(this.tuple);
      return copy;
  }

  // Week 6

  // This method combines two Tuples into one and assigns a new schema
  // to the result Tuple; the method returns the new Tuple
  public Tuple concatenate(Tuple t2,ArrayList<String> attrs,ArrayList<String> doms){
      Tuple dup1 = this.clone(this.attributes);
      Tuple dup2 = t2.clone(t2.attributes);

      Tuple result = new Tuple(attrs, doms);
      result.tuple.addAll(dup1.tuple);
      result.tuple.addAll(dup2.tuple);

      return result;
  }

  // Week 7
  
  // takes an ArrayList of column names, each belonging to this.attributes
  // returns a new Tuple with only those columns in cnames
  public Tuple project(ArrayList<String> cnames) {
      ArrayList<String> newAttr = cnames;
	
      ArrayList<String> newDoms = new ArrayList<String>();
      for (int i = 0; i < cnames.size(); i++) {
	  for (int j = 0; j < domains.size(); j++) {
	      if (cnames.get(i).equals(attributes.get(j))) {
		  newDoms.add(domains.get(j));
		  break;
	      }
	  }
      }
	
      Tuple newTuple = new Tuple(newAttr, newDoms);
      for (int i = 0; i < cnames.size(); i++) {
	  for (int j = 0; j < tuple.size(); j++) {
	      if (cnames.get(i).equals(attributes.get(j))) {
		  newTuple.tuple.add(this.tuple.get(j));
		  break;
	      }
	  }
      }
      return newTuple;			
  }

}
