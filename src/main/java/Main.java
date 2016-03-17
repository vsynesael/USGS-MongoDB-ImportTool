import java.io.File;
import java.util.Scanner;

/**
 * Custom import tool for the USGS DYFI Open Source data
 * Tool imports the data into a MongoDB
 *
 * Developed by: Vanessa Synesael, Jeff Godisart and Delerina Hill
 * Western University - CS4411B - Databases II
 */
public class Main {

    /**
     * Method to iterate over the files and folders in the dyfi folder
     * Recursively calls itself when it finds a folder.
     * For files, it calls the parsers and returns the POJO representing the event.
     * @param folder - folder being processed
     * @return POJO representing the event being parsed
     */
    private static void iterateFiles(File folder) {
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                // parse file
            }
            else {
                iterateFiles(file);
            }
        }
    }

    public static void main(String args[]) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter the root folder for DYFI data:");
        final String inFolder = read.nextLine();

        final File folder = new File(inFolder);


    }
}
