import java.util.*;
import java.io.*;

public class Query1
{
  public static void main(String[] args) {
    Database db = new Database();
    db.initializeDatabase(args[0]);
    ArrayList<String> newAttr = new ArrayList<String>();
    newAttr.addAll(Arrays.asList("DNAME", "DNO", "MGRSSN", "MGRSTARTDATE"));
    Relation newRel = db.getRelation("DEPARTMENT").select("col", "DNAME", "=",
                                                          "str", "Research");
    newRel = newRel.rename(newAttr);
    newRel = newRel.join(db.getRelation("EMPLOYEE"));

    ArrayList<String> projCol = new ArrayList<String>();
    projCol.addAll(Arrays.asList("FNAME", "LNAME", "ADDRESS"));
    newRel = newRel.project(projCol);
    newRel.setName("ANSWER");
    newRel.displayRelation();
  }
}
