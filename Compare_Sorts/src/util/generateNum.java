package util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class generateNum {
  private String dirCurrent;
  String[] dirList;
  
  public generateNum(String [] list){
    dirList = list;
    for(int i = 0; i < 9; i++) {
      File createDirectory = new File(dirList[i]);
      createDirectory.mkdirs();
    }
  }
  
  public void genUnSorted() {                                         //generate 30 unsorted files 
    for(int a = 0; a < 3; a++) {                                      //loop and gen for small, medium and large
      dirCurrent = dirList[a];                                        //set the dir for current list
      int genNum = (int) Math.pow(10, 4+a);                           //the number of nums generated is computed
      for(int i = 0; i < 30; i++) {                                   //gen 30 files 
        String fileName = dirCurrent+"output"+i+".txt";               //creates file in dir and with name output(num).txt
        try {                                                        
          FileWriter file = new FileWriter(fileName);
          for(int n = 0; n < genNum; n++) {                           
            file.write(((int)(Math.random()*(genNum - 0)) + 0)+"\n"); //gen ran num between 0 and 10,000
          }
          file.close();                                               
          genSorted(genNum, fileName, a, i);                          //runs the file through the sorter
          genRevSort(genNum, fileName, a, i);                                               //runs the file through the reverse sorter
        } catch (IOException e) {
          System.out.println("An error occurred.");
        }
      }
    }
  }
  
  /**
   * @param num               the number of generated numbers created in unsorted (10,000/100,000/1,000,000)
   * @param fileName          the file name for the unsorted files, used to read in the numbers
   * @param currentListIndex  the current index of the dirList, used to correctly create new output files
   * @param currentIndex      the current number for the unsorted output file ie output"currentIndex".txt
   */
  public void genSorted(int num, String fileName, int currentListIndex, int currentIndex) {
    String dirOfSorted = dirList[currentListIndex+3];                 //get the directory of the sorted lists
    int[] temp = new int[num];                                        //temp placement of the unsorted nums
    try {
      File file = new File(fileName);
      Scanner scan = new Scanner(file);
      int arrayIndex = 0;
      while (scan.hasNextLine()) {                                    //Uses a scanner to read the unsorted file line by line
        int tempNum = Integer.parseInt(scan.nextLine());
        temp[arrayIndex] = tempNum;                                   //adds the nums to the array
        arrayIndex++;
      }
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
    }
    Arrays.sort(temp);                                                //use java array sorter to sort the temp array
    
    String fileNameSorted = dirOfSorted+"output"+currentIndex+".txt";     
    try {                                                        
      FileWriter file = new FileWriter(fileNameSorted);
      for(int i = 0; i < num; i++) {                                  //iterates through array and places the nums into the sorted array                   
        file.write(temp[i]+"\n"); 
      }
      file.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
    }
  }
  
  /**
   * @param num               the number of generated numbers created in unsorted (10,000/100,000/1,000,000)
   * @param fileName          the file name for the unsorted files, used to read in the numbers
   * @param currentListIndex  the current index of the dirList, used to correctly create new output files
   * @param currentIndex      the current number for the unsorted output file ie output"currentIndex".txt
   */
  public void genRevSort(int num, String fileName, int currentListIndex, int currentIndex) {
    String dirOfSorted = dirList[currentListIndex+6];                 //get the directory of the reverse sorted lists
    int[] temp = new int[num];                                        //temp placement of the unsorted nums
    try {
      File file = new File(fileName);
      Scanner scan = new Scanner(file);
      int arrayIndex = 0;
      while (scan.hasNextLine()) {                                    //Uses a scanner to read the unsorted file line by line
        int tempNum = Integer.parseInt(scan.nextLine());
        temp[arrayIndex] = tempNum;                                   //adds the nums to the array
        arrayIndex++;
      }
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
    }
    Arrays.sort(temp);                                                //use java array sorter to sort the temp array
    
    String fileNameSorted = dirOfSorted+"output"+currentIndex+".txt";     
    try {                                                        
      FileWriter file = new FileWriter(fileNameSorted);
      for(int i = num-1; i >= 0; i--) {                               //iterates through array and places the nums into the reverse sorted array                   
        file.write(temp[i]+"\n"); 
      }
      file.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
    }
  }
}