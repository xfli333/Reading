package info.ishared.reading;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import info.ishared.reading.cache.SimplyCache;
import info.ishared.reading.controller.PageController;
import info.ishared.reading.util.BookUtils;
import info.ishared.reading.util.CacheUtils;
import info.ishared.reading.util.ToastUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 13-1-4
 * Time: AM10:50
 */
public class FirstPageActivity extends BasePageActivity implements GestureDetector.OnGestureListener {
    private TextView mTextView;
    private TextView mTitle;
    private Handler mHandler;

    private GestureDetector mGestureDetector;
    private PageController mController;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.show_content);
        mController = new PageController(this,SecondPageActivity.class);
        mHandler = new Handler();
        Bundle extras = getIntent().getExtras();

        bookNumber = extras.getString("bookNumber");
        fileName = extras.getString("fileName");
        CacheUtils.initCache(bookNumber,fileName);

        lastFileName = BookUtils.getLastFileName(bookNumber,fileName);
        nextFileName = BookUtils.getNextFileName(bookNumber,fileName);

        mGestureDetector = new GestureDetector(this, this);
        mTextView = (TextView) this.findViewById(R.id.html_content);
        mTitle = (TextView) this.findViewById(R.id.read_title);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTextView.setText(mController.getContent());
                mTitle.setText(SimplyCache.menuCache.get(fileName));

                mController.recordReadHistory();
            }
        });
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
        boolean isScroll = Math.abs(motionEvent1.getY() - motionEvent2.getY()) < PageController.FLING_SCROLL_DISTANCE;

        if (motionEvent1.getX() - motionEvent2.getX() > PageController.FLING_MIN_DISTANCE
                && isScroll ) {
            // Fling left NExt
            if(BookUtils.NONE.equals(nextFileName)){
                ToastUtils.showMessage(this,"已到最后一页");
            }else{
                mController.GoToNextPage(bookNumber, nextFileName);
            }
        } else if (motionEvent2.getX() - motionEvent1.getX() > PageController.FLING_MIN_DISTANCE
                && isScroll && !BookUtils.NONE.equals(lastFileName)) {
            // Fling right
            if(BookUtils.NONE.equals(lastFileName)){
                ToastUtils.showMessage(this,"已到第一页");
            }else{
                mController.GoToLastPage(bookNumber, lastFileName);
            }
        }

        return false;
    }

//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public String getBookNumber() {
//        return bookNumber;
//    }
//
//    public void setBookNumber(String bookNumber) {
//        this.bookNumber = bookNumber;
//    }
}