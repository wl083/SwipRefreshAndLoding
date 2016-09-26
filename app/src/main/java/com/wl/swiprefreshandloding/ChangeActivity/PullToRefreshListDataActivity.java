package com.wl.swiprefreshandloding.ChangeActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wl.swiprefreshandloding.R;

/**
 * Created by wl on 2016/9/26.
 * 自定义 PullToRefresh 实现刷新加载及其点击事件
 */
public class PullToRefreshListDataActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        //* hide titlebar
        getSupportActionBar().hide();
    }
}
