package com.kien.lp.myapplication;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.kien.lp.myapplication.fragment.Signin_Fragment_Account;
import com.kien.lp.myapplication.fragment.Signin_Fragment_Finish;
import com.kien.lp.myapplication.fragment.Signin_Fragment_Peronal;
import com.kien.lp.myapplication.library.CheckTablet;

public class Signin_Activity extends AppCompatActivity {
    LinearLayout ll_Acount_Fragment_1,ll_Personal_Fragment_2,ll_Finish_Fragment_3,fm_containe;
    private FragmentTransaction fragTransaction;
    private FragmentManager fragmentManager;
    private Signin_Fragment_Peronal Fragment_2;
    private Signin_Fragment_Finish Fragment_3;
    private Signin_Fragment_Account Fragment_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);
        addControll();
        addEvent();
//        if (CheckTablet.isTablet(getApplicationContext())) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        fragTransaction.remove(Fragment_2);
//        fragTransaction.remove(Fragment_3);
//            ll_Finish_Fragment_3.setVisibility(View.GONE);//
//            ll_Personal_Fragment_2.setVisibility(View.GONE);
//            ll_Acount_Fragment_1.setVisibility(View.GONE);
//        }











    }

    private void addEvent() {
        fragmentManager = getSupportFragmentManager();
        fragTransaction = fragmentManager.beginTransaction();
         Fragment_1 = Signin_Fragment_Account.newInstance("", "");
        fragTransaction.add(ll_Acount_Fragment_1.getId(), Fragment_1);
        fragTransaction.commit();
     }

    private void addControll() {
        fm_containe = findViewById(R.id.fm_containe);
        ll_Acount_Fragment_1 = findViewById(R.id.ll_Acount_Fragment_1);
//        ll_Personal_Fragment_2 = findViewById(R.id.ll_Personal_Fragment_2);
//        ll_Finish_Fragment_3 = findViewById(R.id.ll_Finish_Fragment_3);


    }



}
