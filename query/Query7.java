import java.util.*;
import java.io.*;

public class Query7
{
  public static void main(String[] args) {
    Database db = new Database();
    db.initializeDatabase(args[0]);

    ArrayList<String> projCol1 = new ArrayList<String>();
    projCol1.add("MGRSSN");
    ArrayList<String> nameCol1 = new ArrayList<String>();
    nameCol1.add("SSN");
    Relation r1 = db.getRelation("DEPARTMENT").project(projCol1).rename(
                                                                  nameCol1);

    ArrayList<String> projCol2 = new ArrayList<String>();
    projCol2.add("ESSN");
    Relation r2 = db.getRelation("DEPENDENT").project(projCol2).rename(
                                                                  nameCol1);

    Relation newRel = r1.join(r2);
    newRel = newRel.join(db.getRelation("EMPLOYEE"));

    ArrayList<String> projCol3 = new ArrayList<String>();
    projCol3.addAll(Arrays.asList("LNAME", "FNAME"));
    newRel = newRel.project(projCol3);

    newRel.setName("ANSWER");
    newRel.displayRelation();
  }
}
