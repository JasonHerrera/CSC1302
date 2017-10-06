import java.util.*;
import java.io.*;

public class Query5
{
  public static void main(String[] args) {
    Database db = new Database();
    db.initializeDatabase(args[0]);

    ArrayList<String> projCol1 = new ArrayList<String>();
    projCol1.addAll(Arrays.asList("ESSN", "DEPENDENT_NAME"));
    ArrayList<String> nameCol1 = new ArrayList<String>();
    nameCol1.addAll(Arrays.asList("ESSN1", "DNAME1"));
    Relation r1 = db.getRelation("DEPENDENT").project(projCol1).rename(
                                                                  nameCol1);

    ArrayList<String> projCol2 = new ArrayList<String>();
    projCol2.addAll(Arrays.asList("ESSN", "DEPENDENT_NAME"));
    ArrayList<String> nameCol2 = new ArrayList<String>();
    nameCol2.addAll(Arrays.asList("ESSN2", "DNAME2"));
    Relation r2 = db.getRelation("DEPENDENT").project(projCol2).rename(
                                                                  nameCol2);

    Relation newRel = r1.times(r2);
    newRel = newRel.select("col", "ESSN1", "=", "col", "ESSN2");
    newRel = newRel.select("col", "DNAME1", "<>", "col", "DNAME2");

    ArrayList<String> projCol3 = new ArrayList<String>();
    projCol3.add("ESSN1");
    ArrayList<String> nameCol3 = new ArrayList<String>();
    nameCol3.add("SSN");
    newRel = newRel.project(projCol3).rename(nameCol3);
    newRel = newRel.join(db.getRelation("EMPLOYEE"));

    ArrayList<String> projCol4 = new ArrayList<String>();
    projCol4.addAll(Arrays.asList("LNAME", "FNAME"));
    newRel = newRel.project(projCol4);

    newRel.setName("ANSWER");
    newRel.displayRelation();
  }
}
