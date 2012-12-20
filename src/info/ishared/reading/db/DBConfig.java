package info.ishared.reading.db;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 11-12-7
 * Time: 下午4:47
 */
public class DBConfig {
    public static final String DB_NAME = "reading.db";
    public static final int DB_VERSION = 0x00000001;

    public static class Book {

        public static final String TABLE_NAME = "books";

        public static final String ID = "id";
        public static final String BOOK_NUMBER = "book_number";
        public static final String BOOK_NAME = "book_name";
        public static final String BOOK_SIZE = "book_size";

        public static final String CREATE_BOOK_SQL = "create table " + TABLE_NAME
                + "("
                + ID + " integer primary key autoincrement, "
                + BOOK_NUMBER + " TEXT, "
                + BOOK_NAME + " TEXT, "
                + BOOK_SIZE + "TEXT "
                + ");";
    }


}
