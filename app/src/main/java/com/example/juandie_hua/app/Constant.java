package com.example.juandie_hua.app;

import android.os.Environment;

import java.io.File;

/**
 * 常量存放类
 */
public class Constant {
    public static int WINDOWPAD = 8;
    public static final String PHONE = "4006089178";


    /**
     * 本地文件存储名称与路径
     */
    /*一级目录*/
    private static final String FirstFolder = "JuanDie";
    /*相册路径*/
    public static final String PATH = Environment.getExternalStorageDirectory() + File.separator + FirstFolder + File.separator;
    /*相册目录*/
    public static final String ALBUM = "album";
    /*安装包目录*/
    public static final String SETUP = "setup";


    /**
     * sharedPreference 存储参数
     */
    public static final String is_login = "is_login";
    public static final String cook = "cook";
    public static final String uid = "uid";
    /*支付成功订单号信息*/
    public static final String perorderid = "perorderid";
    public static final String perordertime = "perordertime";
    public static final String hbcs = "hbcs";
    public static final String kfpho = "kfpho";
    public static final String sye_dl = "sye_dl";
    /*是否绑定手机号 0:不需要绑定；1已绑定；2未绑定*/
    public static final String iswxbd = "iswxbd";
    /*第三方登录标志 "wx"微信登录；"dsf"QQ微博登录*/
    public static final String typeqd = "typeqd";
    /*屏蔽中间的手机号*//*登录账号*/
    public static final String username = "username";
    /*手机号*/
    public static final String phone = "phone";
    /*足迹*/
    public static final String zhuji = "zhuji";
    /*是否为首次登录*/
    public static final String first = "first";
    /*版本号*/
    public static final String bbh = "bbh";
    public static final String openid = "openid";
    public static final String zfddh = "zfddh";
    /*是否为分享*/
    public static final String isfenx = "isfenx";
    public static final String phpsid = "phpsid";
    public static final String msg_qudao = "msg_qudao";
    public static final String msg_qudao1 = "msg_qudao1";
    public static final String regid = "regid";


    public static final String mq = "mq";
}
