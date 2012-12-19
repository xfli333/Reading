package info.ishared.reading;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import info.ishared.reading.util.PageJumpUtils;

public class AppLauncherActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private TextView mHtml;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLoading();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                PageJumpUtils.jump(AppLauncherActivity.this,BookListActivity.class);
                AppLauncherActivity.this.finish();
            }
        }, 2000);
    }

    private void showLoading() {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, "", duration);
        toast.getView().setBackground(getResources().getDrawable(R.drawable.splash));
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}
