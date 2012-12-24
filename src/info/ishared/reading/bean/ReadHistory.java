package info.ishared.reading.bean;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-24
 * Time: PM2:16
 */
public class ReadHistory implements Serializable {
    private long id;
    private String bookNumber;
    private String readLocation;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getReadLocation() {
        return readLocation;
    }

    public void setReadLocation(String readLocation) {
        this.readLocation = readLocation;
    }
}
