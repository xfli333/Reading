package info.ishared.reading;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import info.ishared.reading.cache.SimplyCache;
import info.ishared.reading.util.BookUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 12-12-21
 * Time: 下午10:40
 */
public class ShowContentActivity extends Activity {
    private TextView mTextView;
    private TextView mReadTitle;
    private String fileName;
    private String bookNumber;

    private MyPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<View> mListViews;
    private LayoutInflater mInflater;

    private Handler mHandler;

    private View view1, view2, view3;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.content_container);
        mInflater = getLayoutInflater();
        Bundle extras = getIntent().getExtras();
        mHandler = new Handler();
        bookNumber = extras.getString("bookNumber");
        this.setFileName(extras.getString("fileName"));

        initMenuCache();

//        mReadTitle = (TextView) (mInflater.inflate(R.layout.show_content, null).findViewById(R.id.read_title));
//        mReadTitle.setText(SimplyCache.menuCache.get(this.getFileName()));
//        mTextView = (TextView)( mInflater.inflate(R.layout.show_content, null).findViewById(R.id.html_content));
//        mTextView.setText(Html.fromHtml(getFileContent(this.getFileName())));


        mAdapter = new MyPagerAdapter();
        mViewPager = (ViewPager) findViewById(R.id.view_pager_layout);
        mViewPager.setAdapter(mAdapter);

        mListViews = new ArrayList<View>();

        view1 = mInflater.inflate(R.layout.show_content, null);
        ((TextView) view1.findViewById(R.id.html_content)).setText(Html.fromHtml(getFileContent(this.getFileName())));
        ((TextView) view1.findViewById(R.id.read_title)).setText(SimplyCache.menuCache.get(this.getFileName()));
        mListViews.add(view1);

        view2 = mInflater.inflate(R.layout.show_content, null);
        mListViews.add(view2);

        view3 = mInflater.inflate(R.layout.show_content, null);
        mListViews.add(view3);


        //初始化当前显示的view
        mViewPager.setCurrentItem(0);

    }

    private void initMenuCache() {
        if (SimplyCache.menuCache.isEmpty()) {
            String chapterNumber = fileName.substring(0, fileName.indexOf("/"));
            try {
                List<String> lines = FileUtils.readLines(new File(AppConfig.BOOK_DIRECTORY + "/" + bookNumber + "/" + chapterNumber + "/menu.txt"));
                for (String line : lines) {
                    SimplyCache.menuCache.put(line.split("=")[0], line.split("=")[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileContent(String fileName) {
        try {
            return FileUtils.readFileToString(new File(AppConfig.BOOK_DIRECTORY + "/" + bookNumber + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View arg0, int position, Object arg2) {
//            if (position >= mListViews.size()) {
//                int newPosition = position % mListViews.size();
//                position = newPosition;
//                ((ViewPager) arg0).removeView(mListViews.get(position));
//            }
//            if (position < 0) {
//                position = -position;
//                ((ViewPager) arg0).removeView(mListViews.get(position));
//            }
        }

        @Override
        public void finishUpdate(View arg0) {
            Log.d("k", "finishUpdate");
        }

        @Override
        public int getCount() {
//            Log.d("k", "getCount");
//            return mListViews.size();
            return 100;
        }

        @Override
        public Object instantiateItem(View arg0, int position) {
            Log.d("instantiateItem", "===" + position);
            if (position >= mListViews.size()) {
                int newPosition = position % mListViews.size();

                position = newPosition;
            }
            if (position < 0) {
                position = -position;
            }
            try {
                final View currentView = mListViews.get(position);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("instantiateItem", "file name" + fileName);
                        Log.d("instantiateItem", "next file name" + BookUtils.getNextFileName(fileName));
                        String nextFileName = BookUtils.getNextFileName(fileName);
                        ((TextView) currentView.findViewById(R.id.html_content)).setText(Html.fromHtml(getFileContent(nextFileName)));
                        ((TextView) currentView.findViewById(R.id.read_title)).setText(SimplyCache.menuCache.get(nextFileName));
                        setFileName(nextFileName);
                    }
                });
                ((ViewPager) arg0).addView(currentView, 0);

            } catch (Exception e) {
            }
            Log.d("instantiateItem", "= xxx==" + position);
            return mListViews.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            Log.d("k", "isViewFromObject " + (arg0 == (arg1)));
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            Log.d("k", "restoreState");
        }

        @Override
        public Parcelable saveState() {
            Log.d("k", "saveState");
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            Log.d("k", "startUpdate");
        }


    }
}
