import java.util.*;
import java.io.*;

public class CustomerMeans
{
  public static Map<String, List<Double>> makeRatingDictionary() {
    Map<String, List<Double>> ratings = new HashMap<String, List<Double>>();
    File dir = new File("training_set/");
    File[] mvFiles = dir.listFiles();
    OverallMean mean = new OverallMean();
    for (File f : mvFiles) {
      try {
        FileInputStream fin = new FileInputStream(f);
        BufferedReader inFile = new BufferedReader(new InputStreamReader(fin));
        inFile.readLine();
        String nLine = inFile.readLine();
        while (nLine != null) {
          String[] lineArray = nLine.split(",");
          if (ratings.containsKey(lineArray[0])) {
            List<Double> temp = ratings.get(lineArray[0]);
            Double tot = temp.get(0) +
                          (Double.parseDouble(lineArray[1]) - mean.getMean());
            Double num = temp.get(1) + 1;
            temp.set(0, tot);
            temp.set(1, num);
            ratings.replace(lineArray[0], temp);
          }
          else {
            List<Double> temp = new ArrayList<Double>();
            temp.add(Double.parseDouble(lineArray[1]) - mean.getMean());
            temp.add(new Double(1.0));
            ratings.put(lineArray[0], temp);
          }
          nLine = inFile.readLine();
        }
        fin.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return ratings;
  }
  public static void main(String[] args) {
    try {
      FileWriter fout = new FileWriter("customerMeans.txt");
      BufferedWriter outFile = new BufferedWriter(fout);
      Map<String, List<Double>> ratings = makeRatingDictionary();
      for (Map.Entry<String, List<Double>> entry : ratings.entrySet()) {
        List<Double> values = entry.getValue();
        Double average = values.get(0) / values.get(1);
        outFile.write(entry.getKey() + ":" + average);
        outFile.newLine();
      }
      outFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
