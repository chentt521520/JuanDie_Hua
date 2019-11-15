package com.example.juandie_hua.mycar.contact;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mycar.orderpay.addshdz;
import com.example.juandie_hua.utils.StatusBarUtils;

public class txl_choose extends Activity {
	@ViewInject(R.id.txl_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.txl_imreturn)
	ImageView im_return;

	private static final String[] PHONES_PROJECTION = new String[] {
			Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID, Phone.CONTACT_ID };
	private static final int PHONES_DISPLAY_NAME = 0;

	private static final int PHONES_NUMBER = 1;
	private static final int PHONES_PHOTO_ID = 2;
	private static final int PHONES_CONTACT_ID = 3;
	MyHandler handler;

	private ListView sortListView;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;

	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;

	private PinyinComparator pinyinComparator;

	LinearLayout lin_input;
	ImageView image_search;

	ImageView imano;
	View vno;
	TextView teno1, teno2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.txl_choose);
		StatusBarUtils.with(this).setBarColor(R.color.white_fff);

		x.view().inject(this);
		handler = new MyHandler(this);
		initViews();
		setviewhw();
		getPhoneContacts();
		setviewlisten();
	}

	private void setviewlisten() {
		im_return.setOnClickListener(onc);

	}

	OnClickListener onc = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.txl_imreturn:
				finish();
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
				break;

			default:
				break;
			}
		}
	};

	private void getPhoneContacts() {
		ContentResolver resolver = getContentResolver();
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<Bitmap> list3 = new ArrayList<Bitmap>();
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,
				PHONES_PROJECTION, null, null, null);
		List<SortModel> mSortList = new ArrayList<SortModel>();
		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {
				String phoneNumber = phoneCursor.getString(PHONES_NUMBER);
				if (TextUtils.isEmpty(phoneNumber))
					continue;

				String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME);

				Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID);
				Long imgid = phoneCursor.getLong(PHONES_PHOTO_ID);

				Bitmap contactPhoto = null;

				if (imgid > 0) {
					Uri uri = ContentUris.withAppendedId(
							ContactsContract.Contacts.CONTENT_URI, contactid);
					InputStream input = ContactsContract.Contacts
							.openContactPhotoInputStream(resolver, uri);
					contactPhoto = BitmapFactory.decodeStream(input);
				} else {
					contactPhoto = BitmapFactory.decodeResource(getResources(),
							R.drawable.ic_launcher);
				}

				list1.add(contactName);
				list2.add(phoneNumber);
				list3.add(contactPhoto);

				mSortList.add(new SortModel(contactName, "1", contactPhoto,
						phoneNumber, 0));

			}
			phoneCursor.close();
			SourceDateList.addAll(filledData(mSortList));
			Collections.sort(SourceDateList, pinyinComparator);
			adapter.notifyDataSetChanged();

			if (list1.size() == 0) {
				handler.sendEmptyMessage(0x02);
			} else {
				lin_input.setVisibility(View.VISIBLE);
				imano.setVisibility(View.GONE);
				vno.setVisibility(View.GONE);
				teno1.setVisibility(View.GONE);
				teno2.setVisibility(View.GONE);
			}

		} else {
			Toast.makeText(txl_choose.this, "请手动开启通讯录权限", Toast.LENGTH_SHORT)
					.show();
		}
	}

	private void initViews() {
		lin_input = (LinearLayout) findViewById(R.id.consec_lin);
		image_search = (ImageView) findViewById(R.id.comper_imsea);

		imano = (ImageView) findViewById(R.id.comper_imnodata);
		vno = findViewById(R.id.second_viewv);
		teno1 = (TextView) findViewById(R.id.second_teno);
		teno2 = (TextView) findViewById(R.id.second_teno1);

		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		sortListView
				.setSelector(new ColorDrawable(Color.parseColor("#ffffff")));
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Intent i = new Intent();
				// i.setClass(txl_choose.this, Comment_detals.class);
				// i.putExtra("phone", ((SortModel) adapter.getItem(position))
				// .getPhonenum().replace("-", "") + "");
				// i.putExtra("name",
				// ((SortModel) adapter.getItem(position)).getName() + "");
				// startActivity(i);
				// .getPhonenum());
				//
				// overridePendingTransition(R.anim.push_left_in,
				// R.anim.push_left_out);

				Message msg = Message.obtain();
				msg.what = 0x03;
				Bundle bundle = new Bundle();
				bundle.putString("name",
						((SortModel) adapter.getItem(position)).getName());
				bundle.putString("pho",
						((SortModel) adapter.getItem(position)).getPhonenum());
				msg.setData(bundle);
				addshdz.handler.sendMessage(msg);
				finish();
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);

			}
		});

		List<SortModel> mSortList = new ArrayList<SortModel>();
		SourceDateList = filledData(mSortList);

		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(txl_choose.this, SourceDateList);
		sortListView.setAdapter(adapter);

		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	private List<SortModel> filledData(List<SortModel> date) {
		List<SortModel> mSortList = new ArrayList<SortModel>();

		for (int i = 0; i < date.size(); i++) {
			SortModel sortModel = new SortModel(date.get(i).getName(), date
					.get(i).getSortLetters(), date.get(i).getBitmap(), date
					.get(i).getPhonenum(), 0);
			sortModel.setName(date.get(i).getName());
			String pinyin = characterParser.getSelling(date.get(i).getName());
			String sortString = pinyin.substring(0, 1).toUpperCase();

			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;
	}

	private void filterData(String filterStr) {
		List<SortModel> filterDateList = new ArrayList<SortModel>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (SortModel sortModel : SourceDateList) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

	private void setviewhw() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0),
				0);

		setviewhw_re(lin_input, w - (int) (w * 49 / 450.0),
				(int) (w * 55 / 450.0), (int) (w * 49 / 450.0), 0, 0, 0);
		setviewhw_lin(image_search, (int) (w * 20 / 450.0),
				(int) (w * 55 / 450.0), (int) (w * 20 / 450.0), 0, 0, 0);
		setviewhw_lin(mClearEditText, (int) (w * 380 / 450.0),
				(int) (w * 55 / 450.0), (int) (w * 20 / 750), 0, 0, 0);

		setviewhw_lin(imano, (int) (w * 219 / 750), (int) (w * 189 / 750),
				(int) (w * 266 / 750), (int) (w * 384 / 750), 0,
				(int) (w * 60 / 750));
		setviewhw_lin(vno, (int) (w * 16 / 750), (int) (w * 4 / 750),
				(int) (w * 367 / 750), (int) (w * 20 / 750), 0,
				(int) (w * 20 / 750));

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
			overridePendingTransition(R.anim.push_right_out,
					R.anim.push_right_in);

			return false;
		}
		return false;
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

	private void setviewhw_re(View v, int width, int height, int left, int top,
			int right, int bottom) {
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width,
				height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

	public static class MyHandler extends Handler {
		WeakReference<txl_choose> referenceObj;

		public MyHandler(txl_choose activity) {
			referenceObj = new WeakReference<txl_choose>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			txl_choose activity = referenceObj.get();
			switch (msg.what) {
			case 0x02:
				activity.lin_input.setVisibility(View.GONE);
				break;

			default:
				break;
			}
		}
	}

}
