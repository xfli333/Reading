package info.ishared.reading;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-19
 * Time: PM5:00
 */
public class BookListActivity extends Activity {
    private TextView mHtml;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mHtml=(TextView)this.findViewById(R.id.html);
        mHtml.setText("hello world");
    }
}