import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jeff Godisart, Delerina Hill, Vanessa Synesael
 * Western University - CS4411B - Winter 2016
 */
@XmlRootElement(name="contents")
public class Content
{
    public static class file
    {
        private String _title;

        @XmlAttribute
        public String getTitle() {return _title;}
        public void setTitle(String t) {_title=t;}

        private String _id;

        @XmlAttribute
        public String getId() {return _id;}
        public void setId(String i) {_id=i;}

        public static class format
        {
            private String _href;

            @XmlAttribute
            public String getHref() {return _href;}
            public void setHref(String h) {_href=h;}

            private String _type;

            @XmlAttribute
            public String getType() {return _type;}
            public void setType(String t) {_type=t;}
        }

        private format[] _formatList;

        @XmlElement(name="format")
        public format[] getFormatList() {return _formatList;}
        public void setFormatList(format[] f) {_formatList=f;}
    }

    private file[] _fileList;

    @XmlElement(name="file")
    public file[] getFileList() {return _fileList;}
    public void setFileList(file[] f) {_fileList=f;}
}
