import java.util.*;
import java.io.*;

public class Query3
{
  public static void main(String[] args) {
    Database db = new Database();
    db.initializeDatabase(args[0]);

    ArrayList<String> projCol1 = new ArrayList<String>();
    projCol1.add("SSN");
    ArrayList<String> projCol2 = new ArrayList<String>();
    projCol2.add("PNUMBER");
    Relation r1 = db.getRelation("EMPLOYEE").project(projCol1);
    Relation r2 = db.getRelation("PROJECTS").select("col", "DNUM", "=", "num",
                                                      "5").project(projCol2);

    Relation newRel = r1.times(r2);
    ArrayList<String> projCol3 = new ArrayList<String>();
    projCol3.addAll(Arrays.asList("ESSN","PNO"));
    ArrayList<String> nameCol = new ArrayList<String>();
    nameCol.addAll(Arrays.asList("SSN", "PNUMBER"));
    Relation r3 = db.getRelation("WORKS_ON").project(projCol3).rename(nameCol);
    newRel = newRel.minus(r3).project(projCol1);
    newRel = r1.minus(newRel);

    ArrayList<String> projCol4 = new ArrayList<String>();
    projCol4.addAll(Arrays.asList("LNAME", "FNAME"));
    newRel = db.getRelation("EMPLOYEE").join(newRel).project(projCol4);

    newRel.setName("ANSWER");
    newRel.displayRelation();
  }
}
