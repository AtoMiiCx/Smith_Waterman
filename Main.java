import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        File data = new File("E:/OneDrive - Efrei/Concordia/Assignment 2/Smith_Waterman/DNA_data.txt");
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
    }
}
