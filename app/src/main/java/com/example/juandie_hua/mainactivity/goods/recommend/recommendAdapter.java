/**
 * @Author ldlin
 * @Time 2019-1-21 下午2:34:46
 * @Description
 */

package com.example.juandie_hua.mainactivity.goods.recommend;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mainactivity.adapter.GoodRecommondAdapter;
import com.example.juandie_hua.mainactivity.adapter.ShopCartRecommondAdapter;
import com.example.juandie_hua.mainactivity.goods.MyGridView;

/**
 * @Author ldlin
 * @Time 2019-1-21 下午2:34:46
 * @Description
 */

public class recommendAdapter extends BaseAdapter {

    List<recommendData> list;
    Context context;
    gotobuy buy;

    public recommendAdapter(List<recommendData> list, Context context) {
        this.context = context;
        this.list = list;

    }

    public void setbuyinterface(gotobuy buy) {
        this.buy = buy;
    }

    /*
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list == null ? 0 : list.size();
    }

    /*
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    /*
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    /*
     * @see android.widget.Adapter#getView(int, android.view.View,
     * android.view.ViewGroup)
     */
    @Override
    public View getView(final int position, View View, ViewGroup parent) {
        addview add;
        if (View == null) {
            add = new addview();
            View = LayoutInflater.from(context).inflate(R.layout.recommendadapter, parent, false);
            add.te_allprice = (TextView) View.findViewById(R.id.te_allprice);
            add.tegotobuy = (TextView) View.findViewById(R.id.tegotobuy);
            add.sales = (TextView) View.findViewById(R.id.tebuynumber);
            add.recyclerView = View.findViewById(R.id.ui_good_recommend_list);

            View.setTag(add);
        } else
            add = (addview) View.getTag();

        recommendData data = list.get(position);
        List<recommendDatatwo> list1 = new ArrayList<>();

        if (data.getJsaArray() != null) {
            try {
                for (int i = 0; i < data.getJsaArray().length(); i++) {
                    JSONObject jso = data.getJsaArray().getJSONObject(i);
                    list1.add(new recommendDatatwo(jso.getString("id"), jso
                            .getString("thumb"), jso.getString("name"), jso
                            .getString("shop_price")));
                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        LinearLayoutManager layoutmanager = new LinearLayoutManager(context);
        layoutmanager.setOrientation(0);
        //设置RecyclerView 布局
        add.recyclerView.setLayoutManager(layoutmanager);

        GoodRecommondAdapter adapter = new GoodRecommondAdapter(context, list1);
        add.recyclerView.setAdapter(adapter);

//        String price = "组合价" + "<font color = \"#333333\"><big>" + "¥" + data.getAllPrice() + "<big></font>";
        String price = "组合价" + "<font color = \"#333333\"><big>" + data.getAllPrice() + "<big></font>";
        add.te_allprice.setText(Html.fromHtml(price));
        add.sales.setText(data.getBuyer() + "已付款");

        add.tegotobuy.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (buy != null) {
                    buy.gotobuy(position);
                }
            }
        });

        return View;
    }

    public class addview {
        RecyclerView recyclerView;
        TextView te_allprice, tegotobuy, sales;
    }

    public interface gotobuy {
        void gotobuy(int pos);
    }

}
