import java.util.*;
import java.io.*;

public class ProbeAnswers
{
  public static Map<String, String> makeRatingDictionary(String mvID,
                                                          String dir) {
    String fileID = mvID;
    while (fileID.length() < 7) {
      fileID = "0" + fileID;
    }
    Map<String, String> ratings = new HashMap<String, String>();
    try {
      FileInputStream fin = new FileInputStream(dir + "/mv_" +
                                                  fileID + ".txt");
      BufferedReader inFile = new BufferedReader(new InputStreamReader(fin));
      inFile.readLine();
      String nLine = inFile.readLine();
      while (nLine != null) {
        String[] lineArray = nLine.split(",");
        ratings.put(lineArray[0], lineArray[1]);
        nLine = inFile.readLine();
      }
      inFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ratings;
  }
  public static void main(String[] args) {
    try {
      FileWriter fout = new FileWriter("actualRatings.txt");
      BufferedWriter outFile = new BufferedWriter(fout);
      FileInputStream fin = new FileInputStream(args[1]);
      BufferedReader inFile = new BufferedReader(new InputStreamReader(fin));
      String nLine = inFile.readLine();
      Map<String, String> ratings = null;
      while (nLine != null) {
        if (nLine.indexOf(":") >= 0) {
          outFile.write(nLine);
          outFile.newLine();
          ratings = makeRatingDictionary(nLine.substring(0, nLine.length()-1),
                                          args[0]);
        }
        else {
          outFile.write(ratings.get(nLine));
          outFile.newLine();
        }
        nLine = inFile.readLine();
      }
      outFile.close();
      fin.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
