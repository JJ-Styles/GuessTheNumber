/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessthenumber;
import java.util.Scanner;
import java.util.Random;
/**
 * A game to guess a random number
 * @author t7047098
 */
public class GuessTheNumber {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);  //Initialises the Scanner variable Keyboard
        boolean valid = false;
        
        while(!valid)
            {
            System.out.println("Do you want to guess the number or do you want the computer to guess the number.");
            System.out.println("If you want to guess enter Me.");
            System.out.println("If you want the Computer to guess enter Computer");
            String gameMode = keyboard.nextLine().toLowerCase();
            if ("computer".equals(gameMode))
            {
                ComputerMode();
                valid = true;
            }
            else if ("me".equals(gameMode))
            {
                UserMode();
                valid = true;
            }
        }
        
    }
    
    public static void ComputerMode()
    {
        Scanner keyboard = new Scanner(System.in);
        String difficulty = DifficultySettings();
        int counter = 0, value = 0, middle = 0;
        
            if ("easy".equals(difficulty))
            {
                System.out.println("Please enter the value to be guessed between 1 and 100");
                value = keyboard.nextInt();
                counter = 20;
                middle = 50;
            }
            else if ("medium".equals(difficulty))
            {
                System.out.println("Please enter the value to be guessed between 1 and 500");
                value = keyboard.nextInt();
                counter = 15;
                middle = 250;
            }
            else if ("hard".equals(difficulty))
            {
                System.out.println("Please enter the value to be guessed between -1000 and 1000");
                value = keyboard.nextInt();
                counter = 7;
            } 
            System.out.println("The Computer guesses " + middle);
            counter = GuessC(value,middle,counter);
           if (counter <= 0)
           {
               System.out.println("Computer ran out of guesses");
           }
            
    }
    
    public static String DifficultySettings()
    {
        String difficulty = "";
        boolean valid = false;
        Scanner keyboard = new Scanner(System.in);  //Initialises the Scanner variable Keyboard
        
        while (valid == false)
        {
            System.out.println("Easy (number range:1-100 and 20 turns)");
            System.out.println("Medium (number range:1-500 and 15 turns)");
            System.out.println("Hard (number range: -1000 to +1000 and 7 turns)");
            System.out.println("Please Pick a difficulty: ");
            difficulty = keyboard.nextLine().toLowerCase();
            if ("easy".equals(difficulty))
            {
                valid = true;
            }
            else if ("medium".equals(difficulty))
            {
                valid = true;
            }
            else
            {
                valid = true;
            }
        }
        return difficulty;
    }
    
    public static int GuessC(int value, int middle, int counter)
    {               
        if (value > middle)
        {
            middle += middle/2;
            System.out.println("The Computer guesses " + middle);
            GuessC(value, middle,counter-1);
        }
        else if (value < middle)
        {
            if (middle == 0)
            {
                middle = -500;
                System.out.println("The Computer guesses " + middle);
                GuessC(value, middle,counter-1);
            }
            else 
            {
                middle /= 2;
                System.out.println("The Computer guesses " + middle);
                GuessC(value, middle,counter-1);
            }
        }
        else if (value == middle)
        {
            System.out.println("Computer Guessed Correct with " + counter + " guesses left.");
        }
        return counter;       
    }
    
    public static void UserMode()
    {
        Random n = new Random();    //initialises the the random variable
        String difficulty = DifficultySettings();
        int counter = 0, randomNumber = 0;
        if ("easy".equals(difficulty))   //Sets the difficuulty of the game
            {
                counter = 20;
                randomNumber = n.nextInt(100)+1;    //Sets the randomNumber between 1 and 100
            }
            else if ("medium".equals(difficulty))
            {
                counter = 15;
                randomNumber = n.nextInt(500)+1;    //Sets the randomNumber between 1 and 500
            }
            else
            {
                counter = 7;
                randomNumber = n.nextInt(2000)-1000;    //Sets the randomNumber between -1000 and 1000
            }
        GuessH(randomNumber);
    }
    
    public static void GuessH(int randomNumber)
    {
        Scanner keyboard = new Scanner(System.in);
        int guessNumber = 0, counter = 0;
        while ((guessNumber != randomNumber)&&(counter > 0))   // makes the user keep guessing till they guess the number or run out of guesses
        {
            System.out.print("Enter Guess: ");
            int guess = keyboard.nextInt();
            counter -= 1;   //decreases the counter by 1 for each guess
            
            if (guessNumber > randomNumber) //Checks if the value is higher of lower
            {
                System.out.println("Too High");
            }
            else if (guessNumber < randomNumber)
            {
                System.out.println("Too Low");
            }
        }
        
        if (guessNumber == randomNumber)
        {
            System.out.println("You guessed correct with " + counter + " guesses left.");
        }
        else 
        {
            System.out.println("You ran out of guesses. The correct number was " + randomNumber);
        }
    }
}
