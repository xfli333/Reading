package info.ishared.reading.bean;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-20
 * Time: PM4:31
 */
public class Book implements Serializable {
    private Long id;
    private String bookNumber;
    private String bookName;
    private String bookSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookSize() {
        return bookSize;
    }

    public void setBookSize(String bookSize) {
        this.bookSize = bookSize;
    }
}
