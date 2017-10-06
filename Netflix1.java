import java.util.*;
import java.io.*;

public class Netflix1
{
  public static void main(String[] args) {
    try {
      AllMean predict = new AllMean("training_set");
      Double mean = predict.getMean();
      FileInputStream fin = new FileInputStream(args[1]);
      BufferedReader inFile = new BufferedReader(new InputStreamReader(fin));
      ArrayList<Double> predictedRatings = new ArrayList<Double>();
      ArrayList<Double> actualRatings = new ArrayList<Double>();
      String nLine = inFile.readLine();
      int numValues = 0;
      while (nLine != null) {
        if (nLine.indexOf(":") < 0) {
            predictedRatings.add(mean);
            actualRatings.add(Double.parseDouble(nLine));
            numValues++;
        }
        nLine = inFile.readLine();
      }
      fin.close();
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
