package com.kien.lp.myapplication.layout_activity;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.mysingleton.MySingleTon;

public class Activity_Wallet extends AppCompatActivity {
    private MySingleTon mySingleTon = MySingleTon.getInstance();
    private TextView mTV_wallet,mTV_LTR_Result,mTV_ETH_Result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acftivity_wallet);
        addControll();
        addActionBar();
    }

    private void addActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FECC45")));


    }

    private void addControll() {
        mTV_wallet = findViewById(R.id.mTV_wallet);
        mTV_LTR_Result = findViewById(R.id.mTV_LTR_Result);
        mTV_ETH_Result = findViewById(R.id.mTV_ETH_Result);
        mTV_wallet.setText(mySingleTon.getWallet_Finish());
        mTV_LTR_Result.setText(mySingleTon.getLTR_Balance());
        mTV_ETH_Result.setText(mySingleTon.getETH_Balance());
    }
}
