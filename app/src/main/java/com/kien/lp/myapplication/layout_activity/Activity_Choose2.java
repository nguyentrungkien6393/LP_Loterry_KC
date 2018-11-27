package com.kien.lp.myapplication.layout_activity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Display;
import android.widget.LinearLayout;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.buyticket.Ticket;
import com.kien.lp.myapplication.adapter.buyticket.TiketApdater;

import java.util.ArrayList;
import java.util.List;

public class Activity_Choose2 extends AppCompatActivity {
    RecyclerView mReyclerview;
    TiketApdater mAdapter;
    public int mRow = 0;
    public int position = 0;
    private List<Ticket> mListChoose = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose2);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        mRow = intent.getIntExtra("sizelist", 0);
        Log.e("SizeList", position + "____________" + mRow);
        for (int i = 0; i < mRow; i++) {
            mListChoose.add(new Ticket(1, false));
            Log.e("=======================", i + "");
        }
        addActionBar();
        addEvent();
    }

    private void addActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FECC45")));
    }

    private void addEvent() {
        mReyclerview = findViewById(R.id.mRecyclerview_Choose_Parent);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mReyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        Display display =getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        layoutManager.scrollToPositionWithOffset(position, width);
        mReyclerview.setLayoutManager(layoutManager);
        mAdapter = new TiketApdater(this, mListChoose);
        mReyclerview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
