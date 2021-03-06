import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Custom import tool for the USGS DYFI Open Source data
 * Tool imports the data into a MongoDB
 *
 * Developed by: Vanessa Synesael, Jeff Godisart and Delerina Hill
 * Western University - CS4411B - Winter 2016
 */
public class Main {

    /**
     * Method to iterate over the files and folders in the dyfi folder
     * Recursively calls itself when it finds a folder.
     * For files, it calls the parsers and returns the POJO representing the event.
     * @param folder - folder being processed
     * @param eventList - maintained list of events that need to be passed b/c of recursive call
     * @return POJO representing the event being parsed
     */
    private static void iterateFiles(File folder, List<Event> eventList) {
        Event event = new Event();
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                // if the content and data fields have been populated we need to create a new event
                // and add the previous one to the list of events
                if (event.getEventContent() != null && event.getEventData() != null) {
                    eventList.add(event);
                    event = new Event();
                }
                // content file returns all the generic content for the event (ie. files)
                if (file.getName().equals("contents.xml"))
                {
                    try
                    {
                        // parse the file
                        JAXBContext contentsContext = JAXBContext.newInstance(Content.class);
                        Unmarshaller contentsUnmarshaller = contentsContext.createUnmarshaller();
                        Content content = (Content)contentsUnmarshaller.unmarshal(file);
                        event.setEventContent(content);
                        if (event.getEventContent() != null && event.getEventData() != null) {
                            eventList.add(event);
                            event = new Event();
                        }
                    } catch(JAXBException je)
                    {
                        je.printStackTrace();
                    }
                }
                // event data file returns all the event specific data
                else if (file.getName().equals("event_data.xml"))
                {
                    try
                    {
                        // parse the file
                        JAXBContext eventDataContext = JAXBContext.newInstance(EventData.class);
                        Unmarshaller eventDataUnmarshaller = eventDataContext.createUnmarshaller();
                        EventData eventData = (EventData) eventDataUnmarshaller.unmarshal(file);
                        event.setEventData(eventData);
                        if (event.getEventContent() != null && event.getEventData() != null) {
                            eventList.add(event);
                            event = new Event();
                        }
                    } catch (JAXBException je)
                    {
                        je.printStackTrace();
                    }
                }
                    // omit all other files
            }
            // recursively call the folders to search for files
            else {
                iterateFiles(file, eventList);
            }
        }
    }

    static private MongoClient connectToMongoDB( String connection ) {
        MongoClientURI mongoURI = new MongoClientURI(connection);
        return new MongoClient(mongoURI);
    }

    public static void main(String args[]) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter the root folder for DYFI data:");
        final String inFolder = read.nextLine();

        final File folder = new File("C:\\Users\\Vanessa\\courses\\cs4411\\import\\dyfi");
        List<Event> eventList = new ArrayList<>();

        iterateFiles(folder, eventList);

        System.out.println("Enter Mongo Connection URI:");
        // need to determine what input string we need to make a connection to our db
        final String mongoURI = read.nextLine();
        MongoClient connection = connectToMongoDB("mongodb://localhost:27017");
        MongoDatabase db = connection.getDatabase("earthquake");
        System.out.println("Connected successully to db " + db.getName());

        // insert all the events into the db
        Insert mongoInsert = new Insert();
        boolean success = mongoInsert.insertEvents(eventList, db);

    }
}
