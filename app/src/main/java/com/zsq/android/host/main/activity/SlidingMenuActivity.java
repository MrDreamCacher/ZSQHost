package com.zsq.android.host.main.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import com.zsq.android.uikit.R;
import com.zsq.android.uikit.SlidingMenuView;
import com.zsq.android.uikit.test.fragment.ContentFragment1;
import com.zsq.android.uikit.test.fragment.LeftMenuFragment;
import com.zsq.android.uikit.test.fragment.RightMenuFragment;


/**
 * Created by ZHAOSHENGQI467 on 2017/7/10.
 */

public class SlidingMenuActivity extends BaseActivity {

    private static final String TAG = "SlidingMenuActivity";

    LeftMenuFragment mLeftMenu;
    RightMenuFragment mRightMenu;
    ContentFragment1 mContentFragment;
    SlidingMenuView mSlidingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_menu);
        init();
        initListener();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void init() {
        mSlidingView = (SlidingMenuView) findViewById(R.id.slidingMenu);
        mSlidingView.setLeftView(getLayoutInflater().inflate(
                R.layout.left_frame, null));
        mSlidingView.setRightView(getLayoutInflater().inflate(
                R.layout.right_frame, null));
        mSlidingView.setCenterView(getLayoutInflater().inflate(
                R.layout.center_frame, null));

        FragmentTransaction t = this.getSupportFragmentManager()
                .beginTransaction();
        mLeftMenu = new LeftMenuFragment();
        t.replace(R.id.left_frame, mLeftMenu);

        mRightMenu = new RightMenuFragment();
        t.replace(R.id.right_frame, mRightMenu);

        mContentFragment = new ContentFragment1();
        t.replace(R.id.center_frame, mContentFragment);
        t.commit();
    }

    private void initListener() {
        mContentFragment.setMyPageChangeListener(new ContentFragment1.MyPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (mContentFragment.isFirst()) {
                    mSlidingView.setCanSliding(true, false);
                } else if (mContentFragment.isEnd()) {
                    mSlidingView.setCanSliding(false, true);
                } else {
                    mSlidingView.setCanSliding(false, false);
                }
            }
        });
    }

    public void showLeft() {
        mSlidingView.showLeftView();
    }

    public void showRight() {
        mSlidingView.showRightView();
    }
}
