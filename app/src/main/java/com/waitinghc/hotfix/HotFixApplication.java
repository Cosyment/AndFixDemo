package com.waitinghc.hotfix;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * Create by:     何超
 * Create Time:   2016/12/29
 * Brief Desc:
 */

public class HotFixApplication extends Application {


    private static HotFixApplication instance;
    private PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        //初始化patch管理类
        mPatchManager = new PatchManager(this);

        //初始化patch版本
        mPatchManager.init("1.0");


        //加载已经添加到patch管理类中的patch
        mPatchManager.loadPatch();
//        mPatchManager.removeAllPatch();
    }


    public static HotFixApplication getInstance() {
        return instance;
    }

    public PatchManager getPatchManager() {
        return mPatchManager;
    }
}
