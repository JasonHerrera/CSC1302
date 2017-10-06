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

  // Week 8

  // This method takes a comparison condition in the 5 parameters and
  // returns true if the tuple satisfies the condition and false otherwise
  public boolean select(String lopType, String lopValue, String comparison,
  			String ropType, String ropValue) {
      if (lopType.equals("num") && ropType.equals("num")) {
          switch (comparison) {
	      case "=": 
	          if (lopValue.equals(ropValue))
		      return true;
		  break;
	      case "<>":
	          if (lopValue.equals(ropValue))
		      return true;
		  break;
	      case ">":
	          if (lopValue.equals(ropValue))
		      return true;
		  break;
	      case "<":
	          if (lopValue.equals(ropValue))
		      return true;
		  break;
	      case ">=":
	          if (lopValue.equals(ropValue))
		      return true;
		  break;
	      case "<=":
	          if (lopValue.equals(ropValue))
		      return true;
		  break;
	  }
	  return false;
      }
      else if (lopType.equals("str") && ropType.equals("str")) {
          int comp = lopValue.compareTo(ropValue);
	  switch (comparison) {
	      case "=":
	          if (comp == 0)
		      return true;
		  break;
	      case "<>":
	          if (comp != 0)
		      return true;
		  break;
	      case ">":
	          if (comp > 0)
		      return true;
		  break;
	      case "<":
	          if (comp < 0)
		      return true;
		  break;
	      case ">=":
	          if (comp >= 0)
		      return true;
		  break;
	      case "<=":
	          if (comp <= 0)
		      return true;
		  break;
	  }
	  return false;
      }
      else if (lopType.equals("col") && ropType.equals("num")) {
          int spot = attributes.indexOf(lopValue);
	  if (domains.get(spot).equals("INTEGER")) {
	      int comp = ((Integer)tuple.get(spot)).compareTo(Integer.parseInt(ropValue));
	      switch (comparison) {
	          case "=":
	              if (comp == 0)
		          return true;
		      break;
	          case "<>":
	              if (comp != 0)
		          return true;
		      break;
	          case ">":
	              if (comp > 0)
		          return true;
		      break;
	          case "<":
	              if (comp < 0)
		          return true;
		      break;
	          case ">=":
	              if (comp >= 0)
		          return true;
		      break;
	          case "<=":
	              if (comp <= 0)
		          return true;
		      break;
	      }
	      return false;
	  } 
          else if (domains.get(spot).equals("DECIMAL")) {
              int comp = ((Double)tuple.get(spot)).compareTo(Double.parseDouble(ropValue));
              switch (comparison) {
                  case "=":
                      if (comp == 0)
                          return true;
		      break;
                  case "<>":
                      if (comp != 0)
                          return true;
		      break;
                  case ">":
                      if (comp > 0)
                          return true;
		      break;
                  case "<":
                      if (comp < 0)
                          return true;
		      break;
                  case ">=":
                      if (comp >= 0)
                          return true;
		      break;
                  case "<=":
                      if (comp <= 0)
                          return true;
		      break;
              }
              return false;
          }
      }
      else if (lopType.equals("col") && ropType.equals("str")) {
          int spot = attributes.indexOf(lopValue);
	  int comp = ((String)tuple.get(spot)).compareTo(ropValue);
	  switch (comparison) {
	      case "=":
	          if (comp == 0)
		      return true;
		  break;
	      case "<>":
	          if (comp != 0)
		      return true;
		  break;
	      case ">":
	          if (comp > 0)
		      return true;
		  break;
	      case "<":
	          if (comp < 0)
		      return true;
		  break;
	      case ">=":
	          if (comp >= 0)
		      return true;
		  break;
	      case "<=":
	          if (comp <= 0)
		      return true;
		  break;
	  }
	  return false;
      }
      else if (lopType.equals("num") && ropType.equals("col")) {
          int spot = attributes.indexOf(ropValue);
	  if (domains.get(spot).equals("INTEGER")) { 
	      int lop = Integer.parseInt(lopValue);
	      int rop = ((Integer)tuple.get(spot));
	  
              switch (comparison) {
                  case "=":
                      if (lop == rop)
                          return true;
		      break;
                  case "<>":
                      if (lop != rop)
                          return true;
		      break;
                  case ">":
                      if (lop > rop)
                          return true;
		      break;
                  case "<":
                      if (lop < rop)
                          return true;
		      break;
                  case ">=":
                      if (lop >= rop)
                          return true;
		      break;
                  case "<=":
                      if (lop <= rop)
                          return true;
		      break;
              }
	      return false;
	  }
	  else if (domains.get(spot).equals("DECIMAL")) {
	      double lop = Double.parseDouble(lopValue);
	      double rop = ((Double)tuple.get(spot));
	  
	      switch (comparison) {
	          case "=":
	              if (lop == rop)
	 	          return true;
	 	      break;
	          case "<>":
	              if (lop != rop)
		          return true;
		      break;
	          case ">":
	              if (lop > rop)
		          return true;
		      break;
	          case "<":
	              if (lop < rop)
		          return true;
		      break;
	          case ">=":
	              if (lop >= rop)
		          return true;
		      break;
	          case "<=":
	              if (lop <= rop)
		          return true;
		      break;
	      }
	      return false;
	  }
      }
      else if (lopType.equals("str") && ropType.equals("col")) {
          int spot = attributes.indexOf(ropValue);
	  int comp = lopValue.compareTo(((String)tuple.get(spot)));
	  switch (comparison) {
	      case "=":
	          if (comp == 0)
		      return true;
		  break;
	      case "<>":
	          if (comp != 0)
		      return true;
		  break;
	      case ">":
	          if (comp > 0)
		      return true;
		  break;
	      case "<":
	          if (comp < 0)
		      return true;
		  break;
	      case ">=":
	          if (comp >= 0)
		      return true;
		  break;
	      case "<=":
	          if (comp <= 0)
		      return true;
		  break;
	  }
	  return false;
      }
      else if (lopType.equals("col") && ropType.equals("col")) {
          int lSpot = attributes.indexOf(lopValue);
	  int rSpot = attributes.indexOf(ropValue);
	  int comp = tuple.get(lSpot).compareTo(tuple.get(rSpot));
	  switch (comparison) {
	      case "=":
	          if (comp == 0)
		      return true;
		  break;
	      case "<>":
	          if (comp != 0)
		      return true;
		  break;
	      case ">":
	          if (comp > 0)
		      return true;
		  break;
	      case "<":
	          if (comp < 0)
		      return true;
		  break;
	      case ">=":
	          if (comp >= 0)
		      return true;
		  break;
	      case "<=":
	          if (comp <= 0)
		      return true;
		  break;
	  }
	  return false;
      }
      return false;
  }

}
