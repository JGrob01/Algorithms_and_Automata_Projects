/*****************************************************************
*
* GrobakerProject2 *
* John Grobaker *
* *
* To implement a Turing Machine simulator *
* *
*****************************************************************/
package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Main {

  private String userInput = "";
  private int states;
  private List <Integer> acceptingStates;
  private String transitions [][];
  
  /****************************************************************
  * Main *
  * Runs the Main method 
   * @throws IOException *
  ****************************************************************/
  public static void main(String[] args) throws IOException {
    Main m = new Main();
    m.mainLoop();
  }
  
  /****************************************************************
   * mainLoop *
   * Runs the loop of the program * 
    * @throws IOException *
   ****************************************************************/
  
  private void mainLoop() throws IOException {
    setInputFromFile();
    getUserInput();
    evaluate(); 
  }
  
  /****************************************************************
   * setInputFromFile *
   * sets the input from the file 
    * @throws IOException *
   ****************************************************************/
   private void setInputFromFile() throws IOException {
     System.out.println(">>>Loading TM.txt…");
     File file = new File("TM.txt");
     Scanner input = new Scanner(file);
     int indexLine = 0;
     
     Scanner inputCount = new Scanner(file);
     int count = 0;
     while (inputCount.hasNextLine()) {
       inputCount.nextLine();
       count++;
     }
     
     while (input.hasNextLine()) {
       String[] temp = input.nextLine().split(" ");
       if (indexLine == 0) {
         states = Integer.parseInt(temp[0]);
       } else if (indexLine == 1) {
         acceptingStates = new ArrayList<>();
         for(int i = 0; i < temp.length; i++) {
           acceptingStates.add(Integer.parseInt(temp[i]));
         }
       } else if (indexLine == 2) {
         transitions = new String [count-2][5];
         for(int i = 0; i < temp.length; i++) {
           transitions[0][i] = temp[i];
         }
       } else {
         for(int i = 0; i < temp.length; i++) {
           transitions[indexLine-2][i] = temp[i];
         }
       }
       indexLine++;
     }
   }
   
   /****************************************************************
    * getUserInput *
    * get the user input *
    ****************************************************************/
    private void getUserInput() {
      Scanner scanner = new Scanner(System.in);
      System.out.println(">>>Enter the starting tape with one leading and one trailing blank ( _ ): ");
      userInput = scanner.nextLine();
    }
    
    /****************************************************************
     * evaluate *
     * evaluate the given string on the constructed TM and outputs *
     ****************************************************************/
     private void evaluate() {
       
       System.out.println(">>>Processing...");
       
       //SPLITS THE INPUT INTO AN ARRAY
       String [] temp = new String[userInput.length()];
       for (int i = 0; i < userInput.length(); i++) {
         temp[i] = userInput.charAt(i)+"";
       }
       
       int currentState = 0;
       int headerIndex = 0;
       while(true) {
         printTape(currentState, headerIndex, temp);
         
         if(acceptingStates.contains(currentState)) {
           System.out.println("[HALT]");
           return;
         }
         
         for(int i = 0; i < transitions.length; i++ ) {
           if(transitions[i][0].equals(currentState+"")) {
             if(transitions[i][1].equals(temp[headerIndex])) {
               temp[headerIndex] = transitions[i][2];
               if(transitions[i][3].equals("R")) {
                 // FIRST CHECKS IF THE TAPE NEEDS TO GROW SINCE IF THE TAPE IS GOING RIGHT AND NEED MORE SPACE
                 if(headerIndex == temp.length -1) {
                   String[] tempOftemp = new String[temp.length + 1];
                   System.arraycopy(temp, 0, tempOftemp, 0, temp.length);
                   temp = tempOftemp;
                 }
                 headerIndex++;
               } else {
                 // FIRST CHECKS IF THE TAPE NEEDS TO GROW SINCE IF THE TAPE IS GOING LEFT AND NEED MORE SPACE
                 if(headerIndex == 0) {
                   String[] tempOftemp = new String[temp.length + 1];
                   System.arraycopy(temp, 0, tempOftemp, 1, temp.length);
                   temp = tempOftemp;
                 }
                 headerIndex--;
               }
               currentState = Integer.parseInt(transitions[i][4]);
               break;
             }
           }
         }
       }
     }
     
     /****************************************************************
      * printTape *
      * Print the current tape with read-write header *
      ****************************************************************/
      private void printTape(int currentState, int headerIndex, String [] input) {
        System.out.print(currentState + ": ");
        for (int i = 0; i < input.length; i++) {
          if( i == headerIndex) {
            System.out.print("[" + input[i] + "]");
          } else {
            System.out.print(input[i]);
          }
        }
        System.out.println("");
      }
}
