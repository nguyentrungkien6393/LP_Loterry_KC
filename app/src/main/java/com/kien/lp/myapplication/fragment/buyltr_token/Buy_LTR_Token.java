package com.kien.lp.myapplication.fragment.buyltr_token;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;
import com.kien.lp.myapplication.MainActivity;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.base.Create_Class;
import com.kien.lp.myapplication.fragment.wallet.Fragment_Wallet;
import com.kien.lp.myapplication.mysingleton.MySingleTon;
import com.kien.lp.myapplication.singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Buy_LTR_Token extends Fragment implements View.OnClickListener {
    private TextView mTV_adress_wallet, mTV_wallet_LTR, mTV_wallet_ETH, mTV_Ty_Gia, mTV_ETH_Change;
    private EditText mEDT_Buy_LTR;
    private MySingleTon mySingleTon = MySingleTon.getInstance();
    private Create_Class mCreate_class;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button mButton_BuyLTR;
    private TextView mTV_ETH_TxHash, mTV_LTR_TxHash;
    private SharedPreferences settings;
    private String username, passwords;
    public Buy_LTR_Token() {
        // Required empty public constructor
    }

    public static Buy_LTR_Token newInstance(String param1, String param2) {
        Buy_LTR_Token mFragment = new Buy_LTR_Token();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        mFragment.setArguments(args);
//        mRow = args.getString(ARG_PARAM2);

        return mFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buy__ltr__token, container, false);


        addControll(view);
        addEvent();
        return view;
    }

    private void addEvent() {
        mTV_adress_wallet.setText(mySingleTon.getWallet_Finish());
        mTV_wallet_LTR.setText(mySingleTon.getLTR_Balance());
        mTV_wallet_ETH.setText(mySingleTon.getETH_Balance());
        mTV_Ty_Gia.setText((mySingleTon.getPrice_usd() / 0.001) + "");
        mEDT_Buy_LTR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    double d = Double.parseDouble(s.toString());
//                    DecimalFormat formatter = new DecimalFormat("#,###.0000000000");
//                    String formatted = formatter.format((d/(mySingleTon.getPrice_usd()/0.001)));
                    if (((d / (mySingleTon.getPrice_usd() / 0.001))) < 1.0) {
                        DecimalFormat formatter = new DecimalFormat("#,###.0000000000");
                        String formatted = formatter.format((d / (mySingleTon.getPrice_usd() / 0.001)));
                        mTV_ETH_Change.setText("0" + formatted);
                        Log.e("xxxxxxxxxx", "onTextChanged: " + formatted);
                    } else {
                        DecimalFormat formatter = new DecimalFormat("#,###.000000");
                        String formatted = formatter.format((d / (mySingleTon.getPrice_usd() / 0.001)));
                        if ((d / (mySingleTon.getPrice_usd() / 0.001)) > Double.parseDouble(mySingleTon.getETH_Balance())) {
                            mTV_ETH_Change.setText(formatted);
                            mTV_ETH_Change.setTextColor(Color.parseColor("#EE0000"));
                        } else {
                            mTV_ETH_Change.setText(formatted);
                            mTV_ETH_Change.setTextColor(Color.parseColor("#000000"));
                        }
                        Log.e("yyyyyyyyyy", "onTextChanged: " + formatted);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void addControll(View view) {
        mTV_adress_wallet = view.findViewById(R.id.mTV_adress_wallet);
        mTV_wallet_LTR = view.findViewById(R.id.mTV_wallet_LTR);
        mTV_wallet_ETH = view.findViewById(R.id.mTV_wallet_ETH);
        mTV_Ty_Gia = view.findViewById(R.id.mTV_Ty_Gia);
        mTV_ETH_Change = view.findViewById(R.id.mTV_ETH_Change);
        mEDT_Buy_LTR = view.findViewById(R.id.mEDT_Buy_LTR);
        mButton_BuyLTR = view.findViewById(R.id.mButton_BuyLTR);
        mButton_BuyLTR = view.findViewById(R.id.mButton_BuyLTR);
        mButton_BuyLTR.setText("Mua Ngay");
        mButton_BuyLTR.setBackgroundResource(R.drawable.bogoc_background_cam);
        mButton_BuyLTR.setOnClickListener(this);
        settings = getActivity().getSharedPreferences("88", getActivity().MODE_PRIVATE);
         username = settings.getString("username", "");
         passwords = settings.getString("passwords", "");
    }

    private void API_Buy_LTR() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.URL_Buy_LTR), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("CHECK_JSON_BLOCK_CHAIN", s + "");
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String status = jsonObject.getString("status");
                    String data = jsonObject.getString("data");
                    if (status.equals("200")) {
                        JSONArray arr = new JSONArray(data);
                        JSONObject object1 = arr.getJSONObject(0);
                        String message = object1.getString("message");
                        String TxHash = object1.getString("TxHash");
                        JSONObject object2 = arr.getJSONObject(1);
                        String message2 = object2.getString("message");
                        String TxHash2 = object2.getString("TxHash");
                        show_dialog(message, TxHash, message2, TxHash2);
                        mButton_BuyLTR.setBackgroundResource(R.drawable.bogoc_background_cam);
                        mButton_BuyLTR.setText("Mua Ngay");
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
                Toast.makeText(getActivity(), "Lỗi mua bán xin vui lòng thử lại", Toast.LENGTH_SHORT).show();
                mButton_BuyLTR.setText("Mua Ngay");
                mButton_BuyLTR.setBackgroundResource(R.drawable.bogoc_background_cam);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", username);
                params.put("password",passwords);
                params.put("apikey", "NzE0MjM4ZGEtODZiMy00MDQ2LTliOTctYzgxN2RhYTU2N2Iw");
                params.put("ltr_tobuy", mEDT_Buy_LTR.getText().toString());
                return params;
            }
        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


    }

    private void show_dialog(final String message, final String Txhash, final String message2, final String Txhash2) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_buy_ltr, null);
        mTV_ETH_TxHash = dialogView.findViewById(R.id.mTV_ETH_TxHash);
        mTV_LTR_TxHash = dialogView.findViewById(R.id.mTV_LTR_TxHash);
        mTV_ETH_TxHash.setText(Txhash);
        mTV_LTR_TxHash.setText(Txhash2);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        mButton_BuyLTR.setText("Mua Ngay");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mButton_BuyLTR:
                if (mEDT_Buy_LTR.getText().toString().length()==0){
                    Toast.makeText(getActivity(), "MUa ít nhất 1000 LTR", Toast.LENGTH_SHORT).show();
                }else {
                    double t = Double.parseDouble(mEDT_Buy_LTR.getText().toString());
                    if (t<1000.0){
                        Toast.makeText(getActivity(), "Vui Lòng Mua trên 1000 LTR", Toast.LENGTH_SHORT).show();
                    }else {
                        double d = Double.parseDouble(mEDT_Buy_LTR.getText().toString());
                        if ((d / (mySingleTon.getPrice_usd() / 0.001)) > Double.parseDouble(mySingleTon.getETH_Balance())){
                            Toast.makeText(getActivity(), "Không đủ Tiền Vui Lòng Thử Lại", Toast.LENGTH_SHORT).show();
                        }else {
                            mButton_BuyLTR.setText("Đang Mua LTR...");
                            mButton_BuyLTR.setBackgroundResource(R.drawable.bogoc);
                            API_Buy_LTR();
                        }
                    }
                }

                break;
        }
    }
}
