
package com.kien.lp.myapplication.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.kien.lp.myapplication.MainActivity;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.Adapter_GameInfo;
import com.kien.lp.myapplication.adapter.Adapter_Table_How_To_Play;
import com.kien.lp.myapplication.adapter.OnClickListener_Choose;
import com.kien.lp.myapplication.adapter.OnItemClickListenner;
import com.kien.lp.myapplication.adapter.buyticket.Ticket;
import com.kien.lp.myapplication.model.GameInfo;
import com.kien.lp.myapplication.model.Number_Ticket;
import com.kien.lp.myapplication.mysingleton.MySingleTon;
import com.kien.lp.myapplication.singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Fragment_Choose_HowtoPlay extends Fragment implements View.OnClickListener {
    RecyclerView mRecyclerview_How_To_Play;
    List<Ticket> mListString;
    Adapter_Table_How_To_Play mAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView mTV_3, mTV_5, mTV_7, mTV_10, mTV_15, mTV_20, mTV_25,mContent_UnderLine;
    MySingleTon mySingleTon = MySingleTon.getInstance();
    Button mButton_Click_Choose_Ramdom,mButton_Buy_Ticket;
    private LinearLayout ln_view_number;
    private FragmentTransaction fragTransaction;
    private FragmentManager fragmentManager;
    List<String>listNumber69 = new ArrayList<>();
    List<String>listNumber26 = new ArrayList<>();
    public Fragment_Choose_HowtoPlay() {
        // Required empty public constructor
    }


    public static Fragment_Choose_HowtoPlay newInstance(String param1, String param2) {
        Fragment_Choose_HowtoPlay mFragment_1 = new Fragment_Choose_HowtoPlay();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        mFragment_1.setArguments(args);

        return mFragment_1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__choose__howto_play, container, false);
        addControll(view);
//        addEvent();
        addView3Ticket();
        if (mySingleTon.getmHashMap().size()>0){
            Log.e( "____onCreateView:____ ",mySingleTon.getmHashMap().size()+"");
            for ( int key : mySingleTon.getmHashMap().keySet() ) {
                System.out.println( key );
                Ticket tk1 = mySingleTon.getmHashMap().get(key);
                for (int position =0;position<69;position++){
                    if (tk1.getCell69().get(position).isSeleted()==true){
                        Log.e("Mảng Này Chạy Chuẩn ","CMNR");
                        System.out.println("^^^^^^^^^^^^^^^^^^^^^"+(tk1.getCell69().get(position).getNumber()+1)+"^^^^^^^^^^^^^^^^^^^");
                        listNumber69.add((tk1.getCell69().get(position).getNumber()+1)+"");
                        Log.e("SIZE_TICKET",listNumber69.size()+"");
                    }
                }
                for (int position =0;position<26;position++){
                    if (tk1.getCell26().get(position).isSeleted()==true){
                        Log.e("Mảng Chạy Chuẩn ","CMNR");
                        System.out.println("^^^^^^^^^^^^^^^^^^^^^"+(tk1.getCell26().get(position).getNumber()+1)+"^^^^^^^^^^^^^^^^^^^");
                        listNumber26.add((tk1.getCell69().get(position).getNumber()+1)+"");
                        Log.e("SIZE_TICKET",listNumber26.size()+"");
                    }
                }
            }
        }
        return view;
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


    private void addControll(View view) {
        mRecyclerview_How_To_Play = view.findViewById(R.id.mRecyclerview_How_To_Play);
        mTV_3 = view.findViewById(R.id.mTV_3);
        mTV_3.setOnClickListener(this);
        mTV_5 = view.findViewById(R.id.mTV_5);
        mTV_5.setOnClickListener(this);
        mTV_7 = view.findViewById(R.id.mTV_7);
        mTV_7.setOnClickListener(this);
        mTV_10 = view.findViewById(R.id.mTV_10);
        mTV_10.setOnClickListener(this);
        mTV_15 = view.findViewById(R.id.mTV_15);
        mTV_15.setOnClickListener(this);
        mTV_20 = view.findViewById(R.id.mTV_20);
        mTV_20.setOnClickListener(this);
        mTV_25 = view.findViewById(R.id.mTV_25);
        mContent_UnderLine = view.findViewById(R.id.mContent_UnderLine);
        SpannableString content = new SpannableString("view your number");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        mContent_UnderLine.setText(content);
        mContent_UnderLine.setTextColor(Color.parseColor("#2554C7"));
        ln_view_number = view.findViewById(R.id.ln_view_number);
        mButton_Buy_Ticket = view.findViewById(R.id.mButton_Buy_Ticket);
        mButton_Buy_Ticket.setOnClickListener(this);
        mTV_25.setOnClickListener(this);
        ln_view_number.setOnClickListener(this);
        mButton_Click_Choose_Ramdom = view.findViewById(R.id.mButton_Click_Choose_Ramdom);

    }

    private void addEvent(int a, final int row) {

        mRecyclerview_How_To_Play.setLayoutManager(new GridLayoutManager(getActivity(), row));
        mListString = new ArrayList<>();
        mListString.add(new Ticket(1,false));
        mListString.add(new Ticket(1,true));
        mListString.add(new Ticket(1,false));
        mListString.add(new Ticket(1,true));
        mListString.add(new Ticket(1,true));
        mListString.add(new Ticket(1,false));
        mListString.add(new Ticket(1,true));
        mListString.add(new Ticket(1,false));
//        for (int i = 0; i < a; i++) {
//            mListString.add(new Ticket((i+1),false));
//        }
        mAdapter = new Adapter_Table_How_To_Play(mListString, getActivity(), row, 0);
        mRecyclerview_How_To_Play.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mButton_Click_Choose_Ramdom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("XXXXXXX", "YYYYYYYYYy");
                mAdapter = new Adapter_Table_How_To_Play(mListString, getActivity(), row, 1);
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
                settings = getActivity().getSharedPreferences("88", getActivity().MODE_PRIVATE);
                String username = settings.getString("username", "");
                String passwords = settings.getString("passwords", "");
                if (username.length()>0){
                    startFragment();
                }else {
                    Intent intent = new Intent (getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                Log.e("SHOW_JSON",getString(R.string.json));
//                pushJSON();

                break;
            case R.id.ln_view_number:
                fragmentManager = getActivity().getSupportFragmentManager();
                fragTransaction = fragmentManager.beginTransaction();
                View_Number_Choose Fragment_2 = View_Number_Choose.newInstance("","");
                Bundle bundle = new Bundle();
                Fragment_2.setArguments(bundle);
                fragTransaction.replace(R.id.ln_fr, Fragment_2);
                fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTransaction.commit();
            default:
                break;
        }
    }

    private void startFragment() {
        fragmentManager = getActivity().getSupportFragmentManager();
        fragTransaction = fragmentManager.beginTransaction();
        Fragment_ThanhToan Fragment_2 = Fragment_ThanhToan.newInstance(1, 1);
        Bundle bundle = new Bundle();
        Fragment_2.setArguments(bundle);
        fragTransaction.replace(R.id.ln_fr, Fragment_2);
        fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragTransaction.commit();
    }
}
