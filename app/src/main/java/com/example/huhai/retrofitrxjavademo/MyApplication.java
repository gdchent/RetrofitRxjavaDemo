package com.example.huhai.retrofitrxjavademo;

import android.app.Application;
import android.content.Context;

import com.example.huhai.retrofitrxjavademo.manager.Dbhelper;
import com.facebook.stetho.Stetho;

/*
 *  @项目名：  RetrofitRxjavaDemo
 *  @包名：    com.example.huhai.retrofitrxjavademo
 *  @文件名:   MyApplication
 *  @创建者:   huhai
 *  @创建时间:  2018/11/1 9:42
 *  @描述：
 */
public class MyApplication extends Application {
    public static Context mContext;

    /**
     * 得到上下文
     */
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //开启抓包谷歌浏览器抓包
        if (BuildConfig.IS_DEBUG){
            opennetWorkDebug();
        }
        //初始化数据库
        initsql();

    }

    private void initsql() {

        Dbhelper.initGreenDao();
    }

    private void opennetWorkDebug() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
