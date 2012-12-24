package info.ishared.reading.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import info.ishared.reading.bean.Book;
import info.ishared.reading.bean.ReadHistory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-24
 * Time: PM2:21
 */
public class ReadHistoryOperator {
    private DBHelper mDBHelper;


    public ReadHistoryOperator(Context context) {
        this.mDBHelper = DBHelper.getInstance(context);
    }

    public ReadHistory queryByBookNumber(String bookNumber) {
        this.mDBHelper.open();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DBConfig.ReadHistory.TABLE_NAME);
        builder.appendWhere(DBConfig.ReadHistory.BOOK_NUMBER + " = '" + bookNumber + "'");
        String arrColumn[] = {
                DBConfig.ReadHistory.ID, DBConfig.ReadHistory.BOOK_NUMBER, DBConfig.ReadHistory.READ_LOCATION
        };
        Cursor c = builder.query(this.mDBHelper.getMDB(), arrColumn, null, null, null, null, null);
        c.moveToFirst();
        List<ReadHistory> data = new ArrayList<ReadHistory>();
        while (!c.isAfterLast()) {
            ReadHistory item = new ReadHistory();
            item.setId(c.getInt(c.getColumnIndex(arrColumn[0])));
            item.setBookNumber(c.getString(c.getColumnIndex(arrColumn[1])));
            item.setReadLocation(c.getString(c.getColumnIndex(arrColumn[2])));
            data.add(item);
            c.moveToNext();
        }
        c.close();
        this.mDBHelper.open();
        return  data.isEmpty() ? null : data.get(0);
    }


    public void updateReadHistory(ReadHistory readHistory){
        this.mDBHelper.open();
        ContentValues values = new ContentValues();
        values.put(DBConfig.ReadHistory.READ_LOCATION, readHistory.getReadLocation());
        StringBuilder sb = new StringBuilder();
        sb.append(DBConfig.ReadHistory.BOOK_NUMBER + "='" + readHistory.getBookNumber() + "'");
        this.mDBHelper.getMDB().update(DBConfig.ReadHistory.TABLE_NAME, values, sb.toString(), null);
        this.mDBHelper.close();
    }

    public void createReadHistory(ReadHistory readHistory) {
        this.mDBHelper.open();
        ContentValues values = new ContentValues();
        values.put(DBConfig.ReadHistory.BOOK_NUMBER, readHistory.getBookNumber());
        values.put(DBConfig.ReadHistory.READ_LOCATION, readHistory.getReadLocation());
        this.mDBHelper.getMDB().insertOrThrow(DBConfig.ReadHistory.TABLE_NAME, null, values);
        this.mDBHelper.close();
    }

}
