package info.ishared.reading.asyn;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import info.ishared.reading.AppConfig;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 13-1-6
 * Time: PM4:09
 */
public class LoadNetworkImageTask extends AsyncTask<String, Void, Drawable> {
    private ImageView mImageView;
    private SimpleAdapter adapter;

    public LoadNetworkImageTask(ImageView mImageView,SimpleAdapter adapter) {
        this.mImageView = mImageView;
        this.adapter = adapter;
    }

    @Override
    protected Drawable doInBackground(String... urls) {
        Log.d(AppConfig.TAG+"-------","sssssss");
        return loadImageFromUrl(urls[0]);
    }

    @Override
    protected void onPostExecute(Drawable drawable) {
        Log.d(AppConfig.TAG+"-------","ffffffffffff");
        mImageView.setImageDrawable(drawable);
        adapter.notifyDataSetChanged();
    }

    private Drawable loadImageFromNetwork(String imageUrl) {
        Drawable drawable = null;
        if (imageUrl ==null || "".equals(imageUrl.trim())){
            return null;
        }
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            drawable = Drawable.createFromStream(new URL(imageUrl).openStream(), "src");
        } catch (IOException e) {
            Log.d("LoadNetworkImageTask", e.getMessage());
        }
        if (drawable == null) {
            Log.d("LoadNetworkImageTask", "null drawable");
        } else {
            Log.d("LoadNetworkImageTask", "not null drawable");
        }
        return drawable;
    }

    private Drawable loadImageFromLocalCacheDir(String imageUrl){
        Drawable drawable = null;
        if (imageUrl ==null || "".equals(imageUrl.trim())){
            return null;
        }
        String fileName=imageUrl.substring(imageUrl.lastIndexOf("/")+1);
        File imageFile=new File(AppConfig.DATA_DIRECTORY+"/",fileName);
        if(imageFile.exists()){
            drawable=Drawable.createFromPath(AppConfig.DATA_DIRECTORY+"/"+fileName);
        }
        return drawable;
    }
    private Drawable loadImageFromUrl(String imageUrl){
        Drawable drawable = loadImageFromLocalCacheDir(imageUrl);
        if (drawable == null){
            drawable = loadImageFromNetwork(imageUrl);
        }
        return drawable;
    }
}
