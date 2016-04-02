import com.mongodb.Block;
import com.mongodb.DBCursor;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class to insert all the event data into the mongo database
 *
 * @author Jeff Godisart, Delerina Hill, Vanessa Synesael
 * Western University - CS4411B - Winter 2016
 */
public class Insert {

    private static final String DYFI = "dyfi";

    public Insert() {
    }

    public boolean insertEvents(List<Event> eventList, MongoDatabase db) {

        // get the dyfi collection
        MongoCollection dyfi = getDYFICollection(db);

        // insert all events
        for (Event event : eventList) {
            EventData data = event.getEventData();

            // only insert content data if the event data was successful
            if ( insertEventData( data, dyfi ) ) {
                Content content = event.getEventContent();
                if ( !insertContentData(content, dyfi) ) {
                    System.out.println("Error: insertion of event content not successuful");
                }
            }
            else {
                System.out.println("Error: insertion of eventId " +
                        data.getEventId() + " not successful");
            }
        }

        return true;
    }

    private MongoCollection getDYFICollection( MongoDatabase db ) {
        if (collectionExists(DYFI, db))
            return db.getCollection(DYFI);
        db.createCollection(DYFI);
        return db.getCollection(DYFI);
    }

    private static boolean collectionExists(final String collectionName, MongoDatabase db) {
        MongoIterable<String> collectionNames = db.listCollectionNames();
        for (final String name : collectionNames) {
            if (name.equalsIgnoreCase(collectionName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Insert all the event data into the mongo database collection dyfi
     * @param eventData event data
     * @param dyfi dyfi mongo collection
     * @return success of insertion
     */
    private boolean insertEventData( EventData eventData, MongoCollection dyfi ) {

        try {
            dyfi.insertOne(
                new Document()
                        .append("eventId", eventData.getEventId())
                        .append("source", eventData.getSource())
                        .append("network", eventData.getNetwork())
                        .append("region", eventData.getRegion())
                        .append("processTimestamp", eventData.getProcessTimestamp())
                        .append("ciimVersion", eventData.getCiimVersion())
                        .append("codeVersion", eventData.getCodeVersion())
                        .append("eventVersion", eventData.getEventVersion())
                        .append("event_data", new Document()
                                .append("magnitude", eventData.getEvent().getMagnitude())
                                .append("latitude", eventData.getEvent().getLatitude())
                                .append("longitude", eventData.getEvent().getLongitude())
                                .append("depth", eventData.getEvent().getDepth())
                                .append("eventTimestampe", eventData.getEvent().getEventTimestamp())
                                .append("localTime", eventData.getEvent().getLocalTime())
                                .append("locationText", eventData.getEvent().getLocationText())
                        )
                        .append("cdiSummary", new Document()
                                .append("nResponses", eventData.getCdiSummary().getNResponses())
                                .append("maxIntensity", eventData.getCdiSummary().getMaxIntensity())
                                .append("cityDb", eventData.getCdiSummary().getCityDb())
                        )

            );
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    /**
     * Inserts all the content data into the mongo data base collection dyfi
     * @param content event content
     * @param dyfi dyfi mongo collection
     * @return success of insertion
     */
    private boolean insertContentData( Content content, MongoCollection dyfi ) {

        // we have to update the existing documents with the content data
        // this means we need to find and return all the ids of the documents
        // so we can then find each document and update it with the content data

        return true;
    }
}
