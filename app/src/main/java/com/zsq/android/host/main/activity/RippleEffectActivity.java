package com.zsq.android.host.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zsq.android.uikit.R;


/**
 * Created by ZHAOSHENGQI467 on 2017/9/27.
 */

public class RippleEffectActivity extends BaseActivity {

    public static final String TAG = "RippleEffectActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ripple_effect_layout);
        Button rippleButton = findViewById(R.id.ripple_button);
        rippleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Ripple button clicked");
            }
        });
    }
}
