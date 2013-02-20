package info.ishared.reading;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import info.ishared.reading.bean.Book;
import info.ishared.reading.controller.BookListController;
import info.ishared.reading.util.CacheUtils;
import info.ishared.reading.util.ZipUtils;
import org.apache.commons.compress.archivers.zip.ZipUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-19
 * Time: PM5:00
 */
public class BookListActivity extends RoboSherlockActivity {
    @InjectView(R.id.gridView) private GridView mGridView;
    private List<Map<String, Object>> gridItems = new ArrayList<Map<String, Object>>();
    private String bookNumber;
    private String chapterSize;
    private SimpleAdapter adapter;
    private BookListController mController;
    @InjectView(R.id.test_btn) private Button mButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mController=new BookListController(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        mGridView = (GridView)this.findViewById(R.id.gridView);
//        mButton = (Button)this.findViewById(R.id.test_btn);


        initGridViewData();
        initGridViewGUI();
//        mController.checkVersion();

//        try {
//            ZipUtils.unzip(AppConfig.ZIP_DIRECTORY + "/101011.zip", AppConfig.BOOK_DIRECTORY + "/");
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
    }

    private void initGridViewData() {
        List<Book> bookList=mController.queryLocalBooks();
        for(Book book : bookList){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("bookIcon", (AppConfig.BOOK_DIRECTORY + "/"+book.getBookNumber()+"/icon.png"));
            item.put("bookName", book.getBookName() );
            item.put("bookNumber", book.getBookNumber());
            item.put("chapterSize", book.getChapterSize());
            gridItems.add(item);
        }
    }


    /**
     * 初始化 grid View
     */
    private void initGridViewGUI() {
        adapter = new SimpleAdapter(this, gridItems, R.layout.grid_view_item, new String[]{"bookIcon","bookName"}, new int[]{R.id.ItemImage,R.id.ItemText});
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object o, String s) {
                if(view instanceof ImageView){
                    ImageView mImageView = (ImageView) view;
                    mImageView.setImageDrawable(Drawable.createFromPath(s));
                    return true;
                }else{
                    return false;
                }
            }
        });

        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setSelector(new ColorDrawable(Color.LTGRAY));
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bookNumber = (String) gridItems.get(position).get("bookNumber");
                chapterSize = gridItems.get(position).get("chapterSize").toString();
//                testReadFile(bookNumber);

                CacheUtils.clearCache();

                Intent intent = new Intent(BookListActivity.this, ChapterListActivity.class);
                intent.putExtra("bookNumber", bookNumber);
                intent.putExtra("chapterSize", chapterSize);
                BookListActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            }
        });
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                bookNumber = (String) gridItems.get(i).get("bookNumber");
                return true;
            }
        });
    }

}
