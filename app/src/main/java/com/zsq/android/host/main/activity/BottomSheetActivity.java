package com.zsq.android.host.main.activity;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zsq.android.uikit.R;
import com.zsq.android.uikit.test.model.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZHAOSHENGQI467 on 2017/10/10.
 */

public class BottomSheetActivity extends BaseActivity {

    private PopupWindow mBottomPopWindow;
    private ArrayList<AppInfo> mAppInfos;
    private Dialog mBottomDialog;
    @BindView(R.id.bottom_navi_menu) BottomNavigationView mBottomNaviView;

    interface OnShareItemClickListener {

        void onItemClick(AppInfo appInfo, View view, int position);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_layout);
        ButterKnife.bind(this);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "www.github.com");
        mAppInfos = getAppInfos(shareIntent);

        // 使用PopupWindow实现
        View popupWindowView = getLayoutInflater().inflate(R.layout.share_dialog_layout, null);
        // 构造方法中传递需要显示的View，已经显示的宽和高
        mBottomPopWindow = new PopupWindow(popupWindowView, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        mBottomPopWindow.setOutsideTouchable(true);
        mBottomPopWindow.setAnimationStyle(R.style.bottom_popup_style);

        // 设置窗口的背景为透明
        mBottomPopWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

        // 初始化底部弹出dialog
        View dialogView = getLayoutInflater().inflate(R.layout.share_dialog_layout, null);
        mBottomDialog = new Dialog(BottomSheetActivity.this, R.style.bottom_dialog_style);
        Window window = mBottomDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.bottom_popup_style);
            WindowManager.LayoutParams lps = window.getAttributes();
            lps.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        mBottomDialog.setContentView(dialogView);
    }

    @OnClick({R.id.system_bottom_sheet, R.id.bottom_pop_window, R.id.bottom_dialog, R.id.bottom_navi_view})
    public void showSystemSheet(View v) {
        switch (v.getId()) {
            case R.id.system_bottom_sheet:
                showZhiHuShareDialog();
                break;
            case R.id.bottom_pop_window:
                // 设置PopupWindow显示的位置
                mBottomPopWindow.showAtLocation(getWindow().getDecorView(),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                View contentView = mBottomPopWindow.getContentView();
                // 设置PopupWindow显示的背景色，默认为透明
                initShareAdapter(contentView);
                break;
            case R.id.bottom_dialog:
                mBottomDialog.show();
                initShareAdapter(mBottomDialog.findViewById(R.id.share_container));
                break;
            case R.id.bottom_navi_view:
                if (mBottomNaviView.getVisibility() == View.GONE) {
                    mBottomNaviView.animate().alpha(1.0f)
                            .translationY(0)
                            .setDuration(300)
                            .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            mBottomNaviView.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    }).start();
                } else {
                    mBottomNaviView.animate()
                            .alpha(0)
                            .translationY(mBottomNaviView.getHeight())
                            .setDuration(300)
                            .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mBottomNaviView.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    }).start();
                }
                break;
        }
    }

    // 显示系统BottomSheetDialog分享框
    private void showZhiHuShareDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(BottomSheetActivity.this);
        dialog.setContentView(R.layout.share_dialog_layout);
        initShareAdapter(dialog.findViewById(R.id.share_container));
        dialog.show();
    }

    private void initShareAdapter(View view) {
        AppInfoAdapter appInfoAdapter = new AppInfoAdapter(BottomSheetActivity.this, mAppInfos);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new GridLayoutManager(BottomSheetActivity.this, 3));
        recyclerView.setAdapter(appInfoAdapter);
        appInfoAdapter.setOnItemClickListener(new OnShareItemClickListener() {
            @Override
            public void onItemClick(AppInfo appInfo, View view, int position) {

            }
        });
    }

    private ArrayList<AppInfo> getAppInfos(Intent intent) {
        ArrayList<AppInfo> appInfos = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(
                intent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        if (resolveInfos != null) {
            for (ResolveInfo resolveInfo : resolveInfos) {
                AppInfo appInfo = new AppInfo();
                appInfo.setPkgName(resolveInfo.activityInfo.packageName);
                appInfo.setLaunchClassName(resolveInfo.activityInfo.name);
                appInfo.setAppName(resolveInfo.loadLabel(packageManager).toString());
                appInfo.setAppIcon(resolveInfo.loadIcon(packageManager));
                appInfos.add(appInfo);
            }
        }
        return appInfos;
    }

    private static class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.ViewHolder> {

        private final Context mContext;
        private ArrayList<AppInfo> mAppInfos;
        private OnShareItemClickListener mListener;

        AppInfoAdapter(Context context, ArrayList<AppInfo> appInfos) {
            mContext = context;
            mAppInfos = appInfos;
        }

        void setOnItemClickListener(OnShareItemClickListener listener) {
            mListener = listener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            return new ViewHolder(
                    layoutInflater.inflate(R.layout.share_item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.tv.setText(mAppInfos.get(position).getAppName());
            holder.img.setBackground(mAppInfos.get(position).getAppIcon());
            if (mListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        mListener.onItemClick(mAppInfos.get(position), holder.itemView, position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mAppInfos.size();
        }

        static final class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            ImageView img;

            ViewHolder(View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.text_list_item);
                img = itemView.findViewById(R.id.img_list_item);
            }
        }
    }

}
