package com.example.juandie_hua.helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mycar.orderpay.SendDateSelect;
import com.example.juandie_hua.mycar.orderpay.beizu;
import com.example.juandie_hua.mycar.orderpay.heka;
import com.example.juandie_hua.mycar.orderpay.youhuiquan;
import com.example.juandie_hua.percenter.seting.wx_bdgh;
import com.example.juandie_hua.ui.MainActivity;
import com.example.juandie_hua.ui.good.GoodListAty;
import com.example.juandie_hua.ui.good.GoodDetailsAty;
import com.example.juandie_hua.model.UserAddress;
import com.example.juandie_hua.mainactivity.other_web;
import com.example.juandie_hua.ui.login.Loginphowx;
import com.example.juandie_hua.ui.me.MySC;
import com.example.juandie_hua.ui.order.AddressManager;
import com.example.juandie_hua.ui.order.CreateOrder;

public class UiHelper {

    public static final String ZHUJI = "zhuji";
    public static final String KFPHO = "kfpho";

    //特定跳转标记 通常用作指定跳转
    public static final int intentActivityAssign = 9999;
    public static final int fromGoodDetail = 00001;

    public static final int chooseAddress = 00002;
    public static final int chooseSenfTime = 00003;
    public static final int leaveMessage = 00004;
    public static final int remark = 00005;
    public static final int coupon = 00006;
    public static final int serviceNumber = 00007;

    public static final String packShopCat = "com.example.juandie_hua.Ui.ShopCatActivity";

    public static void toActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void toActivity(Activity activity, Class cla) {
        activity.startActivity(new Intent(activity, cla));
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void toLoginActivity(Activity activity) {
        toActivity(activity, Loginphowx.class);
    }

    /**
     * 商品详情界面
     *
     * @param activity activity
     * @param goodId   商品Id
     */
    public static void toGoodDetailActivity(Activity activity, String goodId) {
        Intent intent = new Intent(activity, GoodDetailsAty.class);
        intent.putExtra("goods_id", goodId);
        UiHelper.toActivity(activity, intent);
    }

    /**
     * 商品列表界面
     *
     * @param activity    activity
     * @param cid         分类Id
     * @param keyWord     关键字
     * @param filter_attr 筛选条件
     * @param order       顺序
     * @param by
     * @param positionget
     */
    public static void toGoodListActivity(Activity activity, String cid, String keyWord, String filter_attr, String order, String by, String positionget) {
        Intent i = new Intent(activity, GoodListAty.class);
        Bundle bundle = new Bundle();
        bundle.putString("cid", cid);
        bundle.putString("keywords", keyWord);
        bundle.putString("filter_attr", filter_attr);
        bundle.putString("order", order);
        bundle.putString("by", by);
        bundle.putString("positionget", positionget);
        i.putExtras(bundle);
        UiHelper.toActivity(activity, i);
    }

    public static void toWebActivity(Activity activity, String title, String url) {
        Intent intent = new Intent(activity, other_web.class);
        intent.putExtra("titl", title);
        intent.putExtra("url", url);
        UiHelper.toActivity(activity, intent);
    }

    public static void toAddressActivity(Activity activity, UserAddress address) {
        Intent intent = new Intent(activity, AddressManager.class);
        intent.putExtra("address", address);
        activity.startActivityForResult(intent, chooseAddress);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void toMainActivity(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
    }

    /**
     * 收藏界面
     *
     * @param activity activity
     * @param type     type=1代表我的收藏，2代表浏览记录
     */
    public static void toMySCActivity(Activity activity, String type) {
        Intent i = new Intent(activity, MySC.class);
        i.putExtra("type", type);
        UiHelper.toActivity(activity, i);
    }


    public static void toSendTimeSelect(Activity activity) {
        Intent intent = new Intent(activity, SendDateSelect.class);
        activity.startActivityForResult(intent, chooseSenfTime);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void toCouponActivity(Activity activity) {
        Intent ix = new Intent(activity, youhuiquan.class);
//        Bundle bundlex = new Bundle();
//        bundlex.putStringArrayList("yhq", list_yhqstr);
//        ix.putExtras(bundlex);
        activity.startActivity(ix);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void toMessageActivity(Activity activity) {
        Intent ix_hk = new Intent(activity, heka.class);
//        ix_hk.putExtra("hk", );
        activity.startActivity(ix_hk);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void toRamarkActivity(Activity activity) {
        Intent intent = new Intent(activity, beizu.class);
//    ix_bz.putExtra("bz", te_bzxx.getText().toString());
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void finish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
