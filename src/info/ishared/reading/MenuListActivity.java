package info.ishared.reading;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import info.ishared.reading.util.ToastUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-21
 * Time: PM5:32
 */
public class MenuListActivity extends Activity {
    private ListView mListView;
    private SimpleAdapter adapter;
    private String chapterNumber;
    private String bookNumber;

    private List<Map<String,String>> menuData=new ArrayList<Map<String,String>>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.menu_list);

        Bundle extras = getIntent().getExtras();

        bookNumber = extras.getString("bookNumber");
        chapterNumber = extras.getString("chapterNumber");
        mListView = (ListView) this.findViewById(R.id.menu_list_view);
        initListViewData();
        initListViewGUI();
    }

    private void initListViewGUI() {
        adapter =  new SimpleAdapter(this,menuData, R.layout.menu_list_view_item, new String[]{"displayTitle"},new int[]{R.id.menu_item_text});
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MenuListActivity.this, ShowContentActivity.class);
                intent.putExtra("bookNumber", bookNumber);
                intent.putExtra("fileName",  menuData.get(position).get("fileName"));
                MenuListActivity.this.startActivity(intent);
            }
        });
    }

    private void  initListViewData() {
        List<String> lines = null;
        try {
            lines = FileUtils.readLines(new File(AppConfig.BOOK_DIRECTORY + "/"+bookNumber+"/"+chapterNumber + "/menu.txt"));
            for(String line : lines ){
                Map<String,String> map=new HashMap<String, String>(2);
                map.put("fileName",line.split("=")[0]);
                map.put("displayTitle",line.split("=")[1]);
                menuData.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
