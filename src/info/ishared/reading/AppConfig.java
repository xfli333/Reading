package info.ishared.reading;

import android.os.Environment;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-20
 * Time: PM3:04
 */
public class AppConfig {
    public static final String SD_PATH = Environment.getExternalStorageDirectory().getPath()+"/";
    public static final String TAG="reading";

    public static final String DATA_DIRECTORY=SD_PATH+"reading_data";
    public static final String ZIP_DIRECTORY=DATA_DIRECTORY+"/zip";
    public static final String BOOK_DIRECTORY=DATA_DIRECTORY+"/book";

    public static final String SERVER_URL="http://172.17.22.234:8080/Reading-Server";
    public static final String LIST_BOOKS_URL=SERVER_URL+"/book/listBookAsJson";

}
