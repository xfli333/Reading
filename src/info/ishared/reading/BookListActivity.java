package info.ishared.reading;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

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
    private Handler mHandler;
    private String bookNumber;
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
            item.put("bookIcon", R.drawable.l2_book02_on);
            item.put("bookNumber", "number" + i);
            item.put("bookName", "name" + i);
            gridItems.add(item);
        }
    }


    /**
     * 初始化 grid View
     */
    private void initGridViewGUI() {
        adapter = new SimpleAdapter(this, gridItems, R.layout.grid_view_item, new String[]{"bookIcon", "bookNumber"}, new int[]{R.id.ItemImage, R.id.ItemText});

        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bookNumber = (String) gridItems.get(position).get("bookNumber");
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
