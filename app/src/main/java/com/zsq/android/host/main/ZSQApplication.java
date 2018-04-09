package com.zsq.android.host.main;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.zsq.android.framework.base.rn.ReactNativeManager;
import com.zsq.android.framework.base.utils.BaseApplication;

/**
 * Created by zhaoshengqi on 2018/3/30.
 */

public class ZSQApplication extends BaseApplication implements ReactApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        doOnCreate();
    }

    private void doOnCreate() {

    }

    @Override
    public ReactNativeHost getReactNativeHost() {
        return ReactNativeManager.getInstance().getReactNativeHost();
    }
}
