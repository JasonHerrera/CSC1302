import java.io.*;
import java.util.*;

public class Database {

  private Map<String,Relation> relations;

  // METHODS

  // Week 1

  // Constructor; creates the empty HashMap object
  public Database() {
      relations = new HashMap<String, Relation>();
  }

  // Add relation r with name rname to HashMap
  // if relation does not already exists.
  // Make sure to set the name within r to rname.
  // return true on successful add; false otherwise
  public boolean addRelation(String rname, Relation r) {
      if (relations.containsKey(rname))
	  return false;
      else {
	  r.setName(rname);
	  relations.put(rname, r);
	  return true;
      }
  }

  // Delete relation with name rname from HashMap
  // if relation exists. return true on successful delete; false otherwise
  public boolean deleteRelation(String rname) {
      if (relations.containsKey(rname)) {
	  relations.remove(rname);
	  return true;
      }
      return false;
  }

  // Return true if relation with name rname exists in HashMap
  // false otherwise
  public boolean relationExists(String rname) {
      if (relations.containsKey(rname))
	  return true;
      return false;
  }

  // Retrieve and return relation with name rname from HashMap;
  // return null if it does not exist.
  public Relation getRelation (String rname) {
      if (relationExists(rname))
	  return relations.get(rname);
      return null;
  }

  // Print database schema to screen.
  public void displaySchema() {
      for (Map.Entry<String, Relation> r : relations.entrySet())
	  r.getValue().displaySchema();
      System.out.println();
  }

  // Week 2

  // Create the database object by reading data
  // from several files in directory dir
  public void initializeDatabase(String dir) {
      FileInputStream fin1 = null;
      BufferedReader infile1 = null;
      try {
	  fin1 = new FileInputStream(dir + "/catalog.dat");
	  infile1 = new BufferedReader(new InputStreamReader(fin1));
	  String s1 = infile1.readLine();
	  int c1 = Integer.parseInt(s1);
	  for (int i = 0; i < c1; i++) {
	      String n = infile1.readLine();
	      ArrayList<String> a = new ArrayList<String>();
	      ArrayList<String> d = new ArrayList<String>();
	      String s2 = infile1.readLine();
	      int c2 = Integer.parseInt(s2);
	      for (int j = 0; j < c2; j++) {
		  a.add(infile1.readLine());
		  d.add(infile1.readLine());
	      }
	      Relation rel = new Relation(n, a, d);
	      FileInputStream fin2 = new FileInputStream(dir + "/" + n + ".dat");
	      BufferedReader infile2 = new BufferedReader(new InputStreamReader(fin2));
	      String s3 = infile2.readLine();
	      int c3 = Integer.parseInt(s3);
	      for (int k = 0; k < c3; k++) {
		  Tuple t = new Tuple(a, d);
		  for (String s : d) {
		      if (s.equals("VARCHAR"))
			  t.addStringComponent(infile2.readLine());
		      else if (s.equals("DECIMAL"))
			  t.addDoubleComponent(Double.parseDouble(infile2.readLine()));
		      else if (s.equals("INTEGER"))
			  t.addIntegerComponent(Integer.parseInt(infile2.readLine()));
		  }
		  rel.addTuple(t);
	      }
	      addRelation(n, rel);
	      fin2.close();
	  }
	  fin1.close();
      } catch (IOException e) {
	  System.out.println("Error reading file");
      }
  }

}
