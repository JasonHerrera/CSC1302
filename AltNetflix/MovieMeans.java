import java.util.*;
import java.io.*;

public class MovieMeans
{
  public static Map<String, Double> makeRatingDictionary(String dir) {
    Map<String, Double> ratings = new HashMap<String, Double>();
    File dirName = new File(dir);
    File[] dirFiles = dirName.listFiles();
    AllMean mean = new AllMean(dir);
    for (File f : dirFiles) {
      try {
        FileInputStream fin = new FileInputStream(f);
        BufferedReader inFile = new BufferedReader(new InputStreamReader(fin));
        String mvNum = inFile.readLine();
        String nLine = inFile.readLine();
        double tot = 0;
        int num = 0;
        while (nLine != null) {
          String[] lineArray = nLine.split(",");
          tot += Double.parseDouble(lineArray[1]) - mean.getMean();
          num++;
          nLine = inFile.readLine();
        }
        Double totRating = tot/num;
        ratings.put(mvNum, totRating);
        fin.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return ratings;
  }
  public static void main(String[] args) {
    try {
      FileWriter fout = new FileWriter("movieMeans.txt");
      BufferedWriter outFile = new BufferedWriter(fout);
      Map<String, Double> ratings = makeRatingDictionary(args[0]);
      for (Map.Entry<String, Double> entry : ratings.entrySet()) {
        outFile.write(entry.getKey() + entry.getValue());
        outFile.newLine();
      }
      outFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
