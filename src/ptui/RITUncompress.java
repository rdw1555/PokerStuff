package ptui;

import model.RITQTNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RITUncompress {
    //Global variable - size of the image
    private static int size;

    //Global 2D array of uncompressed image values
    public static int[][] uncompressedImage;

    /**
     * main method
     * @param args - system arguments of teh compressed/uncomopressed file names
     */
    public void main(String[] args) {
        //Check for usage error
        if (args.length != 2) {
            System.out.println("Usage: java RITUncompress compressed.rit uncompressed.txt");
            return;
        }

        //Create a null scanner to read from the input file
        Scanner s = null;
        try {
            File in  = new File(args[0]);
            s = new Scanner(in);
        }catch(FileNotFoundException fne){
            System.err.print("File not found");
        }

        //Assert statement to make IntelliJ happy
        assert s != null;

        //Set the global size variable (size of the image)
        size = s.nextInt();

        //Create an ArrayList of the compressed image values
        ArrayList<Integer> compressed  = new ArrayList<>();
        while(s.hasNextInt()){
            compressed.add(s.nextInt());
        }

        //Print it out (testing)
        System.out.println(compressed.size());

        //Create a new RITQTNode off of the compressed image file
        RITQTNode root = parse(compressed);

        //Set the proper size of the uncompressedImage array[][]
        uncompressedImage = new int[(int)Math.sqrt(size)][(int)Math.sqrt(size)];

        populateUncompressed(root, uncompressedImage);

        //Get the uncompressed file to write to it
        File uncompressed = new File(args[1]);
        try {
            FileWriter writer = new FileWriter(uncompressed);
        } catch (IOException e) {
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


    public static void populateUncompressed(RITQTNode root, int[][] subregion){
        int tempVal = root.getVal();

        //if the value is -1, we want to split the main region into four smaller sub-regions
        if(tempVal == -1){
            int[][] sub1 = new int[subregion.length/2][subregion.length/2];
            int[][] sub2 = new int[subregion.length/2][subregion.length/2];
            int[][] sub3 = new int[subregion.length/2][subregion.length/2];
            int[][] sub4 = new int[subregion.length/2][subregion.length/2];

            //Populate those sub-regions
            populateUncompressed(root.getUpperLeft(), sub1);
            populateUncompressed(root.getUpperRight(), sub2);
            populateUncompressed(root.getLowerLeft(), sub3);
            populateUncompressed(root.getLowerRight(), sub4);

            //After those have run, fill in the big board with the new values
            for(int row = 0; row < subregion.length; row++){
                for(int col = 0; col < subregion.length; col++){
                    //sub1 (0,0)
                    subregion[row][col] = sub1[row][col];
                    //sub2 (0,0+sub2.length)
                    subregion[row][col + sub2.length] = sub2[row][col];
                    //sub3 (0+sub3.length, 0)
                    subregion[row + sub3.length][col] = sub3[row][col];
                    //sub4 (0+sub4.length, 0+sub4.length)
                    subregion[row + sub4.length][col + sub4.length] = sub4[row][col];
                }
            }
        }
        //else, fill the current sub-region with the tempVal
        else{
            for(int[] row : subregion){
                for(int i : row){
                    row[i] = root.getVal();
                }
            }
        }
    }

}