/**
 * @author Larry Rodrigues
 * SWE 642 Fall 2017
 * HW5 Struts
 * 
 * Class DataBean  is used to store mean value data information.
 * The class uses the methods provided in StdStats to perform statistical calculations.
 */
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
 
  public double getMeanValue() {
    return this.meanValue;
  }
  
  public double getStandardDeviation() {
    return this.standardDeviation;
  }
  
  public String getDataFieldList() {
    return this.dataFieldList;
  }
  
  public void setDataFieldList(String dataFieldList) {
    this.dataFieldList = dataFieldList;
    this.parseDataFieldList();
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
  
    @Override
    public String toString() {
        StringBuilder sbf = new StringBuilder();
        sbf.append("[DataBean] {dataFieldList:");
        sbf.append(this.dataFieldList);
        sbf.append(", meanValue:");
        sbf.append(this.meanValue);
        sbf.append(", standardDeviation:");
        sbf.append(this.standardDeviation);
        sbf.append("}");
        
        return sbf.toString();
    }
}
