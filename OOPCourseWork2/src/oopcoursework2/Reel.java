/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopcoursework2;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

/**
 *
 * @author Manul Singhe 2014254
 */
public class Reel {
    
    //creating instances of the symbol
    symbol s1 = new symbol();
    symbol s2 = new symbol();
    symbol s3 = new symbol();
    symbol s4 = new symbol();
    symbol s5 = new symbol();
    symbol s6 = new symbol();

    String location;
    int value, wins, wonCr, total, loses, lostCr, bet1;

   public void instance() {
        
        // assigning data to the instances of the symbol
        s1.setImage("Images\\cherry.png");
        s1.setValue(2);

        s2.setImage("Images\\lemon.png");
        s2.setValue(3);

        s3.setImage("Images\\plum.png");
        s3.setValue(4);

        s4.setImage("Images\\watermelon.png");
        s4.setValue(5);

        s5.setImage("Images\\bell.png");
        s5.setValue(6);

        s6.setImage("Images\\redseven.png");
        s6.setValue(7);
    }

    public void spin() {
        instance();
        //creating an arra of numbers to randomize the selection of instances
        int[] solutionArray = {1, 2, 3, 4, 5, 0};

        shuffleArray(solutionArray);//shuffeling the arry to randomize

        for (int i = 0; i < solutionArray.length; i++) {
            
          //using a switch case to find the instance and assigning those values to variables and returning.
            switch (solutionArray[i]) {
                case 0:
                    location = s1.getImage();
                    value = s1.getValue();
                    return;

                case 1:
                    location = s2.getImage();
                    value = s2.getValue();
                    return;
                case 2:
                    location = s3.getImage();
                    value = s3.getValue();
                    return;
                case 3:
                    location = s4.getImage();
                    value = s4.getValue();
                    return;
                case 4:
                    location = s5.getImage();
                    value = s5.getValue();
                    return;
                case 5:
                    location = s6.getImage();
                    value = s6.getValue();
                    return;
            }

        }

    }

    static void shuffleArray(int[] ar) {
        
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public void compare(int val1, int val2, int val3, int bet) {
        //comapring the values taken from the three instances to see if the roud was won or lost.
        if (val1 == val2) {
            if (val1 == val3) {

                wins = wins + 1;
                total = val1 * bet;

                wonCr = wonCr + total;

                JOptionPane.showMessageDialog(null, "You Won!");
                bet1 = 0;
            }

        } else if (val1 == val3) {

            wins = wins + 1;
            total = val1 * bet;

            wonCr = wonCr + total;

            JOptionPane.showMessageDialog(null, "You Won!");
            bet1 = 0;
        } else if (val2 == val3) {

            wins = wins + 1;
            total = val2 * bet;

            wonCr = wonCr + total;
            

            JOptionPane.showMessageDialog(null, "You Won!");
            bet1 = 0;

        } else {
            loses = loses + 1;
            lostCr = lostCr + bet;
            

            JOptionPane.showMessageDialog(null, "You Lost!");
            bet1 = 0;
        }
    }
}
