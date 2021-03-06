package info.ishared.reading.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import info.ishared.reading.bean.Book;

import java.util.ArrayList;
import java.util.List;

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


    public List<Book> listAllBooks() {
        this.mDBHelper.open();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DBConfig.Book.TABLE_NAME);
        String arrColumn[] = {
                DBConfig.Book.ID, DBConfig.Book.BOOK_NUMBER, DBConfig.Book.BOOK_NAME, DBConfig.Book.BOOK_SIZE,DBConfig.Book.CHAPTER_SIZE
        };
        Cursor c = builder.query(this.mDBHelper.getMDB(), arrColumn, null, null, null, null, null);
        c.moveToFirst();
        List<Book> data = new ArrayList<Book>();
        while (!c.isAfterLast()) {
            Book item = new Book();
            item.setId(c.getLong(c.getColumnIndex(arrColumn[0])));
            item.setBookNumber(c.getString(c.getColumnIndex(arrColumn[1])));
            item.setBookName(c.getString(c.getColumnIndex(arrColumn[2])));
            item.setBookSize(c.getString(c.getColumnIndex(arrColumn[3])));
            item.setChapterSize(c.getInt(c.getColumnIndex(arrColumn[4])));
            data.add(item);
            c.moveToNext();
        }
        c.close();
        this.mDBHelper.close();
        return data;

    }

    public void createBook(Book book){
        this.mDBHelper.open();
        ContentValues values = new ContentValues();
        values.put(DBConfig.Book.BOOK_NUMBER, book.getBookNumber());
        values.put(DBConfig.Book.BOOK_NAME, book.getBookName());
        values.put(DBConfig.Book.BOOK_SIZE, book.getBookSize());
        values.put(DBConfig.Book.CHAPTER_SIZE, book.getChapterSize());
        this.mDBHelper.getMDB().insertOrThrow(DBConfig.Book.TABLE_NAME, null, values);
        this.mDBHelper.close();
    }
}
