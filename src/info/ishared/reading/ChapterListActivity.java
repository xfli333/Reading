package info.ishared.reading;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-21
 * Time: PM4:25
 */
public class ChapterListActivity extends Activity {
    private ListView mListView;
    private ArrayAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.chapter_list);
        mListView = (ListView) this.findViewById(R.id.chapter_list_view);
        initListViewGUI();

    }

    private void initListViewGUI() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData());
        mListView.setAdapter(adapter);
    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        return data;
    }
}