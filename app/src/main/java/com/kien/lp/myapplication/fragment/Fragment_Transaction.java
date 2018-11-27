package com.kien.lp.myapplication.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.kien.lp.myapplication.adapter.transaction.Adapter_Transaction;
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

public class Fragment_Transaction extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerview_Transaction;
    private List<Model_Transaction> model_transactionList;
    private Adapter_Transaction1 mAdapter_TranSaction;
    SharedPreferences settings ;
//    private String

    public Fragment_Transaction() {
        // Required empty public constructor
    }

    public static Fragment_Transaction newInstance(String param1, String param2) {
        Fragment_Transaction mFragment = new Fragment_Transaction();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        mFragment.setArguments(args);
        return mFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__transaction, container, false);
        addConroll(view);
        addEvent();
        return view;
    }

    private void addEvent() {
       settings = getActivity().getSharedPreferences("88",
                getActivity().MODE_PRIVATE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.URL_TRANSACTION), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("AAAAAAAAAAA", s + "");
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String data = jsonObject.getString("data");
                    String msg = jsonObject.getString("msg");
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray = new JSONArray(data);
                    for (int i =0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String game_id   = object.getString("game_id");
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
                    mAdapter_TranSaction = new Adapter_Transaction1(getActivity(),model_transactionList);
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
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }

    private void addConroll(View view) {
        mRecyclerview_Transaction = view.findViewById(R.id.mRecyclerview_Transaction);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview_Transaction.setLayoutManager(layoutManager);
        model_transactionList = new ArrayList<>();
    }
}
