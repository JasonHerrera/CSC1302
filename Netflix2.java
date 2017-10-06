import java.util.*;
import java.io.*;

public class Netflix2
{
  public static void main(String[] args) {
    Map<String, Double> cmeans = new HashMap<String, Double>();
    AllMean mean = new AllMean("training_set");
    try {
      FileInputStream fin1 = new FileInputStream("customerMeans.txt");
      BufferedReader inFile1 = new BufferedReader(new InputStreamReader(fin1));
      String nLine1 = inFile1.readLine();
      while (nLine1 != null) {
        String[] lineArray = nLine1.split(":");
        Double unbiasedMean = Double.parseDouble(lineArray[1]) +
                              mean.getMean();
        cmeans.put(lineArray[0], unbiasedMean);
        nLine1 = inFile1.readLine();
      }
      fin1.close();

      FileInputStream fin2 = new FileInputStream("probe.txt");
      BufferedReader inFile2 = new BufferedReader(new InputStreamReader(fin2));
      FileInputStream fin3 = new FileInputStream("actualRatings.txt");
      BufferedReader inFile3 = new BufferedReader(new InputStreamReader(fin3));
      ArrayList<Double> predictedRatings = new ArrayList<Double>();
      ArrayList<Double> actualRatings = new ArrayList<Double>();
      inFile2.readLine();
      inFile3.readLine();
      String nLine2 = inFile2.readLine();
      String nLine3 = inFile3.readLine();
      int numValues = 0;
      while (nLine2 != null && nLine3 != null) {
        if (nLine2.indexOf(":") < 0) {
          predictedRatings.add(cmeans.get(nLine2));
          actualRatings.add(Double.parseDouble(nLine3));
          numValues++;
        }
        nLine2 = inFile2.readLine();
        nLine3 = inFile3.readLine();
      }
      fin2.close();
      fin3.close();

      double eTotal = 0;
      for (int i = 0; i < predictedRatings.size(); i++) {
        double error = predictedRatings.get(i) - actualRatings.get(i);
        eTotal += (error * error);
      }
      double rmse = Math.sqrt(eTotal/numValues);
      System.out.println("RMSE=" + rmse);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
