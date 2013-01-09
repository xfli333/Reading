package info.ishared.reading.controller;

import info.ishared.reading.AppConfig;
import info.ishared.reading.DownloadBookActivity;
import info.ishared.reading.bean.Book;
import info.ishared.reading.http.AbstractHttpController;
import info.ishared.reading.http.HttpEventListener;
import info.ishared.reading.util.JsonUtils;
import info.ishared.reading.util.ToastUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 13-1-9
 * Time: PM4:58
 */
public class DownloadController extends AbstractHttpController {
    private DownloadBookActivity mActivity;

    public DownloadController(DownloadBookActivity mActivity) {
        super(String.class);
        this.mActivity = mActivity;
    }

    public void listServerBooks(){
        this.sendGetRequest(AppConfig.LIST_BOOKS_URL,"utf-8",new HttpEventListener() {
            @Override
            public void onNetworkAbort() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onNetworkError() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onRequestFinish(Object response) {
                ToastUtils.showMessage(mActivity,response.toString());
                List<Book> books= JsonUtils.createBooksFromJson(response.toString());
                for (Book book : books){
                    ToastUtils.showMessage(mActivity,book.toString());
                }
            }

            @Override
            public void onResponseError() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
