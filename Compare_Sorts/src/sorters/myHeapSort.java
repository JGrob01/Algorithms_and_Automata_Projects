package sorters;

import util.utilities;

public class myHeapSort {
  String[] dirList;
  private String dirCurrent;
  private int[] temp;
  private utilities ut;
  
  public myHeapSort(String [] list) {
    dirList = list; 
    ut = new utilities(list);
  }
  
  /**
   * Runs the algorithm with the unsorted lists
   */
  public void hsUnsorted(){
    for(int a = 0; a < 3; a++) {                            //runs through each list, s/m/l
      dirCurrent = dirList[a]; 
      int num = (int) Math.pow(10, 4+a); 
      for(int i = 0; i < 30; i++) {                         //runs through each file
        temp = ut.makeArray(dirCurrent, num, i);
        double startTime = System.nanoTime();     
        int [] finishedArray = heapSort(temp, 0, temp.length-1);  //runs heapSort method
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
  public void hsSorted(){
    for(int a = 0; a < 3; a++) {                            //runs through each list, s/m/l
      dirCurrent = dirList[a+3];    
      int num = (int) Math.pow(10, 4+a); 
      for(int i = 0; i < 30; i++) {                         //runs through each file
        temp = ut.makeArray(dirCurrent, num, i);
        double startTime = System.nanoTime();     
        int [] finishedArray = heapSort(temp, 0, temp.length-1);  //runs heapSort method
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
  public void hsReverseSorted(){
    for(int a = 0; a < 3; a++) {                            //runs through each list, s/m/l
      dirCurrent = dirList[a+6];         
      int num = (int) Math.pow(10, 4+a); 
      for(int i = 0; i < 30; i++) {                         //runs through each file
        temp = ut.makeArray(dirCurrent, num, i);
        double startTime = System.nanoTime();     
        int [] finishedArray = heapSort(temp, 0, temp.length-1);  //runs heapSort method
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
   * Beginning of the full heapSort algorithm 
   * @param list array to be sorted
   * @param i start index
   * @param j end index
   * @return the array to be tested for correctness
   */
  public int[] heapSort(int [] list, int i, int j) {
    return sort(list);
  }
  
  
  
  /**
   * EVERYTHING BELOW IS NOT MY OWN CODE
   * THIS HEAPSORT IS TAKEN FROM https://www.geeksforgeeks.org/heap-sort/
   */
  public int[] sort(int arr[])
  {
      int n = arr.length;

      // Build heap (rearrange array)
      for (int i = n / 2 - 1; i >= 0; i--)
          heapify(arr, n, i);

      // One by one extract an element from heap
      for (int i = n - 1; i > 0; i--) {
          // Move current root to end
          int temp = arr[0];
          arr[0] = arr[i];
          arr[i] = temp;

          // call max heapify on the reduced heap
          heapify(arr, i, 0);
      }
      return arr;
  }

  // To heapify a subtree rooted with node i which is
  // an index in arr[]. n is size of heap
  void heapify(int arr[], int n, int i)
  {
      int largest = i; // Initialize largest as root
      int l = 2 * i + 1; // left = 2*i + 1
      int r = 2 * i + 2; // right = 2*i + 2

      // If left child is larger than root
      if (l < n && arr[l] > arr[largest])
          largest = l;

      // If right child is larger than largest so far
      if (r < n && arr[r] > arr[largest])
          largest = r;

      // If largest is not root
      if (largest != i) {
          int swap = arr[i];
          arr[i] = arr[largest];
          arr[largest] = swap;

          // Recursively heapify the affected sub-tree
          heapify(arr, n, largest);
      }
  }
}