import com.mongodb.BasicDBObject;
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
            Content content = event.getEventContent();

            // only insert content data if the event data was successful
            if ( !insertEventData( data, content, dyfi ) ) {
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
    private boolean insertEventData( EventData eventData, Content content, MongoCollection dyfi ) {

        Document event = new Document();
        event.append("eventId", eventData.getEventId());
        event.append("source", eventData.getSource());
        event.append("network", eventData.getNetwork());
        event.append("region", eventData.getRegion());
        event.append("processTimestamp", eventData.getProcessTimestamp());
        event.append("ciimVersion", eventData.getCiimVersion());
        event.append("codeVersion", eventData.getCodeVersion());
        event.append("eventVersion", eventData.getEventVersion());

        Document eventDataObj = new Document();
        eventDataObj.append("magnitude", eventData.getEvent().getMagnitude());
        eventDataObj.append("latitude", eventData.getEvent().getLatitude());
        eventDataObj.append("longitude", eventData.getEvent().getLongitude());
        eventDataObj.append("depth", eventData.getEvent().getDepth());
        eventDataObj.append("eventTimestampe", eventData.getEvent().getEventTimestamp());
        eventDataObj.append("localTime", eventData.getEvent().getLocalTime());
        eventDataObj.append("locationText", eventData.getEvent().getLocationText());
        event.append("eventData", eventDataObj);

        Document cdiSummary = new Document();
        cdiSummary.append("nResponses", eventData.getCdiSummary().getNResponses());
        cdiSummary.append("maxIntensity", eventData.getCdiSummary().getMaxIntensity());
        cdiSummary.append("cityDb", eventData.getCdiSummary().getCityDb());
        event.append("cdiSummary", cdiSummary);

        insertContentData(content, event);

        try {
            dyfi.insertOne( event );
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    /**
     * Inserts all the content data into the mongo data base collection dyfi
     * @param content event content
     * @param event root event document
     */
    private void insertContentData( Content content, Document event ) {

        // create a list basic object for files
        List<Document> files = new ArrayList<>();
        // add all the files
        for (Content.file file : content.getFileList()) {
            Document fileObj = new Document();
            fileObj.put("title", file.getTitle());
            fileObj.put("id", file.getId());
            List<Document> formats = new ArrayList<>();
            // add all the formats
            for (Content.file.format format : file.getFormatList()) {
                Document formatObj = new Document();
                formatObj.put("href", format.getHref());
                formatObj.put("type", format.getType());
                formats.add(formatObj);
            }
            fileObj.put("formats", formats);
            files.add(fileObj);
        }
        // append all the files to the root event
        event.append("files", files);

    }
}
