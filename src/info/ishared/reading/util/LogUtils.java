package info.ishared.reading.util;

import android.util.Log;
import info.ishared.reading.AppConfig;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 13-2-22
 * Time: PM3:19
 */
public class LogUtils {
    public static void log(String msg){
        Log.d(AppConfig.TAG,msg);
    }
}
