package com.kien.lp.myapplication.layout_activity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.Adapter_View_Number;
import com.kien.lp.myapplication.model.Item_View_Number;
import com.kien.lp.myapplication.mysingleton.MySingleTon;

import java.util.ArrayList;
import java.util.List;

public class Activity_ViewNumber extends AppCompatActivity implements View.OnClickListener {
    RecyclerView mRecyclerview_View_Number;
    Adapter_View_Number mAdapter;
    List<Item_View_Number> mList_ViewNumber;
    ImageView ic_action_bar_back;
    MySingleTon mSingleTon = MySingleTon.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__view_number);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRecyclerview_View_Number = findViewById(R.id.mRecyclerview_View_Number);
        ic_action_bar_back = findViewById(R.id.ic_action_bar_back);
        ic_action_bar_back.setOnClickListener(this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview_View_Number.setLayoutManager(mLayoutManager);
        mSingleTon.getmList_View_Number();
        mAdapter = new Adapter_View_Number(  mSingleTon.getmList_View_Number(),this);
        mRecyclerview_View_Number.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_action_bar_back:
              onBackPressed();
//                Intent intent  = new Intent(this,Activity_HowtoPlay.class);
//                startActivity(intent);
                break;
                default:
                    break;
        }
    }
}
