package com.zsq.android.host.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.zsq.android.uikit.R;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                new String[]{
                        "Scroller实现的侧滑菜单",
                        "ViewDragHelper实现的侧滑菜单",
                        "自定义DrawerLayout",
                        "ScrollUpPanel类似于系统SlidingDrawer",
                        "FlowLayout瀑布流",
                        "ScrollBy理解scroll动画",
                        "水波纹效果的RelativeLayout",
                        "PathMeasure实例",
                        "底部弹框实例"
                }));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                ActivityOptionsCompat options = null;
                final Activity context = MainActivity.this;
                switch (position) {
                    case 0:
                        intent = new Intent(context, SlidingMenuActivity.class);
                        options = ActivityOptionsCompat.makeCustomAnimation(
                                context, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;

                    case 1:
                        intent = new Intent(context, SlidingMenuActivity2.class);
                        options = ActivityOptionsCompat.makeScaleUpAnimation(
                                mListView, mListView.getWidth() / 2, mListView.getHeight() / 2, 0, 0);
                        break;

                    case 2:
                        intent = new Intent(context, SlidingMenuActivity3.class);
                        options = ActivityOptionsCompat.makeScaleUpAnimation(
                                mListView, mListView.getWidth() / 2, mListView.getHeight() / 2, 0, 0);
                        break;

                    case 3:
                        intent = new Intent(context, ScrollPanelActivity.class);
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beauty01);
                        options = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(
                                mListView, bitmap, 0, 0);
                        break;

                    case 4:
                        intent = new Intent(context, FlowLayoutActivity.class);
                        options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                context, mListView, "option3");
                        break;

                    case 5:
                        intent = new Intent(context, RippleEffectActivity.class);
                        break;

                    case 6:
                        intent = new Intent(context, PathMeasureActivity.class);
                        break;

                    case 7:
                        intent = new Intent(context, BottomSheetActivity.class);
                        break;

                    default:
                        return;
                }

                if (options == null) {
                    options = ActivityOptionsCompat.makeCustomAnimation(
                            context, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
                ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
            }
        });

        Log.d(TAG, "density: " + getResources().getDisplayMetrics().density);
        Log.d(TAG, "screen height: " + getResources().getDisplayMetrics().heightPixels);

        ViewCompat.setTransitionName(mListView, "option3");


        Toolbar toolbar = findViewById(R.id.toolbar);

        //设置最左侧显示图片
        toolbar.setLogo(R.mipmap.ic_launcher);

        // 设置toolbar的标题，必须在setSupportActionBar方法之前设置
        toolbar.setTitle("首页");
        toolbar.setTitleTextColor(Color.BLACK);

        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(MainActivity.this, "点击了首页的图片", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
