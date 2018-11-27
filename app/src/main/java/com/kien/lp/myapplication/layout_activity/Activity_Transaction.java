package com.kien.lp.myapplication.layout_activity;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kien.lp.myapplication.MainActivity;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.transaction.Adapter_Transaction1;
import com.kien.lp.myapplication.model.Model_Transaction;
import com.kien.lp.myapplication.singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_Transaction extends AppCompatActivity {
    private RecyclerView mRecyclerview_Transaction;
    private List<Model_Transaction> model_transactionList;
    private Adapter_Transaction1 mAdapter_TranSaction;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        addControll();
        addEvent();
        addActionBar();
    }


    private void addActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FECC45")));


    }


    private void addEvent() {
        settings = getSharedPreferences("88",
                MODE_PRIVATE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.URL_TRANSACTION), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("AAAAAAAAAAA", s + "");
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String data = jsonObject.getString("data");
                    String msg = jsonObject.getString("msg");
                    Toast.makeText(Activity_Transaction.this, msg, Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray = new JSONArray(data);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String game_id = object.getString("game_id");
                        String user_id = object.getString("user_id");
                        String numbers = object.getString("numbers");
                        String status = object.getString("status");
                        String created_at = object.getString("created_at");
                        String updated_at = object.getString("updated_at");
                        String special_numbers = object.getString("special_numbers");
                        String price = object.getString("price");
                        String txhash = object.getString("txhash");
                        String img = object.getString("img");
                        model_transactionList.add(new Model_Transaction(
                                id,
                                game_id,
                                user_id,
                                numbers,
                                status,
                                created_at,
                                updated_at,
                                special_numbers,
                                price,
                                txhash,
                                img));
                    }
                    mAdapter_TranSaction = new Adapter_Transaction1(Activity_Transaction.this, model_transactionList);
                    mRecyclerview_Transaction.setAdapter(mAdapter_TranSaction);
                    mAdapter_TranSaction.notifyDataSetChanged();
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
                params.put("email", settings.getString("username", ""));
                params.put("password", settings.getString("passwords", ""));
                params.put("apikey", "NzE0MjM4ZGEtODZiMy00MDQ2LTliOTctYzgxN2RhYTU2N2Iw");
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    private void addControll() {
        mRecyclerview_Transaction = findViewById(R.id.mRecyclerview_Transaction);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview_Transaction.setLayoutManager(layoutManager);
        model_transactionList = new ArrayList<>();
    }
}
