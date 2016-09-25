package com.wl.swiprefreshandloding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wl.swiprefreshandloding.ChangeActivity.SwipRefreshLayout_RecyclerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mSwipRefresh_RecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mSwipRefresh_RecyclerView = (Button) findViewById(R.id.btnOne);
        mSwipRefresh_RecyclerView.setOnClickListener(this);
    }

    //TODO change activity
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnOne:
                Toast.makeText(this,"页面跳转",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SwipRefreshLayout_RecyclerView.class));


                break;
            case R.id.btnTwo:
//                turnActivity(SwipRefreshLayout_RecyclerView.class);
                break;
            case R.id.btnThree:
                break;
            case R.id.btnFour:
                break;
        }
    }
}
