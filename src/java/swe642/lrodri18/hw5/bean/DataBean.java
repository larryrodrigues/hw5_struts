package swe642.lrodri18.hw5.bean;

import swe642.lrodri18.hw5.utils.StdStats;

/**
 *
 * @author LRODRIGUES
 */
public class DataBean {

  private String dataFieldList = null;
  private double meanValue = -1;
  private double standardDeviation = -1;

  public DataBean() {
    this.dataFieldList = "";
    this.meanValue = -1;
    this.standardDeviation = -1;
  }

  public DataBean(String dataFieldList) {
    this.dataFieldList = dataFieldList;
    this.parseDataFieldList();
  }

  public void setData(String dataFieldList) {
    this.dataFieldList = dataFieldList;
  }
  
  public double getMeanValue() {
    return this.meanValue;
  }
  
  public double getStandardDeviation() {
    return this.standardDeviation;
  }
  
  public String getDataFieldList() {
    return this.dataFieldList;
  }

  public void parseDataFieldList() {
    if (this.dataFieldList != null && !this.dataFieldList.equals("")) {
      long sumValue = 0;
      double meanValue = 0;

      String[] stringArray = this.dataFieldList.split(",");
      int[] intArray = new int[stringArray.length];
      for (int i = 0; i < stringArray.length; i++) {
        String numberAsString = stringArray[i];
        intArray[i] = Integer.parseInt(numberAsString);
      }
      for (int i = 0; i < intArray.length; i++) {
        sumValue = sumValue + intArray[i];
      }
      if (intArray.length > 0) {
        // meanValue = (double) sumValue / intArray.length;
        this.meanValue = StdStats.mean(intArray);
        this.standardDeviation = StdStats.stddev(intArray);
      }
    }
  }
}
