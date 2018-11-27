package com.kien.lp.myapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.kien.lp.myapplication.fragment.Fragment_Choose_1;
import com.kien.lp.myapplication.fragment.Fragment_Choose_HowtoPlay;
import com.kien.lp.myapplication.fragment.Signin_Fragment_Account;

public class LoaddingActivity extends AppCompatActivity {
    Fragment_Choose_HowtoPlay mFragment;
    Fragment_Choose_1 mFragment_GameInfo;
    LinearLayout mll ;
    FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadding);
        mll = findViewById(R.id.ln_fr);
        mFragmentManager = getSupportFragmentManager();
         FragmentTransaction fragTransaction = mFragmentManager.beginTransaction();
        mFragment_GameInfo = Fragment_Choose_1.newInstance("", "");
        fragTransaction.add(mll.getId(), mFragment_GameInfo);
        fragTransaction.commit();
    }
}
