package info.ishared.reading.controller;

import android.content.Intent;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import info.ishared.reading.BasePageActivity;
import info.ishared.reading.R;
import info.ishared.reading.bean.ReadHistory;
import info.ishared.reading.db.ReadHistoryOperator;
import info.ishared.reading.util.BookUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 13-1-4
 * Time: PM1:11
 */
public class PageController {
    public final static int FLING_MIN_DISTANCE = 50;
    public final static int FLING_SCROLL_DISTANCE = 60;
    private BasePageActivity currentPageActivity;
    private Class nextPageActivityClass;
    private ReadHistoryOperator mReadHistoryOperator;


    public PageController(BasePageActivity currentPageActivity,Class nextPageActivityClass) {
        this.currentPageActivity = currentPageActivity;
        this.nextPageActivityClass = nextPageActivityClass;
        mReadHistoryOperator=new ReadHistoryOperator(currentPageActivity);
    }


    public void GoToNextPage(String bookNumber,String nextFileName){
        Intent intent = new Intent(currentPageActivity, nextPageActivityClass);
        intent.putExtra("bookNumber", bookNumber);
        intent.putExtra("fileName", nextFileName);
        currentPageActivity.startActivity(intent);
        currentPageActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        currentPageActivity.finish();
    }

    public void GoToLastPage(String bookNumber,String lastFileName){
        Intent intent = new Intent(currentPageActivity, nextPageActivityClass);
        intent.putExtra("bookNumber", bookNumber);
        intent.putExtra("fileName", lastFileName);
        currentPageActivity.startActivity(intent);
        currentPageActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        currentPageActivity.finish();
    }

    public Spanned getContent(){
        return Html.fromHtml(BookUtils.getFileContent(currentPageActivity.getBookNumber(),currentPageActivity.getFileName()));
    }

    public void recordReadHistory(){
        ReadHistory readHistory = new ReadHistory();
        readHistory.setBookNumber(currentPageActivity.getBookNumber());
        readHistory.setReadLocation(currentPageActivity.getFileName());
        ReadHistory dbRecord = this.mReadHistoryOperator.queryByBookNumber(readHistory.getBookNumber());
        Log.d("ReadHistory",readHistory.getReadLocation());
        if (dbRecord == null) {
            this.mReadHistoryOperator.createReadHistory(readHistory);
        } else {
            this.mReadHistoryOperator.updateReadHistory(readHistory);
        }
    }

}
