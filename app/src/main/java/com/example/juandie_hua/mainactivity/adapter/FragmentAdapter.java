package com.example.juandie_hua.mainactivity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

//fragment适配器
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        if(list!=null)
            return list.size();
        return 0;
    }
}