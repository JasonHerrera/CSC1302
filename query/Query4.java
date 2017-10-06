import java.util.*;
import java.io.*;

public class Query4
{
  public static void main(String[] args) {
    Database db = new Database();
    db.initializeDatabase(args[0]);

    Relation r1 = db.getRelation("EMPLOYEE");
    ArrayList<String> nameCol1 = new ArrayList<String>();
    nameCol1.addAll(Arrays.asList("DNAME", "DNUMBER", "SSN", "MGRSTARTDATE"));
    Relation r2 = db.getRelation("DEPARTMENT").rename(nameCol1);
    Relation newRel = r1.join(r2);
    newRel = newRel.select("col", "LNAME", "=", "str", "Smith");

    ArrayList<String> projCol1 = new ArrayList<String>();
    projCol1.add("DNUMBER");
    ArrayList<String> nameCol2 = new ArrayList<String>();
    nameCol2.add("DNUM");
    newRel = newRel.project(projCol1).rename(nameCol2);
    Relation r3 = db.getRelation("PROJECTS");

    ArrayList<String> projCol2 = new ArrayList<String>();
    projCol2.add("PNUMBER");
    newRel = newRel.join(r3).project(projCol2);

    ArrayList<String> projCol3 = new ArrayList<String>();
    projCol3.add("SSN");
    ArrayList<String> nameCol3 = new ArrayList<String>();
    nameCol3.add("ESSN");
    ArrayList<String> projCol4 = new ArrayList<String>();
    projCol4.add("PNO");
    Relation r4 = db.getRelation("EMPLOYEE").select("col", "LNAME", "=", "str",
                                                "Smith");
    r4 = r4.project(projCol3).rename(nameCol3);
    r4 = r4.join(db.getRelation("WORKS_ON")).project(projCol4);

    newRel = r4.union(newRel);
    newRel.setName("ANSWER");
    newRel.displayRelation();
  }
}
