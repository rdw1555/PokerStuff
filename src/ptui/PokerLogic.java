package ptui;

import model.RITQTNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PokerLogic {
    //Global compressed image ArrayList
    private static ArrayList<Integer> compressedList;
    //Global initial file size
    private static int initSize;

    //Variables to be used in the toString
    private static String filename;
    private static String outFilename;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java RITCompress uncompressed-file.txt compressed-file.rit");
            return;
        }
        //Instantiate the compressed image values list
        compressedList = new ArrayList<>();

        //Get the name of the file
        filename = args[0];

        //Create a board using the filename
        int[][] board = populateBoard(filename);

        //Call compress image with the board
        compressImage(board);

        //Write to the file
        //Get the uncompressed file to write to it
        File compressed = new File(args[1]);
        try {
            FileWriter writer = new FileWriter(compressed);
            writer.write("" + (int)Math.pow(board.length,2)+ "\n");
            for(int val : compressedList){
                writer.write("" + val+ "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Output File could not be created");
            System.exit(0);
        }

        //Set the name of the output file for use in the toString
        outFilename = args[1];

        //Write stats to Stats File
        writeStats();

    }

    /**
     * compressImage - compresses the image into the quadtree preorder representation, recursing over the image into
     * 4 equally sized subregions, incrementally filling the compressedList ArrayList preorder representation collection
     * @param subregion - the current box subregion of the image
     */
    public static void compressImage(int[][] subregion){
        //if the subregion is bigger than one pixel, split it into 4 subsections
        if(subregion.length > 1){
            //Loop through the entire subregion and see if it's all the same color
            int tempColor = subregion[0][0];

            //boolean flag saying it's not the same color
            boolean diffColor = false;

            for(int row = 0; row < subregion.length; row++) {
                for (int col = 0; col < subregion.length; col++) {
                    //if we've hit a new color
                    if (subregion[row][col] != tempColor) {
                        //raise the flag
                        diffColor = true;
                        //break out of the loops
                        break;
                    }
                }
            }

            //Here, check if the flag was raised
            if(diffColor){
                //It's different colors
                //Add a -1 to the compressedList
                compressedList.add(-1);

                //Split it into 4 different subsections and recurse with them
                //Create the 4 subregions
                int[][] sub1 = new int[subregion.length/2][subregion.length/2];
                int[][] sub2 = new int[subregion.length/2][subregion.length/2];
                int[][] sub3 = new int[subregion.length/2][subregion.length/2];
                int[][] sub4 = new int[subregion.length/2][subregion.length/2];

                //Fill them
                for(int row = 0; row < sub1.length; row++){
                    for(int col = 0; col < sub1.length; col++){
                        //sub1 (0,0)
                        sub1[row][col] = subregion[row][col];
                        //sub2 (0,0+sub2.length)
                        sub2[row][col] = subregion[row][col + sub2.length];
                        //sub3 (0+sub3.length, 0)
                        sub3[row][col] = subregion[row + sub3.length][col];
                        //sub4 (0+sub4.length, 0+sub4.length)
                        sub4[row][col] = subregion[row + sub4.length][col + sub4.length];
                    }
                }

                //Recurse with all subregions
                //System.out.println(sub1.length +" "+ subregion.length);

                compressImage(sub1);
                compressImage(sub2);
                compressImage(sub3);
                compressImage(sub4);
            } else{
                //It's one color
                //Just add that number to the list
                compressedList.add(tempColor);
            }
        } else{
            //it wasn't bigger than one pixel
            //just add that number
            compressedList.add(subregion[0][0]);
        }
    }

    /**
     * populateBoard - populates teh 2d int array of the image board
     * @param filename - the name of the file you're making the image of
     * @return - a int[][] of the filled image board
     */
    public static int[][] populateBoard(String filename){
        //Create the scanner and open the file
        Scanner s = null;
        try {
            File in  = new File(filename);
            s = new Scanner(in);
        }catch(FileNotFoundException fne){
            System.err.print("File not found");
            System.exit(0);
        }

        //Read in all the raw data
        ArrayList<Integer> rawData  = new ArrayList<>();
        while(s.hasNextInt()){
            rawData.add(s.nextInt());
        }

        //Save the size of the board
        initSize = rawData.size();
        int size = (int)Math.sqrt(rawData.size());

        //Create the board
        int[][] board = new int[size][size];

        //Fill the board
        for(int row =0; row< board.length; row++){
            for(int col =0; col < board.length; col++){
                board[row][col] = rawData.remove(0);
            }
        }

        //return the board
        return board;
    }

    /**
     * Write Stats method
     * @return - writes results to a new file
     */
    public static void writeStats(){
        File Stats = new File("src/gui/Stats.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(Stats);
        } catch (IOException e) {
            System.err.println("File not found");
            System.exit(0);
        }
        try {
            writer.write("Compressing " + filename);
            writer.write("\nQTree: " + compressedList);
            writer.write("\nOutput file: " + outFilename);
            writer.write("\nRaw image size: " + initSize);
            writer.write("\nCompressed image size: " + (compressedList.size() + 1));
            writer.write("\nCompression %: " + (100 - 100 * (compressedList.size() + 1) / (double) initSize));
            writer.close();
        }catch(IOException IE){
            System.exit(0);
        }
    }
}
