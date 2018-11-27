package com.kien.lp.myapplication.layout_activity;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Display;
import android.view.View;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.result.Adapter_Result;

import java.util.ArrayList;
import java.util.List;

public class Activity_Result extends AppCompatActivity {
    RecyclerView mRecyclerviewResult;
    List<String> mList_String = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__result);
        addActionBar();
        addControll();
    }

    private void addActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FECC45")));


    }

    private void addControll() {
        mRecyclerviewResult =findViewById(R.id.mRecyclerview_Result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerviewResult.setLayoutManager(linearLayoutManager);
        mList_String.add("1");
        mList_String.add("1");
        mList_String.add("1");
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerviewResult);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        layoutManager.scrollToPositionWithOffset(0, width / 8);
        Adapter_Result mAdapter_result = new Adapter_Result(mList_String,this);
        mRecyclerviewResult.setAdapter(mAdapter_result);
        mAdapter_result.notifyDataSetChanged();
    }
}
