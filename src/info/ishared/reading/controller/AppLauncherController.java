package info.ishared.reading.controller;

import android.os.Environment;
import info.ishared.reading.AppConfig;
import info.ishared.reading.AppLauncherActivity;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 13-1-5
 * Time: PM2:44
 */
public class AppLauncherController {
    private AppLauncherActivity activity;

    public AppLauncherController(AppLauncherActivity activity){
        this.activity = activity;
    }

    public void checkAndCreateDataDirectory() {
        File sd = Environment.getExternalStorageDirectory();
        String path = sd.getPath() + "/" + AppConfig.DATA_DIRECTORY;
        File file = new File(path);
        if (!file.exists()) file.mkdir();

        String zipPath = sd.getPath() + "/" + AppConfig.DATA_DIRECTORY+"/"+AppConfig.ZIP_DIRECTORY;
        File zipDir = new File(zipPath);
        if (!zipDir.exists()) zipDir.mkdir();

        String bookPath = sd.getPath() + "/" + AppConfig.DATA_DIRECTORY+"/"+AppConfig.BOOK_DIRECTORY;
        File bookDir = new File(bookPath);
        if (!bookDir.exists()) bookDir.mkdir();
    }
}
