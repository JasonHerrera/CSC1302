import java.util.*;
import java.io.*;

public class Query6
{
  public static void main(String[] args) {
    Database db = new Database();
    db.initializeDatabase(args[0]);
    ArrayList<String> col1 = new ArrayList<String>();
    col1.add("SSN");
    ArrayList<String> col2 = new ArrayList<String>();
    col2.add("ESSN");
    Relation r1 = db.getRelation("EMPLOYEE").project(col1);
    Relation r2 = db.getRelation("DEPENDENT").project(col2);
    Relation newRel = r1.minus(r2);
    newRel = newRel.join(db.getRelation("EMPLOYEE"));
    ArrayList<String> projCol = new ArrayList<String>();
    projCol.addAll(Arrays.asList("LNAME", "FNAME"));
    newRel = newRel.project(projCol);
    newRel.setName("ANSWER");
    newRel.displayRelation();
  }
}
