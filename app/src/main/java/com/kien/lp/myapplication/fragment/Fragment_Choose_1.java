package com.kien.lp.myapplication.fragment;


import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.Adapter_GameInfo;
import com.kien.lp.myapplication.model.GameInfo;
import com.kien.lp.myapplication.singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Choose_1 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerview_Choose;
    Adapter_GameInfo adapter;
    List<GameInfo> mList;

    public Fragment_Choose_1() {
        // Required empty public constructor
    }


    public static Fragment_Choose_1 newInstance(String param1, String param2) {
        Fragment_Choose_1 mFragment = new Fragment_Choose_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        mFragment.setArguments(args);

        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_1, container, false);

        addControll(view);
//        loadDataGameInfo();

//        Log.e("SIZE",mList.size()+"");

        return view;
    }

    private void addControll(View view) {
        mList = new ArrayList<>();
        mList.add(new GameInfo("1",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        ));
        mRecyclerview_Choose = view.findViewById(R.id.mRecyclerview_Choose);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerview_Choose.setLayoutManager(mLayoutManager);
        adapter = new Adapter_GameInfo(mList,getActivity());
        mRecyclerview_Choose.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private void loadDataGameInfo() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, getString(R.string.URL_Gamesinfo), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("CHECK_JSON_BLOCK_CHAIN", s + "");
                try {

                    JSONArray array = new JSONArray(s);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        String id = obj.getString("id");
                        String name = obj.getString("name");
                        String image = obj.getString("image");
                        String ticket_price = obj.getString("ticket_price");
                        String numbers = obj.getString("numbers");
                        String min_number = obj.getString("min_number");
                        String max_number = obj.getString("max_number");
                        String has_special_number = obj.getString("has_special_number");
                        String min_special = obj.getString("min_special");
                        String max_special = obj.getString("max_special");
                        String draw_time = obj.getString("draw_time");
                        String description = obj.getString("description");
                        String special_numbers = obj.getString("special_numbers");
                        mList.add(new GameInfo(id,
                                name,
                                image,
                                ticket_price,
                                numbers,
                                min_number,
                                max_number,
                                has_special_number,
                                min_special,
                                max_special,
                                draw_time,
                                description,
                                special_numbers
                        ));
                    }
                    adapter = new Adapter_GameInfo(mList,getActivity());
                    mRecyclerview_Choose.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    Log.e("SIZE", mList.size() + "");

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
        });
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


    }


}
