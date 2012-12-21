package info.ishared.reading;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mGridView = (GridView)this.findViewById(R.id.gridView);
        initGridViewData("");
        initGridViewGUI();

    }

    private void initGridViewData(String level) {

        for (int i = 0; i < 15; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            if(i%2==0) {
                item.put("bookIcon", R.drawable.icon_test);
                item.put("bookName", "凡人修仙转" );
            }else {
                item.put("bookIcon", R.drawable.icon_test2);
                item.put("bookName", "流氓高手");
            }
            item.put("bookNumber", "101010");
            item.put("chapterSize", "23");
            gridItems.add(item);
        }
    }


    /**
     * 初始化 grid View
     */
    private void initGridViewGUI() {
        adapter = new SimpleAdapter(this, gridItems, R.layout.grid_view_item, new String[]{"bookIcon","bookName"}, new int[]{R.id.ItemImage,R.id.ItemText});

        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setSelector(new ColorDrawable(Color.LTGRAY));
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bookNumber = (String) gridItems.get(position).get("bookNumber");
                chapterSize = (String) gridItems.get(position).get("chapterSize");
//                testReadFile(bookNumber);
                Intent intent = new Intent(BookListActivity.this, ChapterListActivity.class);
                intent.putExtra("bookNumber", bookNumber);
                intent.putExtra("chapterSize", chapterSize);
                BookListActivity.this.startActivity(intent);
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


    private void testReadFile(String name){
       Map<String ,String> menuMap=new HashMap<String, String>();
        try {
            List<String> lines= FileUtils.readLines(new File(AppConfig.BOOK_DIRECTORY + "/" + name + "/list.properties"));
            for (String line : lines){
                Log.d(AppConfig.TAG,line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
