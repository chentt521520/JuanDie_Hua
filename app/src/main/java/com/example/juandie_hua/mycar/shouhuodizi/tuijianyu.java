package com.example.juandie_hua.mycar.shouhuodizi;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.mycar.orderpay.heka;
import com.example.juandie_hua.utils.StatusBarUtils;

public class tuijianyu extends BaseActivity {

    @ViewInject(R.id.tuijianyu_gridv)
    GridView gdv;
    @ViewInject(R.id.tuijianyu_listv)
    ListView listv_v;

    gridv_adapter gdvada;
    List<gridv_adaData> gdlist;

    listtjy_adapter listtjada;
    List<listtjy_adaData> tjylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setTitleContentView(R.layout.tuijianyu);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);
//		if (Build.VERSION.SDK_INT >= 21) {
//			Window window = getWindow();
//			// 取消设置透明状态栏,使 ContentView 内容不再沉浸到状态栏下
//			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			// 需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//			// 设置状态栏颜色
//			window.setStatusBarColor(Color.parseColor("#f2f3f5"));
//		}
        x.view().inject(this);
        setviewhw();
        setviewdata();
        setviewlisten();
    }

    private void setviewlisten() {
        gdv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                for (int i = 0; i < gdlist.size(); i++) {
                    if (i == position) {
                        gdlist.get(position).setIsselect(true);
                    } else
                        gdlist.get(i).setIsselect(false);
                }
                gdvada.notifyDataSetChanged();
                add_data(position);

            }
        });

        listv_v.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Message msg = Message.obtain();
                msg.what = 0x001;
                Bundle bundle = new Bundle();
                bundle.putString("data", tjylist.get(position).getContxt());
                msg.setData(bundle);
                heka.handler.sendMessage(msg);
                finish();
                overridePendingTransition(R.anim.push_right_out,
                        R.anim.push_right_in);

            }
        });

    }

    private void setviewdata() {
        gdlist = new ArrayList<gridv_adaData>();
        gdvada = new gridv_adapter(tuijianyu.this, gdlist);

        gdv.setAdapter(gdvada);

        gdlist.add(new gridv_adaData(2 + "", "送恋人", true));
        gdlist.add(new gridv_adaData(3 + "", "送朋友", false));
        gdlist.add(new gridv_adaData(4 + "", "送长辈", false));
        gdlist.add(new gridv_adaData(5 + "", "送花篮", false));

        gdvada.notifyDataSetChanged();

        tjylist = new ArrayList<>();
        listtjada = new listtjy_adapter(tuijianyu.this, tjylist);
        listv_v.setAdapter(listtjada);

        add_data(0);
    }

    private void setviewhw() {

        this.getTitleView().setTitleText("推荐祝福语");
        gdv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        listv_v.setSelector(new ColorDrawable(Color.TRANSPARENT));
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

    private void add_data(int position) {
        tjylist.removeAll(tjylist);
        switch (position) {
            case 0:
                tjylist.add(new listtjy_adaData(
                        "",
                        "我多么希望，有一个门口，早晨，阳光照在草上，我们站着，扶着自己的门扇，门很低，但太阳是明亮的。草在结它的种子，风在摇它的叶子，我们站着，不说话，就十分美好。"));
                tjylist.add(new listtjy_adaData("",
                        "女王大人！原谅我吧！我知道我错了，你若不能原谅我的话，那就不断的打电话骂我吧！我愿意被你骂到老！"));
                tjylist.add(new listtjy_adaData("", "即使我五音不全，可还是想为你唱一万首情歌……"));
                tjylist.add(new listtjy_adaData("",
                        "爱你是我一生无悔的决定，漫天星星都是我注视你的眼睛。无论结局如何，我都知道：此生最爱是你！  "));
                tjylist.add(new listtjy_adaData("",
                        "时间会慢慢地让你了解，一个外表很冷漠又很怕羞的人，他的心却充满了对你的爱。  "));

                tjylist.add(new listtjy_adaData("",
                        "遇到你我感到很幸福，但是我不能成为世界上最幸福的人，因为我们在一起，我要让你成为世界上最幸福的人。  "));
                tjylist.add(new listtjy_adaData("",
                        "有些情感，无需多么华美，只是简单的遇见，简单的想念，哪怕它只是简单到：拉着你的手，迎着风走！  "));
                tjylist.add(new listtjy_adaData("",
                        "我想和你虚度时光，比如低头看鱼，比如把茶杯留在桌子上离开，浪费它们好看的阴影，我还想连落日一起浪费，比如散步，一直消磨到星光满天……   "));
                tjylist.add(new listtjy_adaData("",
                        "不要因为也许会改变，就不肯说那句美丽的誓言；不要因为也许会分离，就不敢求一次倾心的相遇。   "));
                listtjada.notifyDataSetChanged();
                break;
            case 1:
                tjylist.add(new listtjy_adaData("", "生日快乐么么哒，恭祝兄弟仙福永享寿与天齐。   "));
                tjylist.add(new listtjy_adaData("",
                        "用友谊，为你披一件健康的衣裳防寒；用真诚，为你送一份吉祥的关怀送暖；用提醒，为你定一条如意的短信保安。朋友祝你健康缠绵，顺利无边，吉祥常伴！   "));
                tjylist.add(new listtjy_adaData("", "朋友是一颗心住在两个人的心房里   "));
                tjylist.add(new listtjy_adaData("", "不管世界尽头多寂寞，你的身边一定有我   "));
                tjylist.add(new listtjy_adaData("", "无私无邪无猜无疑的友情无价，你是我买不到的奢华   "));
                tjylist.add(new listtjy_adaData("", "所有人都说我很坚强，只有你劝我别逞强   "));
                tjylist.add(new listtjy_adaData("", "不求同年同月同日生，但求同年同月同日疯   "));
                tjylist.add(new listtjy_adaData("",
                        "有些情感，无需多么华美，只是简单的遇见，简单的想念，哪怕它只是简单到：拉着你的手，迎着风走！   "));
                tjylist.add(new listtjy_adaData("",
                        "这些日子你过得还好吗？也许忙碌改变了我们的生活，但我永远珍惜我们的情谊。   "));
                tjylist.add(new listtjy_adaData("", "一切的不顺心都是纸老虎！希望你开心！   "));
                listtjada.notifyDataSetChanged();
                break;
            case 2:
                tjylist.add(new listtjy_adaData("",
                        "您是经霜的枫树老更红历尽悲欢，愈显得襟怀坦荡。衷心祝愿您生命之树常青！   "));
                tjylist.add(new listtjy_adaData("",
                        "寿比南山不老松，松古长春朝朝永生。祝您生辰快乐！健康幸福！   "));
                tjylist.add(new listtjy_adaData(
                        "",
                        "一斤花生二斤枣，好运经常跟您跑；三斤苹果四斤梨，吉祥和您不分离；五斤桔子六斤蕉，财源滚进您腰包；七斤葡萄八斤橙，愿您心想事就成；九斤芒果十斤瓜，愿您天天乐开花。   "));
                tjylist.add(new listtjy_adaData("",
                        "这一季有我最深的思念就让花儿捎去满心的祝福，缀满您甜蜜的梦境，祝您健康快乐！   "));
                tjylist.add(new listtjy_adaData("",
                        "祝愿您长寿健康。这一季的花开得格外娇艳，您可知，那是因为我用情感在浇灌只等您的生日赠与您，愿您喜欢。   "));
                tjylist.add(new listtjy_adaData("",
                        "这一刻有我最深的思念。让云捎去满心的祝福，点缀您甜蜜的梦。愿您拥有一个幸福快乐的新年！   "));
                tjylist.add(new listtjy_adaData("",
                        "天给您温暖，地给您温馨，我给您祝福。祝您运气像雨点一样密集！烦恼像流云一样飞去，忧愁像恐龙一样灭绝，幸福像蜂蜜一样甜美。   "));
                tjylist.add(new listtjy_adaData("",
                        "如果我有一棵快乐草，我想将它给您，我希望您快乐；如果我有两棵，我会给您一棵我一棵，希望我们都快乐；如果有三棵，我会给您两棵，希望您比我快乐！   "));
                tjylist.add(new listtjy_adaData("",
                        "吉祥如意、富贵安康；让美丽的朝霞、彩霞、晚霞一起飞进您的生活，这就是我的祈愿！   "));
                tjylist.add(new listtjy_adaData("",
                        "亲爱的妈妈，我也想嫁出去，只是没遇到对的人。但我保证会过得很好，你别担心。你和爸爸身体健康才是我最幸福的事情。多出去走走，看看这个世界。   "));
                listtjada.notifyDataSetChanged();

                break;
            case 3:
                tjylist.add(new listtjy_adaData("", "恒心有恒业，隆德享隆名。   "));
                tjylist.add(new listtjy_adaData("", "财源通四海，生意畅三春。   "));
                tjylist.add(new listtjy_adaData("", "生意似春笋，财源如春潮。   "));
                tjylist.add(new listtjy_adaData("", "大展宏图，开业大吉。   "));
                tjylist.add(new listtjy_adaData("", "敬贺开张，并祝吉祥。   "));
                tjylist.add(new listtjy_adaData("", "吉祥开业，大富启源。   "));
                tjylist.add(new listtjy_adaData("", "一门瑞气，万里和风。   "));
                tjylist.add(new listtjy_adaData("", "鸿基始创，骏业日新。   "));
                tjylist.add(new listtjy_adaData("", "宏图大展，裕业有孚。   "));
                tjylist.add(new listtjy_adaData("", "生意兴隆，恭喜发财。   "));
                listtjada.notifyDataSetChanged();
                break;

            default:
                break;
        }

    }
}
