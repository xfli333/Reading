package info.ishared.reading;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import info.ishared.reading.bean.Book;
import info.ishared.reading.cache.SimplyCache;
import info.ishared.reading.db.BookOperator;
import info.ishared.reading.util.CacheUtils;
import info.ishared.reading.util.PageJumpUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-19
 * Time: PM5:00
 */
public class BookListActivity extends Activity {
    private GridView mGridView;
    private List<Map<String, Object>> gridItems = new ArrayList<Map<String, Object>>();
    private String bookNumber;
    private String chapterSize;
    private SimpleAdapter adapter;
    private BookOperator bookOperator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookOperator=new BookOperator(this);
        setContentView(R.layout.main);
        mGridView = (GridView)this.findViewById(R.id.gridView);
        initGridViewData();
        initGridViewGUI();

    }

    private void initGridViewData() {
        List<Book> bookList=bookOperator.listAllBooks();
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
