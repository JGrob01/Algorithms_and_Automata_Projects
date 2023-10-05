package main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import sorters.myHeapSort;
import sorters.myMergeSort;
import sorters.myQuickSort;
import util.generateNum;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    String[] dirList = {"Files/unsorted/small/","Files/unsorted/medium/","Files/unsorted/large/",
        "Files/sorted/small/","Files/sorted/medium/","Files/sorted/large/",
        "Files/revsorted/small/","Files/revsorted/medium/", "Files/revsorted/large/"};
    
    //generate files, this should in theory only generate the files once and run the sorting code always 
    File checker = new File("Files/");
    if(!(checker.isDirectory())) {
      generateNum g = new generateNum(dirList); 
      g.genUnSorted();
    }
    
    File createDirectory = new File("Times/");
    createDirectory.mkdirs();
    
    //quicksort
    myQuickSort qs = new myQuickSort(dirList);
    qs.qsUnsorted();
    printToFile("Small quicksort Unsorted", qs.getSmall());
    printToFile("Medium quicksort Unsorted", qs.getMed());
    printToFile("Large quicksort Unsorted", qs.getLarge());
    
    qs.qsSorted();
    printToFile("Small quicksort Sorted", qs.getSmall());
    printToFile("Medium quicksort Sorted", qs.getMed());
    printToFile("Large quicksort Sorted", qs.getLarge());
    
    qs.qsReverseSorted();
    printToFile("Small quicksort RevSorted", qs.getSmall());
    printToFile("Medium quicksort RevSorted", qs.getMed());
    printToFile("Large quicksort RevSorted", qs.getLarge());
    System.out.println("done");
    
    
    //mergesort 
    myMergeSort ms = new myMergeSort(dirList);
    ms.msUnsorted();
    printToFile("Small mergesort Unsorted", ms.getSmall());
    printToFile("Medium mergesort Unsorted", ms.getMed());
    printToFile("Large mergesort Unsorted", ms.getLarge());
    
    ms.msSorted();
    printToFile("Small mergesort Sorted", ms.getSmall());
    printToFile("Medium mergesort Sorted", ms.getMed());
    printToFile("Large mergesort Sorted", ms.getLarge());
    
    ms.msReverseSorted();
    printToFile("Small mergesort RevSorted", ms.getSmall());
    printToFile("Medium mergesort RevSorted", ms.getMed());
    printToFile("Large mergesort RevSorted", ms.getLarge());
    System.out.println("done");
    
    //heapsort 
    myHeapSort hs = new myHeapSort(dirList);
    hs.hsUnsorted();
    printToFile("Small heapsort Unsorted", hs.getSmall());
    printToFile("Medium heapsort Unsorted", hs.getMed());
    printToFile("Large heapsort Unsorted", hs.getLarge());
    
    hs.hsSorted();
    printToFile("Small heapsort Sorted", hs.getSmall());
    printToFile("Medium heapsort Sorted", hs.getMed());
    printToFile("Large heapsort Sorted", hs.getLarge());
    
    hs.hsReverseSorted();
    printToFile("Small heapsort RevSorted", hs.getSmall());
    printToFile("Medium heapsort RevSorted", hs.getMed());
    printToFile("Large heapsort RevSorted", hs.getLarge());
    System.out.println("done");
  }
  
  public static void printToFile(String str, double[] ds) {
    String fileName = "Times/"+str+".txt";
    try {                                                        
      FileWriter file = new FileWriter(fileName);
      for(int n = 0; n < ds.length; n++) {                           
        file.write(ds[n]+"\n");
      }
      file.close();                                               
    } catch (IOException e) {
      System.out.println("An error occurred.");
    }
  }
}
