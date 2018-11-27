package com.kien.lp.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kien.lp.myapplication.adapter.AdapterSpinner_Gender;
import com.kien.lp.myapplication.adapter.myaccount.AdapterAccount;
import com.kien.lp.myapplication.base.Create_Class;
import com.kien.lp.myapplication.fragment.Fragment_Choose_1;
import com.kien.lp.myapplication.fragment.Fragment_Choose_HowtoPlay;
import com.kien.lp.myapplication.fragment.result.Fragment_Result;
import com.kien.lp.myapplication.layout_activity.Activity_Result;
import com.kien.lp.myapplication.layout_activity.Activity_Transaction;
import com.kien.lp.myapplication.layout_activity.Activity_BuyToken;
import com.kien.lp.myapplication.model.Gender;
import com.kien.lp.myapplication.mysingleton.MySingleTon;
import com.kien.lp.myapplication.singleton.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Navigation_Bar extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle myToggle;
    private Fragment_Choose_HowtoPlay mFragment;
    private Fragment_Choose_1 mFragment_GameInfo;
    private Fragment_Result mFragment_ReSult;
    private LinearLayout mll, ln_si_su;
    private FragmentManager mFragmentManager;
    private TextView mTV_Ethereum, mTV_LTR, my_account;
    public String getBoolean = "";
    public String messenger = "";
    public String data = "";
    public String ETHBalance = "";
    public String LTRBalance = "";
    public String Wallet = "";
    private NavigationView mNavi;
    private RecyclerView mRecyclerview;
    private LinearLayoutManager layoutManager;
    private List<Gender> mList = new ArrayList<>();
    private AdapterSpinner_Gender mAdapter;
    private MySingleTon mSingleTon = MySingleTon.getInstance();
    private TextView mTV_HOME, mTV_LOTTERY_RUSULT, mTV_TRANSATION, mTV_SIGN_IN, mTV_SIGN_UP,mTV_mTV_BuyLTR_Token;
    private FragmentTransaction fragTransaction;
    private FragmentManager fragmentManager;
    private Bundle bundle;
    private RecyclerView mListView;
    private int clickcount = 0;
    private SharedPreferences settings;
    private Create_Class mCreate_class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
        setContentView(R.layout.activity_navigation__bar);
        mDrawer = findViewById(R.id.my_Drawer);
        myToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.open, R.string.close);
        mNavi = findViewById(R.id.navigationview);
        mDrawer.addDrawerListener(myToggle);
        myToggle.syncState();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FECC45")));
        mll = findViewById(R.id.ln_fr);
        mFragmentManager = getSupportFragmentManager();
        fragTransaction = mFragmentManager.beginTransaction();
        mFragment_GameInfo = Fragment_Choose_1.newInstance("", "");
        fragTransaction.replace(mll.getId(), mFragment_GameInfo);
        fragTransaction.commit();
        View headView = mNavi.getHeaderView(0);
        addControll(headView);


    }


    @Override
    protected void onStart() {
        super.onStart();
        loadControll();
        SharedPreferences editor1 = getSharedPreferences("88", MODE_PRIVATE);
        String username = editor1.getString("username", "");
        String passwords = editor1.getString("passwords", "");
        if (username.length()>0){
            loadbalance();
        }
    }

    private void loadControll() {
        SharedPreferences editor1 = getSharedPreferences("88", MODE_PRIVATE);
        String username = editor1.getString("username", "");
        String passwords = editor1.getString("passwords", "");
        if (username.length() < 1 || passwords.length() < 1) {
            ln_si_su.setVisibility(View.VISIBLE);
            my_account.setVisibility(View.GONE);
        } else {
            ln_si_su.setVisibility(View.GONE);
            my_account.setVisibility(View.VISIBLE);
        }
        mTV_HOME.setOnClickListener(this);
        mTV_LOTTERY_RUSULT.setOnClickListener(this);
        mTV_TRANSATION.setOnClickListener(this);
        mTV_SIGN_IN.setOnClickListener(this);
        mTV_SIGN_UP.setOnClickListener(this);
        my_account.setOnClickListener(this);
        mTV_mTV_BuyLTR_Token.setOnClickListener(this);

    }

    private void addControll(View headView) {
        mCreate_class = new Create_Class();
        mCreate_class.getETH(this);
        mTV_Ethereum = headView.findViewById(R.id.mTV_Ethereum);
        mListView = headView.findViewById(R.id.mListView);
        ln_si_su = headView.findViewById(R.id.ln_si_su);
        mTV_LTR = headView.findViewById(R.id.mTV_LTR);
        mTV_HOME = headView.findViewById(R.id.mTV_HOME);
        mTV_LOTTERY_RUSULT = headView.findViewById(R.id.mTV_LOTTERY_RUSULT);
        mTV_mTV_BuyLTR_Token = headView.findViewById(R.id.mTV_mTV_BuyLTR_Token);
        mTV_TRANSATION = headView.findViewById(R.id.mTV_TRANSATION);
        mTV_SIGN_IN = headView.findViewById(R.id.mTV_SIGN_IN);
        mTV_SIGN_UP = headView.findViewById(R.id.mTV_SIGN_UP);
        my_account = headView.findViewById(R.id.my_account);

    }

    private void loadbalance() {

        final List<String> mList = new ArrayList<>();
        settings = getSharedPreferences("88", MODE_PRIVATE);
        final String username = settings.getString("username", "");
        final String passwords = settings.getString("passwords", "");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.URL_Balance2), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {
                    Log.e("CHECK_JSON_BLOCK_CHAIN", s + "");
                    JSONObject jsonObject = new JSONObject(s);

                    getBoolean = jsonObject.getString("status");
                    messenger = jsonObject.getString("msg");
                    data = jsonObject.getString("data");
                    if (getBoolean.equals("200")) {
                        Toast.makeText(Navigation_Bar.this, messenger, Toast.LENGTH_SHORT).show();
                        JSONObject obj = new JSONObject(data);
                        ETHBalance = obj.getString("ETHBalance");
                        if (ETHBalance.length()>7){
                            String[] separated = ETHBalance.split("\\.");
//                        Log.e("999999999",separated.length+"");
                            Log.e("999999999",ETHBalance);
                            String ETH = separated[1].substring(0,5);
                            String ETH_LTR = separated[0]+"."+ETH;
                            mTV_Ethereum.setText(ETH_LTR);
                            mSingleTon.setETH_Balance(ETH_LTR);
                        }

                        LTRBalance = obj.getString("LTRBalance");
                        Wallet = obj.getString("wallet_address");
                        Log.e("CHECK_JSON_BLOCK_CHAIN", ETHBalance + "\n" + LTRBalance);
//                        mTV_Ethereum.setText(ETHBalance);
                        mTV_LTR.setText(LTRBalance);

                        mSingleTon.setLTR_Balance(LTRBalance);
                        mSingleTon.setWallet_Finish(Wallet);
                        Log.e("CHECK_INFO", mList.size() + "");
                    } else {

                        Log.e("CHECK_LOG", messenger);
                        JSONObject oj = new JSONObject(messenger);
                        String email = oj.getString("email");

                        Toast.makeText(Navigation_Bar.this, email, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("ERRROS_JSON", "" + e);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("ERROR_RESPONE_LICHPHAT", ":" + volleyError);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", username);
                params.put("password", passwords);
                params.put("apikey", "NzE0MjM4ZGEtODZiMy00MDQ2LTliOTctYzgxN2RhYTU2N2Iw");
//                    params.put("tel", mySingleTon.getPhone_Account());

//                Log.e("PAGE", "respones_" + mysingleton.getPageVideoCate());

                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (myToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.mTV_HOME:
                this.finish();
                startActivity(getIntent());
                break;
            case R.id.mTV_LOTTERY_RUSULT:
                intent = new Intent(Navigation_Bar.this, Activity_Result.class);
                startActivity(intent);
//                mFragmentManager = getSupportFragmentManager();
//                fragTransaction = mFragmentManager.beginTransaction();
//                mFragment_ReSult = Fragment_Result.newInstance("", "");
//                fragTransaction.replace(mll.getId(), mFragment_ReSult);
//                fragTransaction.commit();

                break;
            case R.id.mTV_mTV_BuyLTR_Token:
                settings = getSharedPreferences("88", MODE_PRIVATE);
                String username = settings.getString("username", "");
                String passwords = settings.getString("passwords", "");
                if (username.length() > 0) {
                    intent = new Intent(Navigation_Bar.this, Activity_BuyToken.class);
                    startActivity(intent);
//                    fragmentManager = getSupportFragmentManager();
//                    fragTransaction = fragmentManager.beginTransaction();
//                    Buy_LTR_Token fragment2 = Buy_LTR_Token.newInstance("", "");
//                    Bundle bundle = new Bundle();
//                    fragment2.setArguments(bundle);
//                    fragTransaction.replace(R.id.ln_fr, fragment2);
//                    fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                    fragTransaction.commit();
                } else {
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.mTV_TRANSATION:
                settings = getSharedPreferences("88", MODE_PRIVATE);
                String username1 = settings.getString("username", "");
                String passwords1 = settings.getString("passwords", "");
                Log.e("SIZE_SHARE", username1.length() + "");
                if (username1.length() > 0) {
                    intent = new Intent(this,Activity_Transaction.class);
                    startActivity(intent);
//                    mDrawer.setDrawerLockMode(DrawerLayout.ACCESSIBILITY_LIVE_REGION_NONE);
//                    fragmentManager = getSupportFragmentManager();
//                    fragTransaction = fragmentManager.beginTransaction();
//                    Fragment_Transaction Fragment_2 = Fragment_Transaction.newInstance("", "");
//                    Bundle bundle1 = new Bundle();
//                    Fragment_2.setArguments(bundle1);
//                    fragTransaction.replace(R.id.ln_fr, Fragment_2);
//                    fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                    fragTransaction.commit();
                } else {
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.mTV_SIGN_IN:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.mTV_SIGN_UP:
                Intent intent1 = new Intent(this, Signin_Activity.class);
                startActivity(intent1);
                break;
            case R.id.my_account:

                clickcount = clickcount + 1;
                if (clickcount % 2 == 0) {
                    mListView.setVisibility(View.GONE);
                } else {
                    mListView.setVisibility(View.VISIBLE);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mListView.setLayoutManager(mLayoutManager);
                    mList = new ArrayList<>();
                    mList.add(new Gender("My Wallet", ""));
                    mList.add(new Gender("Buy LTR Token", ""));
                    mList.add(new Gender("Profile", ""));
                    mList.add(new Gender("2FA Verification", ""));
                    mList.add(new Gender("Sign Out", ""));
                    AdapterAccount mAdapter = new AdapterAccount(Navigation_Bar.this, mList);
                    mListView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
