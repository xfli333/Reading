package info.ishared.reading.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import info.ishared.reading.AppConfig;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 11-12-7
 * Time: 下午5:06
 */
public class DBHelper {
    private static final String TAG = "DBAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private Context context;

    private DBHelper(Context context) {
        this.context = context;
    }

    private static DBHelper DBHelper = null;

    public static DBHelper getInstance(Context context) {
        if (null == DBHelper) DBHelper = new DBHelper(context);
        return DBHelper;
    }

    public DBHelper open() throws SQLException {
        mDbHelper = new DatabaseHelper(this.context);
        Log.d(AppConfig.TAG,"open");
        mDb = mDbHelper.getWritableDatabase();
        Log.d(AppConfig.TAG,"md open"+mDb.getPath());

        return this;
    }

    public DBHelper close() throws SQLException {
        mDb.close();
        mDbHelper.close();

        return this;
    }

    public void clearDBTableByName(String tableName) {
        mDb.delete(tableName, "1=1", null);
    }

    public SQLiteDatabase getMDB() {
        return mDb;
    }



    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DBConfig.DB_NAME, null, DBConfig.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DBConfig.Book.CREATE_BOOK_SQL);
            db.execSQL(DBConfig.ReadHistory.CREATE_READ_HISTORY_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DBConfig.Book.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + DBConfig.ReadHistory.TABLE_NAME);
            onCreate(db);
        }

    }



}
