package com.kien.lp.myapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.fragment.Fragment_Choose_1;
import com.kien.lp.myapplication.fragment.Fragment_Choose_2;
import com.kien.lp.myapplication.fragment.Fragment_Choose_HowtoPlay;
import com.kien.lp.myapplication.fragment.Signin_Fragment_Account;
import com.kien.lp.myapplication.fragment.Signin_Fragment_Finish;
import com.kien.lp.myapplication.fragment.Signin_Fragment_Peronal;
import com.kien.lp.myapplication.layout_activity.Activity_HowtoPlay;
import com.kien.lp.myapplication.model.GameInfo;
import com.kien.lp.myapplication.mysingleton.MySingleTon;

import java.util.List;
import java.util.Locale;

public class Adapter_GameInfo extends RecyclerView.Adapter<Adapter_GameInfo.UserViewHolder>implements View.OnClickListener {
    List<GameInfo> mList;
    Activity mActivity;
    private static final long START_TIME_IN_MILLIS = 864000000;
    private CountDownTimer mCountDownTimer;
    private FragmentTransaction fragTransaction;
    private FragmentManager fragmentManager;
    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private int visibleThreshold = 5;
    private int lastVisibleItem = 0, totalItemCount = 0, firstVisibleItem = 0, visibleItemCount = 0;
    private int previousTotal = 0;
    public OnItemClickListenner mOnItemClickListenner;
    private static final int TAKE_PICTURE = 1;
    private String URL = "http://buylottery.org/upload/";
    public Adapter_GameInfo(List<GameInfo> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        if (position == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_choose_number, viewGroup, false);
            return new UserViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        if (userViewHolder instanceof UserViewHolder) {
            final UserViewHolder myuserViewHolder = (UserViewHolder) userViewHolder;
            GameInfo mGameInfo = mList.get(position);
            myuserViewHolder.mTime_Dial.setText(mGameInfo.getDraw_time());
            Log.e("GET---LINK---URL",URL+mGameInfo.getImage());
            startTimer(myuserViewHolder);
            myuserViewHolder.mTV_GameInfo_Price.setText(mGameInfo.getTicket_price()+" BTC" );
//            Glide.with(mActivity).load(URL+mGameInfo.getImage())
//                    .placeholder(R.drawable.image_poitrait).centerCrop()
//                    .into(myuserViewHolder.mIMG_GameInfo);
//            myuserViewHolder.mBTN_GameInfo_Play.setOnClickListener(this);

//            https://buylottery.org/upload/games/2018/08/24/ltr3.png

                        Glide.with(mActivity).load("https://buylottery.org/upload/games/2018/08/24/ltr3.png")
                    .placeholder(R.drawable.image_poitrait).centerCrop()
                    .into(myuserViewHolder.mIMG_GameInfo);
            myuserViewHolder.mBTN_GameInfo_Play.setOnClickListener(this);
        }
    }

    @Override
    public int getItemCount() {
        return   mList == null ? 0 : mList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.mBTN_GameInfo_Play:
                Log.e("_________","++++++++");
//                fragmentManager = ((FragmentActivity)mActivity).getSupportFragmentManager();
//                 fragTransaction = fragmentManager.beginTransaction();
//                Fragment_Choose_HowtoPlay fragment2 = Fragment_Choose_HowtoPlay.newInstance("", "");
////                fragTransaction.add(R.id.ln_fr, fragment2);
////                fragTransaction.commit();
//                Bundle bundle = new Bundle();
//                fragment2.setArguments(bundle);
//                fragTransaction.replace(R.id.ln_fr, fragment2);
//                fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                fragTransaction.commit();
                Intent intent = new Intent(mActivity, Activity_HowtoPlay.class);
                mActivity.startActivity(intent);
                break;
        }
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView mIMG_GameInfo;
        TextView mTV_GameInfo_Price,mTime_Dial;
        Button mBTN_GameInfo_Play;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mIMG_GameInfo = itemView.findViewById(R.id.mIMG_GameInfo);
            mTV_GameInfo_Price = itemView.findViewById(R.id.mTV_GameInfo_Price);
            mTime_Dial = itemView.findViewById(R.id.mTime_Dial);
            mBTN_GameInfo_Play = itemView.findViewById(R.id.mBTN_GameInfo_Play);
        }
    }
    private void startTimer(final UserViewHolder userViewHolder) {



        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText(userViewHolder);
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();

    }

    private void updateCountDownText(UserViewHolder userViewHolder) {
        int day = (int)(mTimeLeftInMillis/1000)/86400;
        int hour = (int)(mTimeLeftInMillis/1000)/3600%24;
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60%60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;


        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d days %02d:%02d:%02d",day,hour, minutes, seconds);

        userViewHolder.mTime_Dial.setText(timeLeftFormatted);
    }
}
