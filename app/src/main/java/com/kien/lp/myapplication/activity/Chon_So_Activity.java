//package com.kien.lp.myapplication.activity;
//
//import android.content.Intent;
//import android.graphics.Point;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.PagerSnapHelper;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.SnapHelper;
//import android.util.Log;
//import android.view.Display;
//
//import com.kien.lp.myapplication.R;
//import com.kien.lp.myapplication.adapter.buyticket.Ticket;
//import com.kien.lp.myapplication.adapter.buyticket.TiketApdater;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Chon_So_Activity extends AppCompatActivity {
//    public int mRow = 0;
//    public int position = 0;
//    public RecyclerView mReyclerview;
//    public TiketApdater mAdapter;
//    private List<Ticket> mListChoose = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chon_so);
//        Intent intent = getIntent();
//        position = intent.getIntExtra("position", 0);
//        mRow = intent.getIntExtra("size", 0);
//        Log.e("CHECKK_INTENT", mRow + "_______" + position);
//
//
//        mReyclerview = findViewById(R.id.mRecyclerview_Choose_Parent);
//        for (int i=0;i<mRow;i++){
//            mListChoose.add(new Ticket(1,true));
//            Log.e("=======================",i+"");
//        }
//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(mReyclerview);
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int width = size.x;
////        layoutManager.scrollToPositionWithOffset(position, width/8);
//        layoutManager.scrollToPositionWithOffset(position, width/2);
//        mReyclerview.setLayoutManager(layoutManager);
//        mAdapter = new TiketApdater(this,mListChoose);
//        mReyclerview.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
//
//    }
//}
