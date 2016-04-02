/**
 * @author Jeff Godisart, Delerina Hill, Vanessa Synesael
 * Western University - CS4411B - Winter 2016
 */
public class Event {

    private Content eventContent;
    private EventData eventData;

    public Content getEventContent() {
        return eventContent;
    }

    public void setEventContent(Content eventContent) {
        this.eventContent = eventContent;
    }

    public EventData getEventData() {
        return eventData;
    }

    public void setEventData(EventData eventData) {
        this.eventData = eventData;
    }
}
