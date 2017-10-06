import java.util.*;
import java.io.*;

public class Netflix4
{
  public static void main(String[] args) {
    Map<String, Double> cMeans = new HashMap<String, Double>();
    Map<String, Double> mMeans = new HashMap<String, Double>();
    OverallMean mean = new OverallMean();
    try {
      FileInputStream fin1 = new FileInputStream("customerMeans.txt");
      BufferedReader inFile1 = new BufferedReader(new InputStreamReader(fin1));
      String nLine1 = inFile1.readLine();
      while (nLine1 != null) {
        String[] lineArray = nLine1.split(":");
        Double unbiasedMean = Double.parseDouble(lineArray[1]);
        cMeans.put(lineArray[0], unbiasedMean);
        nLine1 = inFile1.readLine();
      }
      fin1.close();

      FileInputStream fin2 = new FileInputStream("movieMeans.txt");
      BufferedReader inFile2 = new BufferedReader(new InputStreamReader(fin2));
      String nLine2 = inFile2.readLine();
      while (nLine2 != null) {
        String[] lineArray = nLine2.split(":");
        Double unbiasedMean = Double.parseDouble(lineArray[1]);
        mMeans.put(lineArray[0], unbiasedMean);
        nLine2 = inFile2.readLine();
      }
      fin2.close();

      FileInputStream fin3 = new FileInputStream("probe.txt");
      BufferedReader inFile3 = new BufferedReader(new InputStreamReader(fin3));
      FileInputStream fin4 = new FileInputStream("actualRatings.txt");
      BufferedReader inFile4 = new BufferedReader(new InputStreamReader(fin4));
      String nLine3 = inFile3.readLine();
      String nLine4 = inFile4.readLine();
      String mNum = "";
      ArrayList<Double> customerRatings = new ArrayList<Double>();
      ArrayList<Double> movieRatings = new ArrayList<Double>();
      ArrayList<Double> actualRatings = new ArrayList<Double>();
      int numValues = 0;
      while (nLine3 != null && nLine4 != null) {
        if (nLine3.indexOf(":") > 0) {
          mNum = nLine3.substring(0, nLine3.length()-1);
        }
        else {
          customerRatings.add(cMeans.get(nLine3));
          movieRatings.add(mMeans.get(mNum));
          actualRatings.add(Double.parseDouble(nLine4));
          numValues++;
        }
        nLine3 = inFile3.readLine();
        nLine4 = inFile4.readLine();
      }
      fin3.close();
      fin4.close();

      double eTotal = 0;
      for (int i = 0; i < actualRatings.size(); i++) {
        double combError = (customerRatings.get(i) * 0.527 +
                           movieRatings.get(i) * 0.473) + mean.getMean();
        double error = combError - actualRatings.get(i);
        eTotal += (error * error);
      }
      double rmse = Math.sqrt(eTotal/numValues);
      System.out.println("RMSE=" + rmse);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
