package com.zsq.android.host.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.zsq.android.uikit.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZHAOSHENGQI467 on 2017/7/10.
 */

public class FlowLayoutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flow_layout);
        ButterKnife.bind(this);
        ImageView imageView = (ImageView) findViewById(R.id.transition_image_view);
        ViewCompat.setTransitionName(imageView, "option3");
        setSupportActionBar(mToolbar);
    }
}
