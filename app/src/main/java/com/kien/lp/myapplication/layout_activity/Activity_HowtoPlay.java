package com.kien.lp.myapplication.layout_activity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kien.lp.myapplication.MainActivity;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.Adapter_Table_How_To_Play;
import com.kien.lp.myapplication.adapter.buyticket.Ticket;
import com.kien.lp.myapplication.fragment.View_Number_Choose;
import com.kien.lp.myapplication.model.Item_View_Number;
import com.kien.lp.myapplication.mysingleton.MySingleTon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Activity_HowtoPlay extends AppCompatActivity implements View.OnClickListener {
    RecyclerView mRecyclerview_How_To_Play;
    List<Ticket> mListString;
    Adapter_Table_How_To_Play mAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView mTV_3, mTV_5, mTV_7, mTV_10, mTV_15, mTV_20, mTV_25, mContent_UnderLine;
    MySingleTon mySingleTon = MySingleTon.getInstance();
    Button mButton_Click_Choose_Ramdom, mButton_Buy_Ticket;
    List<Item_View_Number> mList_View_Number;
    private ImageView ic_action_bar_back;
    private LinearLayout ln_view_number;
    private FragmentTransaction fragTransaction;
    private FragmentManager fragmentManager;
    List<String> listNumber69 = new ArrayList<>();
    List<String> listNumber26 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto_play);
        getSupportActionBar().hide();
//        addActionBar();
        addControll();
        addView3Ticket();
        mySingleTon.getmHashMap().clear();

    }



    private void addActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FECC45")));


    }


    @Override
    protected void onStart() {
        super.onStart();

        mList_View_Number = new ArrayList<>();
        if (mySingleTon.getmHashMap().size() > 0) {
            Log.e("____onCreateView:____ ", mySingleTon.getmHashMap().size() + "");
            for (int key : mySingleTon.getmHashMap().keySet()) {
                System.out.println(key);
                Ticket tk1 = mySingleTon.getmHashMap().get(key);
                listNumber69 = new ArrayList<>();
                for (int position = 0; position < 69; position++) {
                    if (tk1.getCell69().get(position).isSeleted() == true) {
                        Log.e("__________________", "CMNR");
                        System.out.println("^^^^^^^^^^^^^^^^^^^^^" + (tk1.getCell69().get(position).getNumber() + 1) + "^^^^^^^^^^^^^^^^^^^");
                        listNumber69.add((tk1.getCell69().get(position).getNumber() + 1) + "");
                    }
                }
                listNumber26 = new ArrayList<>();
                StringBuilder builder = new StringBuilder();
                for(String s : listNumber69) {
                    builder.append(s+" ");
                }
                String str = builder.toString();
                Log.e("__________________", str);
                for (int position = 0; position < 26; position++) {
                    if (tk1.getCell26().get(position).isSeleted() == true) {
                        Log.e("__________________", "CMNR");
                        System.out.println("^^^^^^^^^^^^^^^^^^^^^" + (tk1.getCell26().get(position).getNumber() + 1) + "^^^^^^^^^^^^^^^^^^^");
                        listNumber26.add((tk1.getCell69().get(position).getNumber() + 1) + "");
                    }
                }

                StringBuilder builder1 = new StringBuilder();
                for(String s1 : listNumber26) {
                    builder1.append(s1+" ");
                }
                String str1 = builder1.toString();
                Log.e("**************",str1);
                mList_View_Number.add(new Item_View_Number(str,str1));
            }

            mySingleTon.setmList_View_Number(mList_View_Number);
            mListString = new ArrayList<>();
            mListString = mySingleTon.getmList_Ticket();
            Log.e("*********************",mySingleTon.getmList_Ticket().size()+"");
            mAdapter = new Adapter_Table_How_To_Play(mListString, Activity_HowtoPlay.this, 3, 0);
            mRecyclerview_How_To_Play.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            mButton_Buy_Ticket.setVisibility(View.VISIBLE);
        }
        else {
            mListString = new ArrayList<>();
            mListString.add(new Ticket(1,false));
            mListString.add(new Ticket(1,false));
            mListString.add(new Ticket(1,false));
            mAdapter = new Adapter_Table_How_To_Play(mListString, Activity_HowtoPlay.this, 3, 0);
            mRecyclerview_How_To_Play.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
//        addView3Ticket();
//        addControll();


    }

    private void addView3Ticket() {
        mTV_3.setBackgroundResource(R.drawable.bogoc_danhso);
        mTV_3.setTextColor(Color.parseColor("#FFFFFF"));
        mTV_5.setBackgroundResource(R.drawable.bogoc_xanh);
        mTV_5.setTextColor(Color.parseColor("#3333cc"));
        mTV_7.setBackgroundResource(R.drawable.bogoc_xanh);
        mTV_7.setTextColor(Color.parseColor("#3333cc"));
        mTV_10.setBackgroundResource(R.drawable.bogoc_xanh);
        mTV_10.setTextColor(Color.parseColor("#3333cc"));
        mTV_15.setBackgroundResource(R.drawable.bogoc_xanh);
        mTV_15.setTextColor(Color.parseColor("#3333cc"));
        mTV_20.setBackgroundResource(R.drawable.bogoc_xanh);
        mTV_20.setTextColor(Color.parseColor("#3333cc"));
        mTV_25.setBackgroundResource(R.drawable.bogoc_xanh);
        mTV_25.setTextColor(Color.parseColor("#3333cc"));
        mySingleTon.setChoose_number(3);
        addEvent(3, 3);
    }

    private void addEvent(final int a, final int row) {

        mRecyclerview_How_To_Play.setLayoutManager(new GridLayoutManager(this, row));
        mListString = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            mListString.add(new Ticket((i + 1), false));
        }
//        mListString.add(new Ticket((1),false));
//        mListString.add(new Ticket((2),true));
//        mListString.add(new Ticket((3),false));
        mAdapter = new Adapter_Table_How_To_Play(mListString, this, row, 0);
        mRecyclerview_How_To_Play.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mButton_Click_Choose_Ramdom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList_View_Number = new ArrayList<>();
                Set<Integer> allNumbers = new HashSet<>();
                for (int i = 0; i < a; i++) {
                    Set<Integer> set = new HashSet<>();
                    while (set.size() < 5) {
                        int random = (int) (Math.random() * 69 + 1);
                        if (allNumbers.add(random)) {
                            set.add(random);
                        }
                        System.out.println("__________" + random);
                    }
                    Integer[] array = set.toArray(new Integer[]{});
                    Arrays.sort(array);
                    Random ram = new Random();
                    int randomNum = ram.nextInt((10 - 1) + 1) + 1;
                    mList_View_Number.add(new Item_View_Number(array[0] + " " + array[1] + " " + array[2] + " " + array[3] + " " + array[4], randomNum + ""));
                }
                mySingleTon.setmList_View_Number(mList_View_Number);
                mAdapter = new Adapter_Table_How_To_Play(mListString, Activity_HowtoPlay.this, row, 1);
                mRecyclerview_How_To_Play.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //here call the second method
                        mButton_Buy_Ticket.setVisibility(View.VISIBLE);
                        ln_view_number.setVisibility(View.VISIBLE);
                    }
                }, 1500);
            }
        });
    }

    private void addControll() {
        //AddControll_ActionBar ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
        ic_action_bar_back = findViewById(R.id.ic_action_bar_back);
        ic_action_bar_back.setOnClickListener(this);
        mRecyclerview_How_To_Play = findViewById(R.id.mRecyclerview_How_To_Play);
        mTV_3 = findViewById(R.id.mTV_3);
        mTV_3.setOnClickListener(this);
        mTV_5 = findViewById(R.id.mTV_5);
        mTV_5.setOnClickListener(this);
        mTV_7 = findViewById(R.id.mTV_7);
        mTV_7.setOnClickListener(this);
        mTV_10 = findViewById(R.id.mTV_10);
        mTV_10.setOnClickListener(this);
        mTV_15 = findViewById(R.id.mTV_15);
        mTV_15.setOnClickListener(this);
        mTV_20 = findViewById(R.id.mTV_20);
        mTV_20.setOnClickListener(this);
        mTV_25 = findViewById(R.id.mTV_25);
        mContent_UnderLine = findViewById(R.id.mContent_UnderLine);
        SpannableString content = new SpannableString(getString(R.string.view_your_number));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        mContent_UnderLine.setText(content);
        mContent_UnderLine.setTextColor(Color.parseColor("#2554C7"));
        ln_view_number = findViewById(R.id.ln_view_number);
        mButton_Buy_Ticket = findViewById(R.id.mButton_Buy_Ticket);
        mButton_Buy_Ticket.setOnClickListener(this);
        mTV_25.setOnClickListener(this);
        ln_view_number.setOnClickListener(this);
        mButton_Click_Choose_Ramdom = findViewById(R.id.mButton_Click_Choose_Ramdom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTV_3:
                mTV_3.setBackgroundResource(R.drawable.bogoc_danhso);
                mTV_3.setTextColor(Color.parseColor("#FFFFFF"));
                mTV_5.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_5.setTextColor(Color.parseColor("#3333cc"));
                mTV_7.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_7.setTextColor(Color.parseColor("#3333cc"));
                mTV_10.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_10.setTextColor(Color.parseColor("#3333cc"));
                mTV_15.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_15.setTextColor(Color.parseColor("#3333cc"));
                mTV_20.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_20.setTextColor(Color.parseColor("#3333cc"));
                mTV_25.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_25.setTextColor(Color.parseColor("#3333cc"));
                mySingleTon.setChoose_number(3);
                addEvent(3, 3);
                break;
            case R.id.mTV_5:
                mTV_5.setBackgroundResource(R.drawable.bogoc_danhso);
                mTV_5.setTextColor(Color.parseColor("#FFFFFF"));
                mTV_3.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_3.setTextColor(Color.parseColor("#3333cc"));
                mTV_7.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_7.setTextColor(Color.parseColor("#3333cc"));
                mTV_10.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_10.setTextColor(Color.parseColor("#3333cc"));
                mTV_15.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_15.setTextColor(Color.parseColor("#3333cc"));
                mTV_20.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_20.setTextColor(Color.parseColor("#3333cc"));
                mTV_25.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_25.setTextColor(Color.parseColor("#3333cc"));
                mySingleTon.setChoose_number(5);
                addEvent(5, 5);
                break;
            case R.id.mTV_7:
                mTV_7.setBackgroundResource(R.drawable.bogoc_danhso);
                mTV_7.setTextColor(Color.parseColor("#FFFFFF"));
                mTV_3.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_3.setTextColor(Color.parseColor("#3333cc"));
                mTV_5.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_5.setTextColor(Color.parseColor("#3333cc"));
                mTV_10.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_10.setTextColor(Color.parseColor("#3333cc"));
                mTV_15.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_15.setTextColor(Color.parseColor("#3333cc"));
                mTV_20.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_20.setTextColor(Color.parseColor("#3333cc"));
                mTV_25.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_25.setTextColor(Color.parseColor("#3333cc"));
                mySingleTon.setChoose_number(7);
                addEvent(7, 4);
                break;
            case R.id.mTV_10:
                mTV_10.setBackgroundResource(R.drawable.bogoc_danhso);
                mTV_10.setTextColor(Color.parseColor("#FFFFFF"));
                mTV_3.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_3.setTextColor(Color.parseColor("#3333cc"));
                mTV_5.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_5.setTextColor(Color.parseColor("#3333cc"));
                mTV_7.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_7.setTextColor(Color.parseColor("#3333cc"));
                mTV_15.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_15.setTextColor(Color.parseColor("#3333cc"));
                mTV_20.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_20.setTextColor(Color.parseColor("#3333cc"));
                mTV_25.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_25.setTextColor(Color.parseColor("#3333cc"));
                mySingleTon.setChoose_number(10);
                addEvent(10, 5);
                break;
            case R.id.mTV_15:
                mTV_15.setBackgroundResource(R.drawable.bogoc_danhso);
                mTV_15.setTextColor(Color.parseColor("#FFFFFF"));
                mTV_3.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_3.setTextColor(Color.parseColor("#3333cc"));
                mTV_5.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_5.setTextColor(Color.parseColor("#3333cc"));
                mTV_7.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_7.setTextColor(Color.parseColor("#3333cc"));
                mTV_10.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_10.setTextColor(Color.parseColor("#3333cc"));
                mTV_20.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_20.setTextColor(Color.parseColor("#3333cc"));
                mTV_25.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_25.setTextColor(Color.parseColor("#3333cc"));
                mySingleTon.setChoose_number(15);
                addEvent(15, 5);
                break;
            case R.id.mTV_20:
                mTV_20.setBackgroundResource(R.drawable.bogoc_danhso);
                mTV_20.setTextColor(Color.parseColor("#FFFFFF"));
                mTV_3.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_3.setTextColor(Color.parseColor("#3333cc"));
                mTV_5.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_5.setTextColor(Color.parseColor("#3333cc"));
                mTV_7.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_7.setTextColor(Color.parseColor("#3333cc"));
                mTV_10.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_10.setTextColor(Color.parseColor("#3333cc"));
                mTV_15.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_15.setTextColor(Color.parseColor("#3333cc"));
                mTV_25.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_25.setTextColor(Color.parseColor("#3333cc"));
                mySingleTon.setChoose_number(20);
                addEvent(20, 5);
                break;
            case R.id.mTV_25:
                mTV_25.setBackgroundResource(R.drawable.bogoc_danhso);
                mTV_25.setTextColor(Color.parseColor("#FFFFFF"));
                mTV_3.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_3.setTextColor(Color.parseColor("#3333cc"));
                mTV_5.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_5.setTextColor(Color.parseColor("#3333cc"));
                mTV_7.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_7.setTextColor(Color.parseColor("#3333cc"));
                mTV_10.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_10.setTextColor(Color.parseColor("#3333cc"));
                mTV_15.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_15.setTextColor(Color.parseColor("#3333cc"));
                mTV_20.setBackgroundResource(R.drawable.bogoc_xanh);
                mTV_20.setTextColor(Color.parseColor("#3333cc"));
                mySingleTon.setChoose_number(25);
                addEvent(25, 5);
                break;
            case R.id.mButton_Buy_Ticket:
//                Toast.makeText(getActivity(), "++++_____________++++", Toast.LENGTH_SHORT).show();
                SharedPreferences settings;
                settings = getSharedPreferences("88", MODE_PRIVATE);
                String username = settings.getString("username", "");
                String passwords = settings.getString("passwords", "");
                if (username.length() > 0) {
                    Log.e("*************",mySingleTon.getmList_View_Number().size()+"");
                    Intent intent = new Intent(this,Buy_Ticket.class);
                    startActivity(intent);
//                    startFragment();
                } else {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                Log.e("SHOW_JSON", getString(R.string.json));
//                pushJSON();
                break;
            case R.id.ln_view_number:
                Intent intent = new Intent(this, Activity_ViewNumber.class);
                startActivity(intent);
                break;
            case R.id.ic_action_bar_back:
                this.finish();
                break;
            default:
                break;
        }
    }
}
