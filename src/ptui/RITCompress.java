package ptui;

import model.RITQTNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RITCompress {
    //Global compressed image ArrayList
    private static ArrayList<Integer> compressedList;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java RITCompress uncompressed-file.txt compressed-file.rit");
            return;
        }

        //Instantiate the compressed image values list
        compressedList = new ArrayList<>();

        //Get the name of the file
        String filename = args[0];

        //Create a board using the filename
        int[][] board = populateBoard(filename);

        //Call compress image with the board
        compressImage(board);

        //Call parse to make the quadtree
        RITQTNode root = parse(compressedList);

        //Write to the file
        //Get the uncompressed file to write to it
        File compressed = new File(args[1]);
        try {
            FileWriter writer = new FileWriter(compressed);
            for(int val : compressedList){
                writer.write(""+ val +"\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Output File could not be created");
            e.printStackTrace();
        }
    }

    /**
     * parse - Parses the arraylist of compressed values to create a quadtree
     * @param compressed - arraylist of compressed values
     * @return - a completed quadtree of the compressed image
     */
    public static RITQTNode parse(ArrayList<Integer> compressed){
        int temp = compressed.remove(0);
        if(temp==-1){
            return new RITQTNode(-1,parse(compressed),parse(compressed),parse(compressed),parse(compressed));
        }else{
            return new RITQTNode(temp);
        }
    }

    public static void compressImage(int[][] subregion){
        //if the subregion is bigger than one pixel, split it into 4 subsections
        if(subregion.length > 1){
            //Loop through the entire subregion and see if it's all the same color
            int tempColor = subregion[0][0];

            //boolean flag saying it's not the same color
            boolean diffColor = false;

            for (int[] row : subregion) {
                for (int col : row) {
                    //if we've hit a new color
                    if (row[col] != tempColor) {
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
                compressImage(sub1);
                compressImage(sub2);
                compressImage(sub3);
                compressImage(sub4);
            }
            else{
                //It's one color
                //Just add that number to the list
                compressedList.add((subregion[0][0]));
            }
        }
        else{
            //it wasn't bigger than one pixel
            //just add that number
            compressedList.add(subregion[0][0]);
        }
    }

    public static int[][] populateBoard(String filename){
        //Create the scanner and open the file
        Scanner s = null;
        try {
            File in  = new File(filename);
            s = new Scanner(in);
        }catch(FileNotFoundException fne){
            System.err.print("File not found");
        }

        //Read in all the raw data
        ArrayList<Integer> rawData  = new ArrayList<>();
        while(s.hasNextInt()){
            rawData.add(s.nextInt());
        }

        //Save the size of the board
        int size = (int)Math.sqrt(rawData.size());

        //Create the board
        int[][] board = new int[size][size];

        //Fill the board
        for(int[] row : board){
            for(int col : row){
                row[col] = rawData.remove(0);
            }
        }

        //return the board
        return board;
    }
}
