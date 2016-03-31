import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by Vanessa on 2016-03-30.
 */
public class MongoIntegrator {

    public MongoIntegrator() {
    }

    public MongoClient connectToMongoDB( String connection ) {
        MongoClientURI mongoURI = new MongoClientURI(connection);
        return new MongoClient(mongoURI);
    }

    // get collection
    public MongoCollection getCollection(MongoDatabase db, String collectionStr) {
        return db.getCollection(collectionStr);
    }

    // insert into collection

    // find a value in the collection based on a key
}
