package info.ishared.reading;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import info.ishared.reading.cache.SimplyCache;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 12-12-21
 * Time: 下午10:40
 */
public class ShowContentActivity extends Activity implements GestureDetector.OnGestureListener {
    private TextView mTextView;
    private TextView mReadTitle;
    private String fileName;
    private String bookNumber;
    private final int FLING_MIN_DISTANCE = 50;
    private final int FLING_SCROLL_DISTANCE = 60;
    private GestureDetector mGestureDetector;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.show_content);
        Bundle extras = getIntent().getExtras();

        bookNumber = extras.getString("bookNumber");
        fileName = extras.getString("fileName");

        mGestureDetector = new GestureDetector(this,this);
        initMenuCache();
        mReadTitle = (TextView) this.findViewById(R.id.read_title);
        mReadTitle.setText(SimplyCache.menuCache.get(fileName));
        mTextView = (TextView) this.findViewById(R.id.html_content);
        mTextView.setText(Html.fromHtml(getFileContent()));

    }

    private void initMenuCache(){
        if(SimplyCache.menuCache.isEmpty()){
            String chapterNumber=fileName.substring(0,fileName.indexOf("/"));
            try {
               List<String> lines = FileUtils.readLines(new File(AppConfig.BOOK_DIRECTORY + "/" + bookNumber + "/" + chapterNumber + "/menu.txt"));
                for (String line : lines) {
                    SimplyCache.menuCache.put(line.split("=")[0], line.split("=")[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileContent() {
        try {
            return FileUtils.readFileToString(new File(AppConfig.BOOK_DIRECTORY + "/" + bookNumber + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        mGestureDetector.onTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        mGestureDetector.onTouchEvent(motionEvent);
//        return true;
//    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float v, float v2) {
        boolean isScroll=Math.abs(motionEvent1.getY() - motionEvent2.getY()) < FLING_SCROLL_DISTANCE;
        if (motionEvent1.getX() - motionEvent2.getX() > FLING_MIN_DISTANCE && isScroll ) {
            // Fling left NExt
        } else if (motionEvent2.getX() - motionEvent1.getX() > FLING_MIN_DISTANCE  && isScroll ) {
            // Fling right
        }
        return false;
    }
}
