package com.wl.swiprefreshandloding.ChangeActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wl.swiprefreshandloding.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wl on 2016/9/25.
 */
public class SwipRefreshLayout_RecyclerView extends AppCompatActivity{

    private SwipeRefreshLayout mSwipRefresh;
    private RecyclerView mRecyclerView;
    private List<String> data = new ArrayList<>();
    private MyAdapter adapter;
    private int lastVisibleItem;    // 当前页的最后一个 item
    private LinearLayoutManager mLinearLayoutManager;
    private int count;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        getSupportActionBar().hide();

        initView();
        initData();
        initAdapter();
        /**
         * refresh or loading more data
         */
        initRefreshLable();
        initRefresh();
        
    }

    private void initAdapter() {
        adapter = new MyAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }


    private void initView() {
        mSwipRefresh = (SwipeRefreshLayout) findViewById(R.id.swiprefresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }

    private void initData() {
        for (int i = 0; i < 50; i++) {
            data.add("当前数据为："+ i);
        }
    }

    private void initRefresh() {
        //* TODO use SwiperRefreshLayout refresh data
        mSwipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //* TODO refresh data by requre data again and notity adapter change
                adapter.notifyDataSetChanged();

                //* TODO make the refresh ball dismiss after refresh data
//                    mSwipRefresh.setRefreshing(false);
            }
        });

        //* TODO use RecyclerView loading more data
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 ==adapter.getItemCount()) {
                    mSwipRefresh.setRefreshing(true);
                    data.add("新加载的第：" + count++ + "个");

                    adapter.notifyDataSetChanged();
//                    mSwipRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }


    private void initRefreshLable() {
        // set the ball of color
        mSwipRefresh.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
//        mSwipRefresh.setProgressViewOffset(false, 0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,24,getResources().getDisplayMetrics()));
        //* 设置刷新小圆圈相对于最上端的位置
        mSwipRefresh.setProgressViewOffset(true,0,500);
//        mSwipRefresh.setProgressBackgroundColor();
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(new TextView(SwipRefreshLayout_RecyclerView.this));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
//            if (holder ==null)
            holder.textView.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            int dataLength = data.size() > 0 ? data.size() : 0;
//            Log.i("wl", "getItemCount: " + dataLength);
            return dataLength;
        }
        // creat class viewholder
        class MyHolder extends RecyclerView.ViewHolder{
            TextView textView = new TextView(SwipRefreshLayout_RecyclerView.this);
            public MyHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView;
            }
        }
    }
}
