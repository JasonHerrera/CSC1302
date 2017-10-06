import java.io.*;
import java.util.*;

public class Relation {
  // Name of the relation.
  private String name;

  // Attribute names for the relation
  private ArrayList<String> attributes;

  // Domain classes or types of attributes; possible values: INTEGER, DECIMAL, VARCHAR
  private ArrayList<String> domains;

  // Actual data storage (list of tuples) for the relation.
  private ArrayList<Tuple> table;

  // METHODS

  // Week 1

  // Constructor; set instance variables
  public Relation (String name, ArrayList<String> attributes, ArrayList<String> dNames) {
	table = new ArrayList<Tuple>();
	this.name = name;
	this.attributes = attributes;
	domains = dNames;	
  }

  // returns true if attribute with name aname exists in relation schema
  // false otherwise
  public boolean attributeExists(String aname) {
	int loc = attributes.indexOf(aname);
	if (loc < 0) {
		return false;
	}
	else {
		return true;
	}
  }

  // returns domain type of attribute aname; return null if not present
  public String attributeType(String aname) {
	if (attributeExists(aname)) {
		return domains.get(attributes.indexOf(aname));
	}
	else {
		return "null";
	}
  }

  // Print relational schema to screen.
  public void displaySchema() {
  	int loc = 0;
  	System.out.print(name + "(");
	for (int i = 0; i < attributes.size() - 1; i++) {
		loc++;
		System.out.print(attributes.get(i) + ":" + domains.get(i) + ",");
	}
	System.out.println(attributes.get(loc) + ":" + domains.get(loc) + ")");
  }

  // Set name of relation to rname
  public void setName(String rname) {
	name = rname;
  }

  // Add tuple tup to relation; Duplicates are fine.
  public void addTuple(Tuple tup) {
	table.add(tup);

  }

  // Print relation to screen (see output of run for formatting)
  public void displayRelation() {
	displaySchema();
	System.out.println("Number of tuples: " + table.size() + "\n");
	for (Tuple t : table) {
		System.out.println(t);
	}
	System.out.println();
  }

  // Return String version of relation; See output of run for format.
  public String toString() {
	String output = name + "(";
	for (int i = 0; i < attributes.size(); i++) {
		output = output + attributes.get(i) + ":" + domains.get(i) + ",";
	}
	output = output.substring(0, output.length() - 1) + ")\nNumber of tuples: " + table.size() + "\n";
	for (Tuple t : table) {
		output = output + "\n" + t;
	}
	return output + "\n";
  }

  // Week 2

  // Remove duplicate tuples from this relation
  public void removeDuplicates() {
	for (int i = 0; i < table.size() - 1; i++) {
		for (int j = i + 1; j < table.size(); j++) {
			if (table.get(i).equals(table.get(j))) {
				table.remove(j);
				j -= 1;
			}
		}
	}
  }

  // Week 4

  // returns true if Tuple t is present in the Relation
  // and false otherwise
  public boolean member(Tuple t) {
 	for (int i = 0; i < this.table.size(); i++) {
		if (t.equals(this.table.get(i)))
			return true;
        }                
	return false;
  }

  // returns the union of two Relations (this and r2)
  // should remove duplicates before returning
  // clone the Tuples from the input Relations and then add to output
  public Relation union(Relation r2) {
  	String newName = this.name + "_UNION_" + r2.name;
  	Relation join = new Relation(newName, this.attributes, this.domains);
	for (int i = 0; i < this.table.size(); i++) {
		Tuple dup = this.table.get(i).clone(this.attributes);
		join.addTuple(dup);
	}
	for (int j = 0; j < r2.table.size(); j++) {
		Tuple dup = r2.table.get(j).clone(r2.attributes);
		join.addTuple(dup);
	}
	join.removeDuplicates();
	return join;
  }

  // returns the intersection of two Relations (this and r2)
  // clone the Tuples from the input Relations and then add to output
  public Relation intersect(Relation r2) {
	String newName = this.name + "_INTERSECT_" + r2.name;
	Relation common = new Relation(newName, this.attributes, this.domains);
	for (int i = 0; i < r2.table.size(); i++) {
		if (this.member(r2.table.get(i))) {
			Tuple dup = r2.table.get(i).clone(this.attributes);
			common.addTuple(dup);
		}
	}
	return common;
  }

  // returns the difference of two Relations (this and r2)
  // clone the Tuples from the input Relations and then add to output
  public Relation minus(Relation r2) {
	String newName = this.name + "_MINUS_" + r2.name;
	Relation diff = new Relation(newName, this.attributes, this.domains);
	for (int i = 0; i < this.table.size(); i++) {
		if (!r2.member(this.table.get(i))) {
			Tuple dup = this.table.get(i).clone(this.attributes);
			diff.addTuple(dup);
		}
  	}
	return diff;
  }

}
