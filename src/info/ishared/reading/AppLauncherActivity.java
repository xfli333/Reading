package info.ishared.reading;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;
import info.ishared.reading.bean.Book;
import info.ishared.reading.controller.AppLauncherController;
import info.ishared.reading.db.BookOperator;
import info.ishared.reading.util.PageJumpUtils;

import java.io.File;

public class AppLauncherActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private AppLauncherController mController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLoading();
        mController = new AppLauncherController(this);
        mController.checkAndCreateDataDirectory();
//        insertTestData();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                PageJumpUtils.jump(AppLauncherActivity.this, BookListActivity.class);
                AppLauncherActivity.this.finish();
            }
        }, 2000);
    }

    private void showLoading() {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, "", duration);
        toast.getView().setBackground(getResources().getDrawable(R.drawable.splash));
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }


    private void insertTestData(){
        Book book=new Book();
        book.setBookNumber("101010");
        book.setBookName("凡人修仙传");
        book.setChapterSize(23);
        book.setBookSize("20M");
        BookOperator bookOperator=new BookOperator(this);
        bookOperator.createBook(book);
    }
}
