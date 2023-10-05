/*****************************************************************
*
* John Grobaker *
* *
* To implement a Deterministic Finite Automata simulator *
* To compute and have an indication of acceptance or rejection from a given string *
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
  private List <String> alphabet;
  private int transitions [][];
  
  /****************************************************************
  * Main *
  * Runs the Main method 
   * @throws IOException *
  ****************************************************************/
  public static void main(String[] args) throws IOException {
    Main m = new Main();
    m.mainLoop();
  }
  
  private void mainLoop() throws IOException {
    setInputFromFile();
    while(!userInput.equals("Quit")) {
      getUserInput();
      if (userInput.equals("Quit"))
        break;
      evaluate();
    }
    System.out.println(">>>Goodbye!");
  }
  
  /****************************************************************
  * setInputFromFile *
  * sets the input from the file 
   * @throws IOException *
  ****************************************************************/
  private void setInputFromFile() throws IOException {
    System.out.println(">>>Loading DFA.txt…");
    File file = new File("DFA.txt");
    Scanner input = new Scanner(file);
    int indexLine = 0;
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
        alphabet = new ArrayList<>();
        for(int i = 0; i < temp.length; i++) {
          alphabet.add(temp[i]);
        }
      } else if (indexLine == 3) {
        transitions = new int [states][alphabet.size()];
        for(int i = 0; i < temp.length; i++) {
          transitions[0][i] = Integer.parseInt(temp[i]);
        }
      } else {
        for(int i = 0; i < temp.length; i++) {
          transitions[indexLine-3][i] = Integer.parseInt(temp[i]);
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
    System.out.println(">>>Please enter a string to evaluate:");
    userInput = scanner.nextLine();
  }
  
  /****************************************************************
  * evaluate *
  * evaluate the given string on the constructed DFA and outputs *
  ****************************************************************/
  private void evaluate() {
    System.out.println(">>>Computation…");
    String [] temp = new String[userInput.length()];
    for (int i = 0; i < userInput.length(); i++) {
      temp[i] = userInput.charAt(i)+"";
    }
    
    int currentState = 0;
    for (int i = 0; i < temp.length; i++) {
      if(!alphabet.contains(temp[i])) {
        System.out.println(currentState+", " + userInput.substring(i) + " -> INVALID INPUT " + temp[i]);
        System.out.println("REJECTED");
        return;
      }
      
      System.out.print(currentState+", " + userInput.substring(i) + " -> ");
      int indexOfElement = alphabet.indexOf(temp[i]);
      currentState = transitions[currentState][indexOfElement];
      if(userInput.substring(i+1).equals(""))
        System.out.println(currentState+", {e}");
      else
        System.out.println(currentState+", " + userInput.substring(i+1));
    }
    if(acceptingStates.contains(currentState))
      System.out.println("ACCEPTED");
    else 
      System.out.println("REJECTED");
  }
}