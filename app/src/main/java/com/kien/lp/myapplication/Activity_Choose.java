package com.kien.lp.myapplication;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.kien.lp.myapplication.fragment.Fragment_Choose_2;
import com.kien.lp.myapplication.fragment.Signin_Fragment_Account;

public class Activity_Choose extends AppCompatActivity {
    LinearLayout mFragment_Choose_1;
    private FragmentTransaction fragTransaction;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__choose);
        addControll();
//        fragmentManager = getSupportFragmentManager();
//        fragTransaction = fragmentManager.beginTransaction();
//        Fragment_Choose_2 mFragment = Fragment_Choose_2.newInstance("", "");
//        fragTransaction.add(mFragment_Choose_1.getId(), mFragment);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        fragTransaction.commit();
    }

    private void addControll() {
        mFragment_Choose_1 = findViewById(R.id.mFragment_Choose_1);

    }

}
