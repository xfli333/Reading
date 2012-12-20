package info.ishared.reading.db;

import android.content.ContentValues;
import android.content.Context;
import info.ishared.reading.bean.Book;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-20
 * Time: PM4:45
 */
public class BookOperator {
    private DBHelper mDBHelper;

    public BookOperator(Context context) {
        this.mDBHelper = DBHelper.getInstance(context);
    }

    public Long createBook(Book book){
        this.mDBHelper.open();
        ContentValues values = new ContentValues();
        values.put(DBConfig.Book.BOOK_NUMBER, book.getBookNumber());
        long id= this.mDBHelper.getMDB().insertOrThrow(DBConfig.Book.TABLE_NAME, null, values);
        this.mDBHelper.close();
        return id;
    }
}
