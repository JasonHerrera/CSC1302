import java.util.*;
import java.io.*;

public class Netflix3
{
  public static void main(String[] args) {
    Map<String, Double> mMeans = new HashMap<String, Double>();
    AllMean mean = new AllMean("training_set");
    try {
      FileInputStream fin1 = new FileInputStream("movieMeans.txt");
      BufferedReader inFile1 = new BufferedReader(new InputStreamReader(fin1));
      String nLine1 = inFile1.readLine();
      while (nLine1 != null) {
        String[] lineArray = nLine1.split(":");
        Double unbiasedMean = Double.parseDouble(lineArray[1]) +
                              mean.getMean();
        mMeans.put(lineArray[0], unbiasedMean);
        nLine1 = inFile1.readLine();
      }
      fin1.close();

      FileInputStream fin2 = new FileInputStream("actualRatings.txt");
      BufferedReader inFile2 = new BufferedReader(new InputStreamReader(fin2));
      String nLine2 = inFile2.readLine();
      String mNum = "";
      ArrayList<Double> predictedRatings = new ArrayList<Double>();
      ArrayList<Double> actualRatings = new ArrayList<Double>();
      int numValues = 0;
      while (nLine2 != null) {
        if (nLine2.indexOf(":") > 0) {
          mNum = nLine2.substring(0, nLine2.length()-1);
        }
        else {
          predictedRatings.add(mMeans.get(mNum));
          actualRatings.add(Double.parseDouble(nLine2));
          numValues++;
        }
        nLine2 = inFile2.readLine();
      }
      fin2.close();

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
