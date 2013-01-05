package info.ishared.reading.controller;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import info.ishared.reading.AppConfig;
import info.ishared.reading.BasePageActivity;
import info.ishared.reading.BookListActivity;
import info.ishared.reading.bean.Book;
import info.ishared.reading.db.BookOperator;
import info.ishared.reading.http.AbstractHttpController;
import info.ishared.reading.http.HttpEventListener;
import info.ishared.reading.util.ToastUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 13-1-5
 * Time: PM2:48
 */
public class BookListController extends AbstractHttpController {

    private BookListActivity mActivity;
    private BookOperator bookOperator;

    public BookListController(BookListActivity mActivity) {
        super(String.class);
        this.mActivity = mActivity;
        this.bookOperator = new BookOperator(mActivity);
    }

    public List<Book> queryLocalBooks(){
        return bookOperator.listAllBooks();
    }

    public void checkVersion(){
        this.sendGetRequest(AppConfig.SERVER_URL,"utf-8",new HttpEventListener() {
            @Override
            public void onNetworkAbort() {

            }

            @Override
            public void onNetworkError() {

            }

            @Override
            public void onRequestFinish(Object response) {
                Log.d("onRequestFinish",response.toString());
            }

            @Override
            public void onResponseError() {

            }
        });
    }
}
