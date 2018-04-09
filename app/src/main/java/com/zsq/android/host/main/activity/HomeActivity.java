package com.zsq.android.host.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.zsq.android.framework.base.activity.BaseActivity;
import com.zsq.android.framework.base.rn.ReactNativeManager;
import com.zsq.android.host.R;
import com.zsq.android.host.main.fragment.HomeFragment;

/**
 * Created by zhaoshengqi on 2018/3/27.
 */

public class HomeActivity extends BaseActivity implements DefaultHardwareBackBtnHandler {

    private ReactInstanceManager mReactInstance;
    private HomeFragment mHomeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReactInstance = ReactNativeManager.getInstance().getReactInstanceManager();
        setContentView(R.layout.main_activity_layout);
        setupViews();
    }

    private void setupViews() {
        FragmentManager fm = getSupportFragmentManager();
        mHomeFragment = (HomeFragment) fm.findFragmentByTag("home_fragment");
        if (mHomeFragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            mHomeFragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putString("screen", "HomeContainer");
            ft.add(R.id.home_content, mHomeFragment, "screen");
            mHomeFragment.setArguments(args);
            ft.commitNowAllowingStateLoss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mReactInstance != null) {
            mReactInstance.onHostResume(this, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mReactInstance != null) {
            mReactInstance.onHostPause(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReactInstance != null) {
            mReactInstance.onHostDestroy(this);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mReactInstance != null) {
            mReactInstance.onActivityResult(this, requestCode, resultCode, data);
        }
    }

    @Override
    public void invokeDefaultOnBackPressed() {

    }
}
