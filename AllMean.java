import java.util.*;
import java.io.*;

public class AllMean
{
  private double mean;

  public AllMean(String dir) {
    mean = computeMean(dir);
  }

  public double getMean() {
    return mean;
  }

  public double computeMean(String dir) {
    File dirName = new File(dir);
    File[] directoryListing = dirName.listFiles();
    double total = 0;
    double numFiles = 0;
    if (directoryListing != null) {
      for (File f : directoryListing) {
        try {
          FileInputStream fin = new FileInputStream(f);
          BufferedReader infile = new BufferedReader(new
                                                      InputStreamReader(fin));
          String nLine = infile.readLine();
          nLine = infile.readLine();
          while (nLine != null) {
            String[] data = nLine.split(",");
            total += Integer.parseInt(data[1]);
            numFiles++;
            nLine = infile.readLine();
          }
          infile.close();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
      }
    }
    return total/numFiles;
  }
}
