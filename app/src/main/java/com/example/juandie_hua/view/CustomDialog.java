package com.example.juandie_hua.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.juandie_hua.R;


/**
 * 自定义Dialog
 *
 * @author chentt
 */
public class CustomDialog extends Dialog implements DialogInterface {

    /**
     * 标题View
     */
    private TextView tvTitle;
    /**
     * 内容View
     */
    private TextView tvMsg;

    /**
     * 确定按钮
     */
    private TextView btnPositive;

    /**
     * 取消按钮
     */
    private TextView btnNegative;
    /**
     * 按钮中间的分割线
     */
    private View divider;
    /**
     * 内容与按钮之间的分割线
     */
    private View dividerLine;


//	/**
//	 * 内容容器
//	 */
//	private FrameLayout customContent;
//
//    /**
//     * 标题容器
//     */
//    private LinearLayout titleContent;

    /**
     * 标题文字
     */
    private String mTitle;

    /**
     * 内容文字
     */
    private String mMessage;

    /**
     * 确定按钮文字
     */
    private String positiveText;

    private int positiveTextColor;
    /**
     * 取消按钮文字
     */
    private String negativeText;

    private int negativeTextColor;
    private int dividerLineColor;
    /**
     * 取消按钮是否显示
     */
    private boolean isNegativeBtnShow = true;

    /**
     * 确定按钮是否显示
     */
    private boolean isPositiveBtnShow = true;

    /**
     * 自定义VIew
     */
    private View mView;

    /**
     * 上下文环境
     */
    private Context context;

    /**
     * 列表
     */
    private ListView listView;

    /**
     * 单选选择的序列号
     */
    private int mCheckItem;

    /**
     * 是否为单选
     */
    private boolean isSingleChoice;

    /**
     * ListView适配器
     */
    private ListAdapter mAdapter;

    /**
     * 按钮部分
     */
    private LinearLayout btnContent;

    /**
     * 列表部分
     */
    private LinearLayout listContent;

    private View.OnClickListener onDefaultClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            cancel();
        }

    };
    private View.OnClickListener onPositiveListener = onDefaultClickListener;
    private View.OnClickListener onNegativeListener = onDefaultClickListener;

    private OnClickListener onItemClickListener = new OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int checkItem) {
            dialog.dismiss();
            cancel();
        }
    };

    public OnClickListener mOnClickListener = onItemClickListener;

    public CustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    public CustomDialog(Context context) {
        this(context, R.style.CustomDialog);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_customer_form);
        tvTitle = findViewById(R.id.dialog_title);
        tvMsg = findViewById(R.id.dialog_massage);
        divider = findViewById(R.id.line_divider);
        btnPositive = findViewById(R.id.dialog_ok);
        btnNegative = findViewById(R.id.dialog_cancel);
        dividerLine = findViewById(R.id.divider_line);
//		listView = (ListView) findViewById(R.id.custom_dialog_listView);
//		btnContent = (LinearLayout) findViewById(R.id.custom_dialog_btns);
//		listContent = (LinearLayout) findViewById(R.id.custom_dialog_list);
    }

    /**
     * 调用完Builder类的create()方法后显示该对话框的方法
     */
    @Override
    public void show() {
        super.show();
        show(this);
    }

    private void show(final CustomDialog mDialog) {
        if (dividerLineColor != 0) {
            mDialog.dividerLine.setBackgroundColor(context.getResources().getColor(dividerLineColor));
        }
        if (!TextUtils.isEmpty(mTitle)) {
            mDialog.tvTitle.setText(mDialog.mTitle);
            mDialog.tvTitle.setVisibility(View.VISIBLE);
//			mDialog.titleContent.setVisibility(View.VISIBLE);
        } else {
            mDialog.tvTitle.setVisibility(View.GONE);
//			mDialog.titleContent.setVisibility(View.GONE);
        }
//		if (mDialog.mView != null) {
//			mDialog.customContent.addView(mDialog.mView);
//			mDialog.tvMsg.setVisibility(View.GONE);
//		} else {
//
        if (!TextUtils.isEmpty(mDialog.mMessage)) {
            mDialog.tvMsg.setText(mDialog.mMessage);
            mDialog.tvMsg.setVisibility(View.VISIBLE);
        }
//		}
        //设置取消按钮消失
        if (!mDialog.isNegativeBtnShow) {
            mDialog.btnNegative.setVisibility(View.GONE);
            mDialog.divider.setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mDialog.btnPositive.getLayoutParams();
            layoutParams.setMargins(150, layoutParams.topMargin, 150, layoutParams.bottomMargin);
            mDialog.btnPositive.setLayoutParams(layoutParams);
        } else {
            mDialog.btnNegative.setOnClickListener(mDialog.onNegativeListener);
            if (!TextUtils.isEmpty(mDialog.negativeText)) {
                mDialog.btnNegative.setText(mDialog.negativeText);
            }
            if (negativeTextColor != 0) {
                mDialog.btnNegative.setTextColor(context.getResources().getColor(negativeTextColor));
            }
        }
        //设置确认按钮消失
        if (!mDialog.isPositiveBtnShow) {
            mDialog.btnPositive.setVisibility(View.GONE);
            mDialog.divider.setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mDialog.btnNegative.getLayoutParams();
            layoutParams.setMargins(150, layoutParams.topMargin, 150, layoutParams.bottomMargin);
            mDialog.btnNegative.setLayoutParams(layoutParams);
        } else {
            mDialog.btnPositive.setOnClickListener(mDialog.onPositiveListener);
            if (!TextUtils.isEmpty(mDialog.positiveText)) {
                mDialog.btnPositive.setText(mDialog.positiveText);
            }
            if (positiveTextColor != 0) {
                mDialog.btnPositive.setTextColor(context.getResources().getColor(positiveTextColor));
            }
        }

        mDialog.btnPositive.setOnClickListener(mDialog.onPositiveListener);
        if (!TextUtils.isEmpty(mDialog.positiveText)) {
            mDialog.btnPositive.setText(mDialog.positiveText);
        }

        if (isSingleChoice) {
            listContent.setVisibility(View.VISIBLE);
            listView.setAdapter(mDialog.mAdapter);
            btnContent.setVisibility(View.GONE);

            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    mOnClickListener.onClick(mDialog, position);
                    // mDialog.dismiss();
                }
            });
        }

//        Window dialogWindow = getWindow();
//        LayoutParams lp = dialogWindow.getAttributes();
//        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
//        lp.width = (int) (d.widthPixels * 0.9); // 高度设置为屏幕的0.6
//        lp.flags = LayoutParams.FLAG_DIM_BEHIND;
//        lp.alpha = 0.5f;
//         lp.dimAmount=0.5f;
//        dialogWindow.setAttributes(lp);
    }

    public static class Builder {

        private CustomDialog mDialog;

        public Builder(Context context) {
            mDialog = new CustomDialog(context);
        }

        /**
         * 设置对话框标题
         */
        public Builder setTitle(String title) {
            mDialog.mTitle = title;
            return this;
        }

        /**
         * 设置对话框文本内容,如果调用了setView()方法，该项失效
         */
        public Builder setMessage(String msg) {
            mDialog.mMessage = msg;
            return this;
        }

        public Builder setLineColor(int lineColor) {
            mDialog.dividerLineColor = lineColor;
            return this;
        }

        /**
         * 设置确认按钮的回调
         */
        public Builder setPositiveButton(View.OnClickListener onClickListener) {
            mDialog.onPositiveListener = onClickListener;
            return this;
        }

        /**
         * 设置确认按钮的回调
         */
        public Builder setPositiveButton(String btnText, View.OnClickListener onClickListener) {
            mDialog.positiveText = btnText;
            mDialog.onPositiveListener = onClickListener;
            return this;
        }

        /**
         * 设置确认按钮的回调
         */
        public Builder setPositiveButton(String btnText, int color, View.OnClickListener onClickListener) {
            mDialog.positiveText = btnText;
            mDialog.positiveTextColor = color;
            mDialog.onPositiveListener = onClickListener;
            return this;
        }

        /**
         * 设置取消按钮的回调
         */
        public Builder setNegativeButton(View.OnClickListener onClickListener) {
            mDialog.onNegativeListener = onClickListener;
            return this;
        }

        /**
         * 设置取消按钮的回调
         */
        public Builder setNegativeButton(String btnText, View.OnClickListener onClickListener) {
            mDialog.negativeText = btnText;
            mDialog.onNegativeListener = onClickListener;
            return this;
        }

        /**
         * 设置取消按钮的回调
         */
        public Builder setNegativeButton(String btnText, int color, View.OnClickListener onClickListener) {
            mDialog.negativeText = btnText;
            mDialog.negativeTextColor = color;
            mDialog.onNegativeListener = onClickListener;
            return this;
        }

        /**
         * 设置是否显示取消按钮，默认显示
         */
        public Builder setNegativeBtnShow(boolean isNegativeBtnShow) {
            mDialog.isNegativeBtnShow = isNegativeBtnShow;
            return this;
        }


        /**
         * 设置是否显示确认按钮，默认显示
         */
        public Builder setPositiveBtnShow(boolean isPositiveBtnShow) {
            mDialog.isPositiveBtnShow = isPositiveBtnShow;
            return this;
        }

        /**
         * 设置自定义内容View
         */
        public Builder setView(View view) {
            mDialog.mView = view;
            return this;
        }

        /**
         * 设置该对话框能否被Cancel掉，默认可以
         */
        public Builder setCancelable(boolean cancelable) {
            mDialog.setCancelable(cancelable);
            return this;
        }

        /**
         * 设置对话框被cancel对应的回调接口，cancel()方法被调用时才会回调该接口
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            mDialog.setOnCancelListener(onCancelListener);
            return this;
        }

        /**
         * 设置对话框消失对应的回调接口，一切对话框消失都会回调该接口
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            mDialog.setOnDismissListener(onDismissListener);
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, OnClickListener onClickListener) {
            mDialog.mAdapter = adapter;
            mDialog.mOnClickListener = onClickListener;
            mDialog.mCheckItem = checkedItem;
            mDialog.isSingleChoice = true;
            return this;
        }

        /**
         * ͨ 通过Builder类设置完属性后构造对话框的方法
         */
        public CustomDialog create() {
            // 点击返回按钮，Dialog不消失
            // mDialog.setCancelable(false);
//            mDialog.setCanceledOnTouchOutside(false);
            return mDialog;
        }
    }

}
