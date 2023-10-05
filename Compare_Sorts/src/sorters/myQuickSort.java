package sorters;

import java.util.Arrays;
import util.utilities;

public class myQuickSort {
  String[] dirList;
  private String dirCurrent;
  private int[] temp;
  private utilities ut;
  
  public myQuickSort(String [] list) {
    dirList = list; 
    ut = new utilities(list);
  }
  
  /**
   * Runs the algorithm with the unsorted lists
   */
  public void qsUnsorted(){
    for(int a = 0; a < 3; a++) {                            //runs through each list, s/m/l
      dirCurrent = dirList[a]; 
      int num = (int) Math.pow(10, 4+a); 
      for(int i = 0; i < 30; i++) {                         //runs through each file
        temp = ut.makeArray(dirCurrent, num, i);
        double startTime = System.nanoTime();     
        int [] finishedArray = quickSort(temp, 0, temp.length-1);  //runs quicksort method
        double stopTime = System.nanoTime();
        ut.setTime((stopTime - startTime), i);
        
        //ut.testList(finishedArray, a, num, i);               //tests the array, comment out after finished
      }
      ut.setAvg(a);
    }
  }
  
  /**
   * Runs the algorithm with the sorted lists
   */
  public void qsSorted(){
    for(int a = 0; a < 3; a++) {                            //runs through each list, s/m/l
      dirCurrent = dirList[a+3];    
      int num = (int) Math.pow(10, 4+a); 
      for(int i = 0; i < 30; i++) {                         //runs through each file
        temp = ut.makeArray(dirCurrent, num, i);
        double startTime = System.nanoTime();     
        int [] finishedArray = quickSort(temp, 0, temp.length-1);  //runs quicksort method
        double stopTime = System.nanoTime();
        ut.setTime((stopTime - startTime), i);

        //ut.testList(finishedArray, a, num, i);               //tests the array, comment out after finished
      }
      ut.setAvg(a);
    }
  }

  /**
   * Runs the algorithm with the reverse sorted lists
   */
  public void qsReverseSorted(){
    for(int a = 0; a < 3; a++) {                            //runs through each list, s/m/l
      dirCurrent = dirList[a+6];         
      int num = (int) Math.pow(10, 4+a); 
      for(int i = 0; i < 30; i++) {                         //runs through each file
        temp = ut.makeArray(dirCurrent, num, i);
        double startTime = System.nanoTime();     
        int [] finishedArray = quickSort(temp, 0, temp.length-1);  //runs quicksort method
        double stopTime = System.nanoTime();
        ut.setTime((stopTime - startTime), i);
       
        //ut.testList(finishedArray, a, num, i);               //tests the array, comment out after finished
      }
      ut.setAvg(a);
    }
  }
  
  public double[] getSmall() {
    return ut.getSmall();
  }
  
  public double[] getMed() {
    return ut.getMed();
  }
  
  public double[] getLarge() {
    return ut.getLarge();
  }
  
  /**
   * Beginning of the full quicksort algorithm 
   * it will first partiton the array then recursively call each 'side'
   * @param list array to be sorted
   * @param i start index
   * @param j end index
   * @return the array to be tested for correctness
   */
  public int[] quickSort(int [] list, int i, int j) {
    if(i < j){                                                      //Base case
      int p = myPartition(list, i, j);
      quickSort(list, i, p-1);
      quickSort(list, p+1, j);
    }
    return list;
  }
  
  public int myPartition(int [] list, int i, int j) {
    int pivot;
    if(list.length>=101) {                                //prevent overhead                                         
      int middle = (i+j)/2;                               //find median of the three values
      int[] sortTemp = {list[i], list[middle], list[j]};
      Arrays.sort(sortTemp);
      if(list[j] < list[i])                               //swaps position so that they are in correct placements
        swapValues(list, i, j);
      if(list[middle] < list[i])
        swapValues(list, middle, i);
      if(list[j] < list[middle])
        swapValues(list, middle, j);
    }
    
    pivot = list[i];                                    //set pivot value to first value(swapped earlier, this is the median)
    int pivotIndex = i;                                 //set pivot index to first index
    i = pivotIndex+1;                                   //set i index to 1 +
    while(j>=i) { 
      while (pivot > list[i]) {                         //move i until pivot <= list[i]
        i++;
      }
      while (pivot < list[j]) {                         //move j until pivot >= list[j]
        j--;
      }
      if(i >= j) {                                      //check the indices of i,j
        break;
      }
      swapValues(list, i, j);                           //swap i and j and increment to next
      i++;
      j--;
    }
    swapValues(list, j, pivotIndex);                    //end of the partition, swap pivot
    return j;
  } 
  
  public void swapValues(int [] list, int i, int j) {   //swap util
    int tempNum = list[i];
    list[i] = list[j];
    list[j] = tempNum;
  }
}