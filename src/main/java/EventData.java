import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jeff Godisart, Delerina Hill, Vanessa Synesael
 * Western University - CS4411B - Winter 2016
 */
@XmlRootElement(name="event_data")
public class EventData
{
    private String _eventId;

    @XmlAttribute(name="eventid")
    public String getEventId() {return _eventId;}
    public void setEventId(String e) {_eventId=e;}

    private String _source;

    @XmlAttribute(name="source")
    public String getSource() {return _source;}
    public void setSource(String s) {_source=s;}

    private String _network;

    @XmlAttribute(name="network")
    public String getNetwork() {return _network;}
    public void setNetwork(String n) {_network=n;}

    private String _region;

    @XmlAttribute(name="region")
    public String getRegion() {return _region;}
    public void setRegion(String r) {_region=r;}

    private String _processTimestamp;

    @XmlAttribute(name="process_timestamp")
    public String getProcessTimestamp() {return _processTimestamp;}
    public void setProcessTimestamp(String p) {_processTimestamp=p;}

    private String _ciimVersion;

    @XmlAttribute(name="ciim_version")
    public String getCiimVersion() {return _ciimVersion;}
    public void setCiimVersion(String c) {_ciimVersion=c;}

    private String _codeVersion;

    @XmlAttribute(name="code_version")
    public String getCodeVersion() {return _codeVersion;}
    public void setCodeVersion(String c) {_codeVersion=c;}

    private String _eventVersion;

    @XmlAttribute(name="event_version")
    public String getEventVersion() {return _eventVersion;}
    public void setEventVersion(String e) {_eventVersion=e;}

    public static class Event
    {
        private String _magnitude;

        @XmlAttribute(name="magnitude")
        public String getMagnitude() {return _magnitude;}
        public void setMagnitude(String m) {_magnitude=m;}

        private String _latitude;

        @XmlAttribute(name="latitude")
        public String getLatitude() {return _latitude;}
        public void setLatitude(String l) {_latitude=l;}

        private String _longitude;

        @XmlAttribute(name="longitude")
        public String getLongitude() {return _longitude;}
        public void setLongitude(String l) {_longitude=l;}

        private String _depth;

        @XmlAttribute(name="depth")
        public String getDepth() {return _depth;}
        public void setDepth(String d) {_depth=d;}

        private String _eventTimestamp;

        @XmlAttribute(name="event_timestamp")
        public String getEventTimestamp() {return _eventTimestamp;}
        public void setEventTimestamp(String e) {_eventTimestamp=e;}

        private String _localTime;

        @XmlAttribute(name="localtime")
        public String getLocalTime() {return _localTime;}
        public void setLocalTime(String l) {_localTime=l;}

        private String _locationText;

        @XmlAttribute(name="locationtext")
        public String getLocationText() {return _locationText;}
        public void setLocationText(String l) {_locationText=l;}
    }

    private Event _event;

    @XmlElement(name="event")
    public Event getEvent() {return _event;}
    public void setEvent(Event e) {_event=e;}

    public static class Cdi_Summary
    {
        String _nResponses;

        @XmlAttribute(name="nresponses")
        public String getNResponses() {return _nResponses;}
        public void setNResponses(String n) {_nResponses=n;}

        private String _maxIntensity;

        @XmlAttribute(name="max_intensity")
        public String getMaxIntensity() {return _maxIntensity;}
        public void setMaxIntensity(String m) {_maxIntensity=m;}

        private String _cityDb;

        @XmlAttribute(name="citydb")
        public String getCityDb() {return _cityDb;}
        public void setCityDb(String c) {_cityDb=c;}
    }

    private Cdi_Summary _cdiSummary;

    @XmlElement(name="cdi_summary")
    public Cdi_Summary getCdiSummary() {return _cdiSummary;}
    public void setCdiSummary(Cdi_Summary c) {_cdiSummary=c;}
}
