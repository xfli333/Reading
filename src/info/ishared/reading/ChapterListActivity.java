package info.ishared.reading;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import info.ishared.reading.util.PageJumpUtils;
import info.ishared.reading.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
    private SimpleAdapter adapter;
    private String bookNumber;
    private String chapterSize;
    private List<Map<String,String>> menuData=new ArrayList<Map<String,String>>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.chapter_list);
        Bundle extras = getIntent().getExtras();

        bookNumber = extras.getString("bookNumber");
        chapterSize = extras.getString("chapterSize");
        mListView = (ListView) this.findViewById(R.id.chapter_list_view);
        initListViewData();
        initListViewGUI();
    }

    private void initListViewGUI() {
        adapter = new SimpleAdapter(this,menuData, R.layout.chapter_list_view_item, new String[]{"title"},new int[]{R.id.chapter_item_text});
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(ChapterListActivity.this, MenuListActivity.class);
                intent.putExtra("bookNumber", bookNumber);
                intent.putExtra("chapterNumber",  menuData.get(position).get("index"));
                ChapterListActivity.this.startActivity(intent);
            }
        });
    }

    private void initListViewData() {
        for(int i=0;i<Integer.valueOf(chapterSize);i++){
            Map<String,String> map=new HashMap<String, String>(2);
            map.put("index",i+"");
            map.put("title","第"+(i+1)+"部分");
            menuData.add(map);
        }
    }
}
