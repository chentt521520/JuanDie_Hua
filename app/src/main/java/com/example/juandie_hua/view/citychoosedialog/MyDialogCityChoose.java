package com.example.juandie_hua.view.citychoosedialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.juandie_hua.R;
import com.example.juandie_hua.view.citychoosedialog.adapter.CityAdapter;
import com.example.juandie_hua.view.citychoosedialog.entity.CityInfo;

import org.xutils.common.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

//城市选中对话框
public class MyDialogCityChoose extends Dialog {

    private Context context;
    private DialogCityChooseOnClick dialogCityChooseOnClick;    //传值接口

    private String chooseProvince, chooseCity, chooseCounty;  //已选中的省市县
    private List<CityInfo> listProvince = new ArrayList<>();    //首页省数据
    private List<CityInfo> listCity = new ArrayList<>();    //首页省数据
    private List<CityInfo> listArea = new ArrayList<>();    //首页省数据
    private int chooseP = -1, chooseC = -1, chooseA = -1;    //选中的省市县下标值，默认是-1
    private int flag;
    private CityAdapter provinceAdapter;
    private CityAdapter cityAdapter;
    private CityAdapter areaAdapter;

    public MyDialogCityChoose(Context context, int flag, List<CityInfo> data, DialogCityChooseOnClick dialogCityChooseOnClick) {
        super(context);
        this.context = context;
        if (flag == 0) {
            this.listProvince = data;
        } else if (flag == 1) {
            this.listCity = data;
        } else if (flag == 2) {
            this.listArea = data;
        }
        this.flag = flag;
        this.dialogCityChooseOnClick = dialogCityChooseOnClick;
    }

    public void fresh(int flag, List<CityInfo> data) {
        this.flag = flag;
        if (flag == 0) {
            this.listProvince = data;
        } else if (flag == 1) {
            this.listCity = data;
        } else if (flag == 2) {
            this.listArea = data;
        }
        setData();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_citychoose);
        init();
    }

    private RelativeLayout province_tab, city_tab, county_tab; //省市县
    private TextView citychoose_province, citychoose_city, citychoose_county; //省市县
    private View citychoose_line_province, citychoose_line_city, citychoose_line_county;  //区域线
    private ListView citychoose_provincelist, citychoose_citylist, citychoose_countylist;   //省市县数据列表

    private void init() {

        //省
        citychoose_province = findViewById(R.id.item_province_text);
        citychoose_line_province = findViewById(R.id.item_province_line);
        //市
        citychoose_city = findViewById(R.id.item_city_text);
        citychoose_line_city = findViewById(R.id.item_city_line);
        //县
        citychoose_county = findViewById(R.id.item_area_text);
        citychoose_line_county = findViewById(R.id.item_area_line);
        //数据列表
        citychoose_provincelist = findViewById(R.id.citychoose_provincelist);
        citychoose_citylist = findViewById(R.id.citychoose_citylist);
        citychoose_countylist = findViewById(R.id.citychoose_countylist);

        //监听
        citychoose_provincelist.setOnItemClickListener(provinceItemClickListener);
        citychoose_citylist.setOnItemClickListener(provinceItemClickListener);
        citychoose_countylist.setOnItemClickListener(provinceItemClickListener);

        province_tab = findViewById(R.id.item_province_tab);
        province_tab.setOnClickListener(onClickListener);
        city_tab = findViewById(R.id.item_city_tab);
        city_tab.setOnClickListener(onClickListener);
        county_tab = findViewById(R.id.item_area_tab);
        county_tab.setOnClickListener(onClickListener);
        findViewById(R.id.item_dialog_close).setOnClickListener(onClickListener);

        provinceAdapter = new CityAdapter(context, listProvince);
        citychoose_provincelist.setAdapter(provinceAdapter);
        cityAdapter = new CityAdapter(context, listCity);
        citychoose_citylist.setAdapter(cityAdapter);
        areaAdapter = new CityAdapter(context, listArea);
        citychoose_countylist.setAdapter(areaAdapter);

        setData();
    }

    private void setData() {
        switch (flag) {
            case 0:
                provinceAdapter.setChoose(listProvince, chooseP);
                break;
            case 1:
                cityAdapter.setChoose(listCity, chooseC);
                break;
            case 2:
                areaAdapter.setChoose(listArea, chooseA);
                break;
        }
        setStatus();
    }

    private void setStatus() {
        //省
        citychoose_provincelist.setVisibility(flag == 0 ? View.VISIBLE : View.GONE);

        //市
        citychoose_citylist.setVisibility(flag == 1 ? View.VISIBLE : View.GONE);

        //县
        citychoose_countylist.setVisibility(flag == 2 ? View.VISIBLE : View.GONE);
    }


    //省监听
    private AdapterView.OnItemClickListener provinceItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (flag == 0) {
                chooseP = position;
                chooseProvince = listProvince.get(position).getRegion_name();
                citychoose_province.setText(chooseProvince);
                citychoose_line_province.setVisibility(View.VISIBLE);

                city_tab.setVisibility(View.VISIBLE);
                citychoose_city.setText("");
                citychoose_line_city.setVisibility(View.INVISIBLE);

                county_tab.setVisibility(View.INVISIBLE);

                dialogCityChooseOnClick.onItemSelect(1, listProvince.get(chooseP).getRegion_id(), "0");
            } else if (flag == 1) {
                chooseC = position;
                chooseCity = listCity.get(position).getRegion_name();
                citychoose_city.setText(chooseCity);
                citychoose_line_city.setVisibility(View.VISIBLE);

                county_tab.setVisibility(View.VISIBLE);
                citychoose_county.setText("");
                citychoose_line_county.setVisibility(View.INVISIBLE);

                dialogCityChooseOnClick.onItemSelect(2, listProvince.get(chooseP).getRegion_id(), listCity.get(chooseC).getRegion_id());
            } else if (flag == 2) {
                chooseA = position;
                chooseCounty = listArea.get(position).getRegion_name();
                citychoose_county.setText(chooseCounty);
                citychoose_line_county.setVisibility(View.VISIBLE);
                String ids = listProvince.get(chooseP).getRegion_id() + "," + listCity.get(chooseC).getRegion_id() + "," + listArea.get(chooseA).getRegion_id();
                String cities = chooseProvince + "," + chooseCity + "," + chooseCounty;
                dialogCityChooseOnClick.complete(ids, cities);
                dismiss();
            }
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_dialog_close:
                    dismiss();
                    return;
                case R.id.item_province_tab:
                    if (flag != 0) {
                        flag = 0;
                        chooseP = -1;
                        chooseC = -1;
                        chooseA = -1;
                        citychoose_province.setText("");
                        citychoose_line_province.setVisibility(View.INVISIBLE);

                        county_tab.setVisibility(View.INVISIBLE);
                        city_tab.setVisibility(View.INVISIBLE);

                        dialogCityChooseOnClick.onTabSelect(flag, "0", "0");
                    }

                    break;
                case R.id.item_city_tab:
                    if (flag != 1) {
                        flag = 1;
                        chooseC = -1;
                        chooseA = -1;
                        citychoose_city.setText("");
                        citychoose_line_city.setVisibility(View.INVISIBLE);

                        county_tab.setVisibility(View.INVISIBLE);
                        dialogCityChooseOnClick.onTabSelect(flag, listProvince.get(chooseP).getRegion_id(), "0");
                    }
                    break;
                case R.id.item_area_tab:
                    if (flag != 2) {
                        flag = 2;
                        chooseA = -1;
                        citychoose_county.setText("");
                        citychoose_line_county.setVisibility(View.INVISIBLE);

                        dialogCityChooseOnClick.onTabSelect(flag, listProvince.get(chooseP).getRegion_id(), listCity.get(chooseC).getRegion_id());
                    }
                    break;
            }
            setStatus();
        }
    };


    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = DensityUtil.getScreenWidth();
        params.height = -2;
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setGravity(Gravity.BOTTOM);
    }
}
