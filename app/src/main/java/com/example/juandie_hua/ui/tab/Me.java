package com.example.juandie_hua.ui.tab;

import java.io.File;
import java.lang.ref.WeakReference;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.juandie_hua.R;
import com.example.juandie_hua.app.App;
import com.example.juandie_hua.app.BaseFragment;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.app.HttpUrl;
import com.example.juandie_hua.calender.utils.ImageUtils;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.Fengmian;
import com.example.juandie_hua.mainactivity.Landing;
import com.example.juandie_hua.model.ResUser;
import com.example.juandie_hua.percenter.seting.wx_bdgh;
import com.example.juandie_hua.ui.me.OfficialAccountActivity;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.other_web1;
import com.example.juandie_hua.percenter.yiJian;
import com.example.juandie_hua.percenter.myorder.myOrder;
import com.example.juandie_hua.percenter.myscanddingdan.dingDanchaxun;
import com.example.juandie_hua.percenter.myscanddingdan.order_list;
import com.example.juandie_hua.ui.me.MyCouponActivity;
import com.example.juandie_hua.percenter.seting.seting;
import com.example.juandie_hua.utils.DecimalUtil;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.view.CustomDialog;
import com.jauker.widget.BadgeView;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;


/**
 * @author Administrator
 * @Date 2019-1-9 上午10:41:09
 */
public class Me extends BaseFragment implements View.OnClickListener {

    private View content;

    @ViewInject(R.id.ui_my_head)
    ImageView myHead;
    @ViewInject(R.id.ui_my_name)
    TextView myName;
    @ViewInject(R.id.ui_my_rank)
    TextView myRank;
    @ViewInject(R.id.ui_my_unpay_order)
    TextView unpayOrder;
    @ViewInject(R.id.ui_my_unsend_order)
    TextView unsendOrder;
    @ViewInject(R.id.ui_my_unprise_order)
    TextView unpriseOrder;
    @ViewInject(R.id.ui_my_all_order)
    TextView allOrder;

    @ViewInject(R.id.ui_my_vip_auth)
    TextView vipAuth;
    @ViewInject(R.id.ui_my_coupon)
    TextView myCoupon;
    @ViewInject(R.id.ui_my_anniversary)
    TextView myAnniversary;
    @ViewInject(R.id.ui_my_phone_service)
    TextView phoneService;

    public static MyHandler handler;

    int num1 = 0, num2 = 0, num3 = 0;
    BadgeView bg1, bg2, bg3;

    Landing landing;
    private CustomDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (content == null) {
            content = LayoutInflater.from(getContext()).inflate(R.layout.fragment_me, container, false);

            x.view().inject(this, content);
            handler = new MyHandler(Me.this);
            landing = new Landing(getActivity(), R.style.CustomDialog);

            bg1 = new BadgeView(getActivity());
            bg2 = new BadgeView(getActivity());
            bg3 = new BadgeView(getActivity());

            initView();
        }

        bg1.setBackgroundResource(R.drawable.nyuan);
        bg2.setBackgroundResource(R.drawable.nyuan);
        bg3.setBackgroundResource(R.drawable.nyuan);
        bg1.setTextSize(8);
        bg2.setTextSize(8);
        bg3.setTextSize(8);
        return content;
    }

    private void initView() {
        content.findViewById(R.id.ui_my_service).setOnClickListener(this);
        content.findViewById(R.id.ui_my_setting).setOnClickListener(this);
        content.findViewById(R.id.ui_my_vip).setOnClickListener(this);
        content.findViewById(R.id.ui_my_official_account).setOnClickListener(this);
        content.findViewById(R.id.ui_my_coupon_view).setOnClickListener(this);
        content.findViewById(R.id.ui_my_anniversary_view).setOnClickListener(this);
        content.findViewById(R.id.ui_my_order_search).setOnClickListener(this);
        content.findViewById(R.id.ui_my_vip_info).setOnClickListener(this);
        content.findViewById(R.id.ui_my_vip_auth_view).setOnClickListener(this);
        content.findViewById(R.id.ui_my_collection).setOnClickListener(this);
        content.findViewById(R.id.ui_my_online_service_view).setOnClickListener(this);
        content.findViewById(R.id.ui_my_phone_service_view).setOnClickListener(this);
        content.findViewById(R.id.ui_my_scan_history).setOnClickListener(this);
        content.findViewById(R.id.ui_my_suggestion).setOnClickListener(this);
        content.findViewById(R.id.ui_my_unpay_order_view).setOnClickListener(this);
        content.findViewById(R.id.ui_my_unsend_order_view).setOnClickListener(this);
        content.findViewById(R.id.ui_my_unprise_order_view).setOnClickListener(this);
        content.findViewById(R.id.ui_my_all_order_view).setOnClickListener(this);

        myHead.setOnClickListener(this);
        myName.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ui_my_setting://设置
                UiHelper.toActivity(getActivity(), seting.class);
                break;
            case R.id.ui_my_vip:
            case R.id.ui_my_vip_info:
            case R.id.ui_my_vip_auth_view://会员中心
                if (isLogin()) {
                    Intent i = new Intent();
                    i.putExtra("titl", "会员中心");
                    i.putExtra("url", "https://mnosu.juandie.com/user-rank.html?is_app=1&uid=" + App.getInstance().getUid());
                    i.setClass(getActivity(), other_web1.class);
                    UiHelper.toActivity(getActivity(), i);
                } else {
                    UiHelper.toLoginActivity(getActivity());
                }
                break;
            case R.id.ui_my_official_account://公众号
                UiHelper.toActivity(getActivity(), OfficialAccountActivity.class);
                break;
            case R.id.ui_my_coupon_view://我的优惠券
                if (!isLogin()) {
                    UiHelper.toLoginActivity(getActivity());
                } else {
                    if (myCoupon.getText().equals("点击领取")) {
                        String hbcs = (String) SharedPreferenceUtils.getPreference(getActivity(), Constant.hbcs, "S");
                        if (hbcs.contains(",")) {
                            String[] cs = hbcs.split(",");
                            get_hb(cs[0]);
                        }
                    } else {
                        Intent i = new Intent();
                        i.setClass(getActivity(), MyCouponActivity.class);
                        i.putExtra("type", "0");// 1代表可以点击使用优惠券
                        UiHelper.toActivity(getActivity(), i);
                    }
                }
                break;
            case R.id.ui_my_anniversary_view://纪念日
                if (!isLogin()) {
                    UiHelper.toLoginActivity(getActivity());
                } else {
                    Intent i = new Intent();
                    i.putExtra("titl", "生日/纪念日提醒");
                    i.putExtra("url", "https://mnosu.juandie.com/user_holiday.html?is_app=1&uid=" + App.getInstance().getUid());
                    i.setClass(getActivity(), other_web1.class);
                    UiHelper.toActivity(getActivity(), i);
                }
                break;
            case R.id.ui_my_order_search://订单查询
                UiHelper.toActivity(getActivity(), dingDanchaxun.class);
                break;
            case R.id.ui_my_collection://我的收藏
                if (!isLogin()) {
                    UiHelper.toLoginActivity(getActivity());
                } else {
                    UiHelper.toMySCActivity(getActivity(), "1");
                }
                break;
            case R.id.ui_my_service:
            case R.id.ui_my_online_service_view://在线客服
                UiHelper.toChatActivity(getActivity());
                break;
            case R.id.ui_my_phone_service_view://客服电话
                String pho = "4006089178";
                String phone = (String) SharedPreferenceUtils.getPreference(getActivity(), Constant.kfpho, "S");
                if (!TextUtils.isEmpty(phone)) {
                    pho = phone.replace("-", "");
                }
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + pho));
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                UiHelper.toActivity(getActivity(), intent1);
                break;
            case R.id.ui_my_scan_history://浏览记录
                UiHelper.toMySCActivity(getActivity(), "2");
                break;
            case R.id.ui_my_suggestion://投诉建议
                UiHelper.toActivity(getActivity(), yiJian.class);
                break;
            case R.id.ui_my_head:
            case R.id.ui_my_name:
                if (!isLogin()) {
                    UiHelper.toLoginActivity(getActivity());
                }
                break;
            case R.id.ui_my_unpay_order_view://待付款
                searchOrder("2");
                break;
            case R.id.ui_my_unsend_order_view://待收货
                searchOrder("3");
                break;
            case R.id.ui_my_unprise_order_view://待评价
                searchOrder("4");
                break;
            case R.id.ui_my_all_order_view://全部订单
                searchOrder("1");
                break;
        }
    }

    /**
     * 查询对应状态的订单
     *
     * @param type type
     */
    private void searchOrder(String type) {
        if (!isLogin()) {
            UiHelper.toLoginActivity(getActivity());
        } else {
            Intent i = new Intent();
            i.putExtra("type", type);
            i.setClass(getActivity(), myOrder.class);
            UiHelper.toActivity(getActivity(), i);
        }
    }

    /**
     * 领取红包
     *
     * @param field
     */
    private void get_hb(String field) {
        landing.show();
        String url = HttpUrl.index + "act=" + HttpUrl.receive_bonus + "&field=" + field;
//        String url = "https://app.juandie.com/api_mobile/index.php?act=receive_bonus&field="+ field;

        Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                try {
                    JSONObject response = new JSONObject(result);
                    if (response.getString("status").equals("1")) {
                        toast(response.getString("msg"));
                        Intent i = new Intent(getActivity(), MyCouponActivity.class);
                        i.putExtra("type", "0");
                        UiHelper.toActivity(getActivity(), i);
                    } else {
                        String jsb = response.getString("msg");
                        if (jsb.contains("登录")) {
                            toast(jsb);
                            SharedPreferenceUtils.setPreference(getActivity(), Constant.sye_dl, "1", "S");// 1表示首页领取红包登录后自动领取
                            UiHelper.toLoginActivity(getActivity());
                        } else if (jsb.contains("领取")) {
                            Intent i = new Intent(getActivity(), MyCouponActivity.class);
                            i.putExtra("type", "0");
                            UiHelper.toActivity(getActivity(), i);
                        } else {
                            showDialogtis(jsb);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        landing.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onFail(String result) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        landing.dismiss();
                    }
                }, 1000);
            }

            @Override
            public void onCancel(Callback.CancelledException cex) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        landing.dismiss();
                    }
                }, 1000);
            }
        });
    }


    /**
     * 获取用户信息
     */
    private void getUserInfo() {
        String url = HttpUrl.user + "act=" + HttpUrl.user_index;

        Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

            @Override
            public void onResponse(String result) {
                JSONObject jso1;
                try {
                    jso1 = new JSONObject(result);
                    if (jso1.getString("status").equals("1")) {

                        ResUser user = JSON.parseObject(jso1.getString("data"), ResUser.class);
                        ImageUtils.setCircleImage(getContext(), R.mipmap.login_in, myHead);

                        vipAuth.setText(getResources().getString(R.string.search_now));

                        /*优惠券 */
                        if (DecimalUtil.string2Int(user.getBonus_not_used_count()) > 0) {
                            myCoupon.setText(getString(R.string.has_some_coupon, user.getBonus_not_used_count()));
                        } else {
                            myCoupon.setText(getResources().getString(R.string.click_to_receive));
                        }

                        /*纪念日 */
                        if (DecimalUtil.string2Int(user.getHoliday_count()) > 0) {
                            myAnniversary.setText(getResources().getString(R.string.search_now));
                        } else {
                            myAnniversary.setText(getResources().getString(R.string.add_notice));
                        }

                        num1 = DecimalUtil.string2Int(user.getCount0());
                        num2 = DecimalUtil.string2Int(user.getCount1());
                        num3 = DecimalUtil.string2Int(user.getCount2());
                        handler.sendEmptyMessage(0x0021);
                        handler.sendEmptyMessage(0x0022);
                        handler.sendEmptyMessage(0x0023);

                        ResUser.UserInfo user_info = user.getUser_info();
                        String userName = user_info.getUser_name();
                        String userPhone = user_info.getMobile_phone();
                        myName.setText(userName);
                        myRank.setText(user.getUser_rank_info().getRank_text());

                        //判断用户的登录方式
                        if (TextUtils.equals(user_info.getIs_app_wechat_user(), "1")) {//微信登录
                            if (TextUtils.equals(user_info.getIs_wechat_binding_account(), "1")) {//已绑定手机号
                                setInfo("1", "wx", userName, userPhone);
                            } else {
                                setInfo("2", "wx", userName, "");
                            }
                        } else if (TextUtils.equals(user_info.getIs_third_user(), "1")) {//QQ，新浪登录
                            if (TextUtils.equals(user_info.getIs_binding_account(), "1")) {//已绑定手机号
                                setInfo("1", "dsf", userName, userPhone);
                            } else {
                                setInfo("2", "dsf", userName, "");
                            }
                        } else {// 手机号登录
                            setInfo("0", "", userName, "");
                        }
                    } else {
                        myName.setText("未登录");
                        myRank.setText("");
                        ImageUtils.setCircleImage(getContext(), R.mipmap.un_login_in, myHead);

                        vipAuth.setText("开通会员");
                        myAnniversary.setText("添加提醒");
                        myCoupon.setText("点击领取");

                        SharedPreferenceUtils.setPreference(getActivity(), Constant.uid, "", "S");

                        num1 = num2 = num3 = 0;
                        handler.sendEmptyMessage(0x0021);
                        handler.sendEmptyMessage(0x0022);
                        handler.sendEmptyMessage(0x0023);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
            }

            @Override
            public void onCancel(Callback.CancelledException cex) {
            }
        });
    }

    private void setInfo(String iswxbd, String typeqd, String username, String phone) {
        SharedPreferenceUtils.setPreference(getActivity(), Constant.iswxbd, iswxbd, "S");
        SharedPreferenceUtils.setPreference(getActivity(), Constant.typeqd, typeqd, "S");
        SharedPreferenceUtils.setPreference(getActivity(), Constant.username, username, "S");
        SharedPreferenceUtils.setPreference(getActivity(), Constant.phone, phone, "S");
    }

    public void showDialogtis(final String text) {

        dialog = new CustomDialog.Builder(getActivity())
                .setTitle(text)
                .setLineColor(R.color.red)
                .setPositiveButton("立即前往", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        if (text.contains("绑定")) {
                            UiHelper.toActivity(getActivity(), wx_bdgh.class);
                        }
//                        if (text.contains("微信")) {
//                            String iswxbd = (String) SharedPreferenceUtils.getPreference(getActivity(), Constant.iswxbd, "S");
//
//                            Intent i = new Intent();
//                            i.setClass(getActivity(), wx_bdgh.class);
//                            i.putExtra("type", iswxbd);
//                            UiHelper.toActivity(getActivity(), i);
//                            UiHelper.toActivity(getActivity(), seting.class);
//                        }
                    }
                })
                .setNegativeBtnShow(false)
                .create();
        dialog.show();
    }


    public static class MyHandler extends Handler {
        WeakReference<Me> referenceObj;

        public MyHandler(Me activity) {
            referenceObj = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final Me activity = referenceObj.get();
            switch (msg.what) {
//                case 0x001:// 登录与退出登录逻辑
//                    activity.myName.setVisibility(View.GONE);
//                    activity.myHead.setVisibility(View.GONE);
//                    break;
                case 0x0021:// 订单消息数量设置
                    if (activity.bg1 != null) {
                        activity.bg1.setTargetView(activity.unpayOrder);
                        activity.bg1.setBadgeCount(activity.num1);
                    }

                    break;
                case 0x0022:// 订单消息数量设置
                    if (activity.bg2 != null) {
                        activity.bg2.setTargetView(activity.unsendOrder);
                        activity.bg2.setBadgeCount(activity.num2);
                    }

                    break;
                case 0x0023:// 订单消息数量设置
                    if (activity.bg3 != null) {
                        activity.bg3.setTargetView(activity.unpriseOrder);
                        activity.bg3.setBadgeCount(activity.num3);
                    }
                    break;

                case 0x003:
                    if (activity != null) {
                        activity.getUserInfo();
                    }
                    break;

                case 0x007:
                    Intent i = new Intent(activity.getActivity(), order_list.class);
                    String zfddh = (String) SharedPreferenceUtils.getPreference(activity.getActivity(), Constant.zfddh, "S");
                    i.putExtra("sjh", "");
                    i.putExtra("ddh", zfddh);
                    activity.toast(zfddh);
                    UiHelper.toActivity(activity.getActivity(), i);
                    break;
                default:
                    break;
            }
        }
    }

//    @Override
//    public void detelorderstr(int pos) {
//        list.remove(pos);
//        ada.notifyDataSetChanged();
//
//        if (list.size() <= 0) {
//            orderstr = "";
//            ordertime = "";
//        } else {
//            orderstr = "";
//            ordertime = "";
//            for (int i = 0; i < list.size(); i++) {
//                orderstr += list.get(i).getOrdernumber() + ",";
//                ordertime += list.get(i).getTime() + ",";
//            }
//            if (orderstr.contains(",")) {
//                orderstr = orderstr.substring(0, orderstr.length() - 1);
//                ordertime = ordertime.substring(0, ordertime.length() - 1);
//            }
//        }
//        SharedPreferenceUtils.setPreference(getActivity(), Constant.perorderid, orderstr, "S");
//        SharedPreferenceUtils.setPreference(getActivity(), Constant.perordertime, ordertime, "S");
//    }
}
