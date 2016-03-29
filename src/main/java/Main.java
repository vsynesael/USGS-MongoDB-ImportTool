import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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
    private static void iterateFiles(java.io.File folder) {
        for (java.io.File file : folder.listFiles()) {
            if (file.isFile()) {
                if (file.getName().equals("contents.xml"))
                {
                    try
                    {
                        // parse the file
                        JAXBContext contentsContext = JAXBContext.newInstance(Contents.class);
                        Unmarshaller contentsUnmarshaller=contentsContext.createUnmarshaller();
                        Contents contents=(Contents)contentsUnmarshaller.unmarshal(file);
                        ;
                    } catch(JAXBException je)
                    {
                        je.printStackTrace();
                    }
                }
                else if (file.getName().equals("product.xml"))
                {
                    try
                    {
                        // parse the file
                        JAXBContext productContext = JAXBContext.newInstance(Product.class);
                        Unmarshaller productUnmarshaller=productContext.createUnmarshaller();
                        Product product=(Product)productUnmarshaller.unmarshal(file);
                        "h".toString();
                    } catch(JAXBException je)
                    {
                        je.printStackTrace();
                    }                }
                // omit all other files
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

        iterateFiles(folder);

    }
}
