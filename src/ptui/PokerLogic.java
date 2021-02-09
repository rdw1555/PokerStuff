package ptui;

import model.PlayerNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PokerLogic {


    /**
     * toString method
     * @return - A string representation of the stats
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
        //Check for usage error
        if (args.length != 2) {
            System.err.println("Command Line does not have required arguments");
            System.exit(0);
        }

        //Create a null scanner to read from the input file
        Scanner s = new Scanner(System.in);

        PokerLogic pl = new PokerLogic();

        System.out.println(pl.toString());
    }
}