package com.zsq.android.host.main;

import android.support.multidex.MultiDexApplication;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;

import java.util.List;

/**
 * Created by zhaoshengqi on 2018/3/30.
 */

public class ZSQApplication extends MultiDexApplication implements ReactApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        doOnCreate();
    }

    private void doOnCreate() {

    }

    @Override
    public ReactNativeHost getReactNativeHost() {
        return new ReactNativeHost(this) {
            @Override
            public boolean getUseDeveloperSupport() {
                return false;
            }

            @Override
            protected List<ReactPackage> getPackages() {
                return null;
            }
        };
    }
}
