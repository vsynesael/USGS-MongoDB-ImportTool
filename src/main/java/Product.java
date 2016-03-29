import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="product", namespace = "http://earthquake.usgs.gov/distribution/product")
public class Product
{
    private static class Property
    {
        String _name;

        @XmlAttribute(name="name")
        public String getName() {return _name;}
        public void setName(String n) {_name=n;}

        String _value;

        @XmlAttribute(name="value")
        public String getValue() {return _value;}
        public void setValue(String v) {_value=v;}
    }

    private Property[] _propertyList;

    @XmlElement(name="property")
    public Property[] getPropertyList() {return _propertyList;}
    public void setPropertyList(Property[] p) {_propertyList=p;}
}
