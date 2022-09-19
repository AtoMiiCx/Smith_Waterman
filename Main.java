import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;

public class Main {

    //Helper function to print 2d array as matrix
    public static void printRow(Object[] row) {
        for (Object i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        //Read the data file and specific lines with DNA sequences
        File data = new File("E:\\OneDrive - Efrei\\Concordia\\Assignment 2\\Smith_Waterman\\DNA_data.txt");
        Scanner read = new Scanner(data);
        String[] dna = new String[10];
        int i = 0;
        while (i < 10) {
            int ind = read.nextLine().indexOf(">");
            if (ind != -1) {
                dna[i] = read.nextLine();
                i++;
            }
        }

        System.out.println("------ SMITH-WATERMAN ALGORITHM FOR DNA SEQUENCES ------\n");

        int rows = 12, columns = 12;
        Object[][] matrix = new Object[rows][columns];

        for (i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = 0;
            }
        }

        for (i = 0; i < 9; i++) {

            System.out.println("Local alignment between the following DNA sequences: \n");

            System.out.println(dna[i] + "  " + dna[i + 1] +"\n");

            //Initialization of the matrix

            for (int j = 2; j < 12; j++) {
                matrix[0][j] = dna[i].charAt(j - 2);
                matrix[j][0] = dna[i + 1].charAt(j - 2);
            }

            //Implement the Smith Waterman algorithm

            Smith_Waterman S = new Smith_Waterman(dna[i], dna[i + 1], matrix, -2);

            System.out.println("The score matrix is: \n");

            for (Object[] pur : S.dp) {
                printRow(pur);
            }

            System.out.println("\nThe score is: " + S.score + "\n");


            System.out.println("The matching sequences are: \n");

            System.out.println(S.getoutputsequences());

            System.out.println("\n");

        }


        read.close();
    }


}
