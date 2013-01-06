package info.ishared.reading;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import info.ishared.reading.asyn.LoadNetworkImageTask;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 13-1-5
 * Time: PM5:07
 */
public class DownloadBookActivity extends Activity {

    private ListView mGridView;
    private List<Map<String, Object>> gridItems = new ArrayList<Map<String, Object>>();
    private Handler mHandler;
    private SimpleAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.download_book_list);

        initGridViewData();
        initGridViewGUI();

    }

    private void initGridViewData() {


        Map<String, Object> item = new HashMap<String, Object>();
//        item.put("ItemImage", Drawable.createFromPath("http://www.exploremetro.com/blog/wp-content/uploads/2012/02/icon3.png"));
        item.put("ItemImage", R.drawable.ic_launcher);

        item.put("ItemText", "充值缴费");
        item.put("ItemText2", "给自己充值，也可为他人缴费");
        item.put("itemId", 0);
        item.put("ItemImageUrl", "http://www.exploremetro.com/blog/wp-content/uploads/2012/02/icon3.png");
        gridItems.add(item);

        item = new HashMap<String, Object>();
        item.put("ItemImage", R.drawable.ic_launcher);
        item.put("ItemText", "当月话费");
        item.put("ItemText2", "当月话费明细查询");
        item.put("itemId", 1);
        item.put("ItemImageUrl", "http://www.exploremetro.com/blog/wp-content/uploads/2012/02/icon3.png");
        gridItems.add(item);

        item = new HashMap<String, Object>();
        item.put("ItemImage", R.drawable.ic_launcher);
        item.put("ItemText", "套餐余量");
        item.put("ItemText2", "当前剩余流量/购买流量包");
        item.put("itemId", 2);
        item.put("ItemImageUrl", "http://www.exploremetro.com/blog/wp-content/uploads/2012/02/icon3.png");
        gridItems.add(item);

        item = new HashMap<String, Object>();
        item.put("ItemImage", R.drawable.ic_launcher);
        item.put("ItemText", "历史账单");
        item.put("ItemText2", "历史账单明细查询");
        item.put("itemId", 3);
        item.put("ItemImageUrl", "http://www.exploremetro.com/blog/wp-content/uploads/2012/02/icon3.png");
        gridItems.add(item);

        item = new HashMap<String, Object>();
        item.put("ItemImage", R.drawable.ic_launcher);
        item.put("ItemText", "可用余额");
        item.put("ItemText2", "预付款余额查询");
        item.put("itemId", 4);
        item.put("ItemImageUrl", "http://www.exploremetro.com/blog/wp-content/uploads/2012/02/icon3.png");
        gridItems.add(item);

        item = new HashMap<String, Object>();
        item.put("ItemImage", R.drawable.ic_launcher);
        item.put("ItemText", "缴费记录查询");
        item.put("ItemText2", "可查询过去6个月内的充值记录");
        item.put("itemId", 5);
        item.put("ItemImageUrl", "http://www.exploremetro.com/blog/wp-content/uploads/2012/02/icon3.png");
        gridItems.add(item);

        item = new HashMap<String, Object>();
        item.put("ItemImage", R.drawable.ic_launcher);
        item.put("ItemText", "修改密码");
        item.put("ItemText2", "可修改登录密码");
        item.put("itemId", 6);
        item.put("ItemImageUrl", "http://www.exploremetro.com/blog/wp-content/uploads/2012/02/icon3.png");
        gridItems.add(item);


        item = new HashMap<String, Object>();
        item.put("ItemImage", R.drawable.ic_launcher);
        item.put("ItemText", "绑定银行卡");
        item.put("ItemText2", "绑定银行卡说明");
        item.put("itemId", 8);
        item.put("ItemImageUrl", "http://www.exploremetro.com/blog/wp-content/uploads/2012/02/icon3.png");
        gridItems.add(item);

        item = new HashMap<String, Object>();
        item.put("ItemImage", R.drawable.ic_launcher);
        item.put("ItemText", "解除绑定");
        item.put("ItemText2", "解除银行卡说明");
        item.put("itemId", 9);
        item.put("ItemImageUrl", "http://www.exploremetro.com/blog/wp-content/uploads/2012/02/icon3.png");
        gridItems.add(item);


    }

    /**
     * 初始化 grid View
     */
    private void initGridViewGUI() {
        adapter = new SimpleAdapter(this, gridItems, R.layout.download_list_item, new String[]{"ItemImage", "ItemText", "ItemText2"}, new int[]{R.id.item_image, R.id.item_text, R.id.item_text2}){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.d(AppConfig.TAG+"-------","uuuuu"+convertView.toString());
                if(convertView instanceof ImageView){
                    Log.d(AppConfig.TAG+"-------","uuuuu");
                    new LoadNetworkImageTask((ImageView)convertView,adapter).execute(gridItems.get(position).get("ItemImageUrl").toString());
                    return convertView;
                }else{
                    return super.getView(position, convertView, parent);
                }
            }
        };
//        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
//
//            @Override
//            public boolean setViewValue(View view, Object o, String s) {
//
//                if(view instanceof ImageView){
//                    Log.d(AppConfig.TAG+"dddd",o instanceof Map?"map======":"==========");
////                    new LoadNetworkImageTask((ImageView)view).execute(gridItems.get((Integer)o).get("ItemImageUrl").toString());
//                    return false;
//                }
//                return false;  //To change body of implemented methods use File | Settings | File Templates.
//            }
//        });
        mGridView = (ListView) findViewById(R.id.download_book);
        mGridView.setAdapter(adapter);
//        mGridView.setOnItemClickListener(new GridListViewClickedListener());
    }
}