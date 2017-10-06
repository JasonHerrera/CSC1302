import java.util.*;
import java.io.*;

public class Query2
{
  public static void main(String[] args) {
    Database db = new Database();
    db.initializeDatabase(args[0]);
    ArrayList<String> newAttr = new ArrayList<String>();
    newAttr.addAll(Arrays.asList("DNAME", "DNUM", "SSN", "MGRSTARTDATE"));
    Relation r1 = db.getRelation("PROJECTS").select("col", "PLOCATION", "=",
                                                    "str", "Stafford");
    Relation r2 = db.getRelation("DEPARTMENT").rename(newAttr);
    Relation newRel = r1.join(r2);
    newRel = newRel.join(db.getRelation("EMPLOYEE"));
    ArrayList<String> projCol = new ArrayList<String>();
    projCol.addAll(Arrays.asList("PNUMBER", "DNUM", "LNAME",
                                  "ADDRESS", "BDATE"));
    newRel = newRel.project(projCol);
    newRel.setName("ANSWER");
    newRel.displayRelation();
  }
}
