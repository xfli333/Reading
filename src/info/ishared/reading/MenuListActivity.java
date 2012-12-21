package info.ishared.reading;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-21
 * Time: PM5:32
 */
public class MenuListActivity extends Activity {
    private ListView mListView;
    private ArrayAdapter adapter;
    private String chapterNumber;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.menu_list);
        mListView = (ListView) this.findViewById(R.id.menu_list_view);
        initListViewGUI();

    }

    private void initListViewGUI() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData());
        mListView.setAdapter(adapter);
        mListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private List<String> getData() {
        List<String> data = null;
        try {
            data = FileUtils.readLines(new File(AppConfig.BOOK_DIRECTORY + "/101010/" + "0/menu.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}