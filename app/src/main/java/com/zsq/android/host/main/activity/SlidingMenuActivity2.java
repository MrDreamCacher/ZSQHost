package com.zsq.android.host.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.zsq.android.uikit.R;
import com.zsq.android.uikit.SlidingMenuView2;
import com.zsq.android.uikit.test.fragment.ContentFragment2;
import com.zsq.android.uikit.test.fragment.LeftMenuFragment;


/**
 * Created by ZHAOSHENGQI467 on 2017/7/10.
 */

public class SlidingMenuActivity2 extends BaseActivity {

    private static final String TAG = "SlidingMenuActivity2";

    private SlidingMenuView2 mSlidingMenu;

    private LeftMenuFragment mMenuFragment;

    private ContentFragment2 mContentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_menu2);

        setUpViews();
    }

    private void setUpViews() {
        mSlidingMenu = (SlidingMenuView2) findViewById(R.id.slidingMenu);

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mMenuFragment = new LeftMenuFragment();
        ft.replace(R.id.menu_container, mMenuFragment);

        mContentFragment = new ContentFragment2();
        ft.replace(R.id.content_container, mContentFragment);
        ft.commit();
    }
}
