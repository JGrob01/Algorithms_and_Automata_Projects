package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class utilities {
  private double [] times;      //stores individual times
  private double []small;       //stores each small time 
  private double []med;       //stores each medium time 
  private double []large;       //stores each large time 
  String[] dirList;
  private String dirCurrent;
  
  public utilities(String [] list) {
    dirList = list; 
    times = new double[30];
    small = new double[30];
    med = new double[30];
    large = new double[30];
  }
  
  /**
   * Sets the avg times for each list
   * @param index
   */
  public void setAvg(int index) {
    if(index == 0) {
      for(int i = 0; i < 30; i++) {
        small[i] = times[i];
      }
    } else if (index == 1) {
      for(int i = 0; i < 30; i++) {
        med[i] = times[i];
      }
    } else {
      for(int i = 0; i < 30; i++) {
        large[i] = times[i];
      }
    }
  }
  
  public double[] getSmall() {
    return small;
  }
  
  public double[] getMed() {
    return med;
  }
  
  public double[] getLarge() {
    return large;
  }
  
  /**
   * sets the times for each individual list
   * @param time
   * @param index
   */
  public void setTime(double time, int index){
    times[index] = time/1000000;                      //if you wanted to store each individual time, here is where youd do it
    System.out.println(times[index]);
  }
  
  /**
   * Creates arrays for sorters
   * @param dirCurrent
   * @param num
   * @param i
   * @return
   */
  public int[] makeArray(String dirCurrent, int num, int i) {
    String fileName = dirCurrent+"output"+i+".txt"; 
    int [] tempArray = new int[num];
    try {
      File file = new File(fileName);
      Scanner scan = new Scanner(file);
      int arrayIndex = 0;
      while (scan.hasNextLine()) {                                    //Uses a scanner to read the file line by line
        int tempNum = Integer.parseInt(scan.nextLine());
        tempArray[arrayIndex] = tempNum;                                   //adds the nums to the array
        arrayIndex++;
      }
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
    }
    return tempArray;
  }
  
  public void printArray(int list[]) {
    int n = list.length;
    for (int i = 0; i < n; ++i) {
      System.out.print(list[i] + " ");
    }
    System.out.println("");
  }
  
  /**
   * tests the finished sorted array with the 'key' array
   * @param finishedArray
   * @param a
   * @param num
   * @param i
   */
  public void testList(int [] finishedArray, int a, int num, int i) {
    dirCurrent = dirList[a+3];                          //tests if correct
    int [] key = makeArray(dirCurrent, num, i);         //uses sorted list to test
    //printArray(finishedArray);
    //printArray(key);
    if(test(finishedArray, key))                            
      System.out.println(i+": passes");
    else
      System.out.println(i+": fails");
    
    System.out.println(dirCurrent+" "+i+": done");
  }
  
  /**
   * tests the sorted array with the finished to see if they are the same
   * @param array
   * @param key
   * @return
   */
  public boolean test(int array[], int key[]) {
    for(int i = 0; i < array.length-1; i++) {
      if(array[i] != key[i]) {
        System.out.println(array[i]+" does not equal " + key[i]);
        return false;
      }
    }
    return true;
  }
}
