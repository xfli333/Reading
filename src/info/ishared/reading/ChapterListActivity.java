package info.ishared.reading;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import info.ishared.reading.util.PageJumpUtils;

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
    private List<String> menuData=new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.chapter_list);
        mListView = (ListView) this.findViewById(R.id.chapter_list_view);
        initListViewGUI();

    }

    private void initListViewGUI() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData());
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(AppConfig.TAG,i+"");
                PageJumpUtils.jump(ChapterListActivity.this,MenuListActivity.class);
            }
        });
    }

    private List<String> getData() {
        for(int i=0;i<22;i++){
            menuData.add("第"+i+"部分");
        }
        return menuData;
    }
}