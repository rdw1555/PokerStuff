package ptui;

import model.PlayerNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class for the PokerLogic
 * This is where (spoiler alert) all of the logic for poker will be handled
 * As of rn, this has a runner method thrown at the bottom - this will eventually cease to exist, and will strictly be for testing purposes
 * Eventually, this is basically just gonna handle housing a deck of cards, shuffling that deck, removing cards from the deck,
 * adding those cards to player hands, putting cards back into the deck from certain players, and (most importantly) betting
 *
 * Please lmk if I forgot any important poker functions like a dumbass
 */
public class PokerLogic {


    /**
     * toString method
     * @return - A string representation of something idk
     */
    @Override
    public String toString(){
        String result = "";

        result += "nothing coded yet lmao";

        return result;
    }

    /**
     * main method
     * @param args - system arguments
     */
    public static void main(String[] args) {
         //Create a null scanner to read from the input file
        Scanner s = new Scanner(System.in);

        PokerLogic pl = new PokerLogic();

        System.out.println(pl.toString());
    }
}