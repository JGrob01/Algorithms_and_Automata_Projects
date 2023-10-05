package sorters;

import util.utilities;

public class myMergeSort {
  String[] dirList;
  private String dirCurrent;
  private int[] temp;
  private utilities ut;
  
  public myMergeSort(String [] list) {
    dirList = list; 
    ut = new utilities(list);
  }
  
  /**
   * Runs the algorithm with the unsorted lists
   */
  public void msUnsorted(){
    for(int a = 0; a < 3; a++) {                            //runs through each list, s/m/l
      dirCurrent = dirList[a]; 
      int num = (int) Math.pow(10, 4+a); 
      for(int i = 0; i < 30; i++) {                         //runs through each file
        temp = ut.makeArray(dirCurrent, num, i);
        double startTime = System.nanoTime();     
        int [] finishedArray = mergeSort(temp, temp.length);  //runs mergeSort method
        double stopTime = System.nanoTime();
        ut.setTime((stopTime - startTime), i);
        
       // ut.testList(finishedArray, a, num, i);               //tests the array, comment out after finished
      }
      ut.setAvg(a);
    }
  }
  
  /**
   * Runs the algorithm with the sorted lists
   */
  public void msSorted(){
    for(int a = 0; a < 3; a++) {                            //runs through each list, s/m/l
      dirCurrent = dirList[a+3];    
      int num = (int) Math.pow(10, 4+a); 
      for(int i = 0; i < 30; i++) {                         //runs through each file
        temp = ut.makeArray(dirCurrent, num, i);
        double startTime = System.nanoTime();     
        int [] finishedArray = mergeSort(temp, temp.length);  //runs mergeSort method
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
  public void msReverseSorted(){
    for(int a = 0; a < 3; a++) {                            //runs through each list, s/m/l
      dirCurrent = dirList[a+6];         
      int num = (int) Math.pow(10, 4+a); 
      for(int i = 0; i < 30; i++) {                         //runs through each file
        temp = ut.makeArray(dirCurrent, num, i);
        double startTime = System.nanoTime();     
        int [] finishedArray = mergeSort(temp, temp.length);  //runs mergeSort method
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
  
  public static int[] mergeSort(int[] A, int n) {
    if (n > 1) {                                            
      int i = n / 2;
      int[] B = new int[i];                                  //'splits' the array in 2
      int[] C = new int[n - i];

      for (int a = 0; a < i; a++) {                          //copys the first half to B
        B[a] = A[a];               
      }
      for (int b = i; b < n; b++) {                          //copys the second half to C
        C[b - i] = A[b];
      }
      mergeSort(B, i);                                      //call to sort the first half
      mergeSort(C, n - i);                                  //call to sort the second half

      merge(A, B, C);                                       //merges the Array back together
    }
    return A;
  }
  
  public static void merge( int[] A, int[] B, int[] C) {
    int i = 0;
    int j = 0;
    int k = 0;
    int p = B.length;
    int q = C.length;
    
    while (i < p && j < q) {                                //core of the sorter
      if (B[i] <= C[j]) {                                   //sorts array with less than. greater than checks
        A[k] = B[i];
        i++;
      } else {
        A[k] = C[j];
        j++;
      }
      k++;
    }
    
    while (i < p) {                                         //puts any remaining into A
      A[k] = B[i];
      k++;
      i++;
    }
    
    while (j < q) {
      A[k] = C[j];
      k++;
      j++;
    }
  }
}