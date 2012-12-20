package info.ishared.reading;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Html;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import info.ishared.reading.util.PageJumpUtils;

import java.io.File;

public class AppLauncherActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showLoading();
        checkDataDirectory();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                PageJumpUtils.jump(AppLauncherActivity.this, BookListActivity.class);
                AppLauncherActivity.this.finish();
            }
        }, 2000);
    }

    private void showLoading() {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, "", duration);
        toast.getView().setBackground(getResources().getDrawable(R.drawable.splash));
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    private void checkDataDirectory() {
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
