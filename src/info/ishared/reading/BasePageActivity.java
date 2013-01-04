package info.ishared.reading;

import android.app.Activity;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 13-1-4
 * Time: PM1:48
 */
public class BasePageActivity extends Activity {
    protected String fileName;
    protected String bookNumber;
    protected String lastFileName = "NONE";
    protected String nextFileName = "NONE";


    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
