/**
 * Base class for format of data to be fetched from the code base
 * 
 * @author z003p4sp
 *
 */
public class KeyMapEntry {

    public KeyMapEntry(String key, String fileName, String startLocation, String startLineNumber, String stopLocation, String stopLineNumber, String comment) {
        super();
        this.key = key;
        this.fileName = fileName;
        this.startLocation = startLocation;
        this.startLineNumber = startLineNumber;
        this.stopLocation = stopLocation;
        this.stopLineNumber = stopLineNumber;
        this.comment = comment;
    }

    private String key;
    private String fileName;
    private String startLocation;
    private String startLineNumber;
    private String stopLocation;
    private String stopLineNumber;
    private String comment;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(String stopLocation) {
        this.stopLocation = stopLocation;
    }

    public String getStartLineNumber() {
        return startLineNumber;
    }

    public void setStartLineNumber(String startLineNumber) {
        this.startLineNumber = startLineNumber;
    }

    public String getStopLineNumber() {
        return stopLineNumber;
    }

    public void setStopLineNumber(String stopLineNumber) {
        this.stopLineNumber = stopLineNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
