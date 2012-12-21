package info.ishared.reading;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 12-12-21
 * Time: 下午10:40
 */
public class ShowContentActivity extends Activity {
    private TextView mTextView;
    private String fileName;
    private String bookNumber;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.show_content);
        Bundle extras = getIntent().getExtras();

        bookNumber = extras.getString("bookNumber");
        fileName = extras.getString("fileName");
        mTextView =(TextView) this.findViewById(R.id.html_content);
        mTextView.setText(Html.fromHtml(getFileContent()));
    }

    private String getFileContent(){
        try {
           return FileUtils.readFileToString(new File(AppConfig.BOOK_DIRECTORY+"/"+bookNumber+"/"+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
