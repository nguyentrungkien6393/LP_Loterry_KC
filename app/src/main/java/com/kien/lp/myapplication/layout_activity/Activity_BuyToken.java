package com.kien.lp.myapplication.layout_activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.base.Create_Class;
import com.kien.lp.myapplication.mysingleton.MySingleTon;
import com.kien.lp.myapplication.singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Activity_BuyToken extends AppCompatActivity  implements View.OnClickListener{
    private TextView mTV_adress_wallet, mTV_wallet_LTR, mTV_wallet_ETH, mTV_Ty_Gia, mTV_ETH_Change;
    private EditText mEDT_Buy_LTR;
    private MySingleTon mySingleTon = MySingleTon.getInstance();
    private Create_Class mCreate_class;
    private Button mButton_BuyLTR;
    private TextView mTV_ETH_TxHash, mTV_LTR_TxHash;
    private SharedPreferences settings;
    private String username, passwords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_buytoken);
        getSupportActionBar().hide();
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

    private void addControll() {
        mTV_adress_wallet = findViewById(R.id.mTV_adress_wallet);
        mTV_wallet_LTR = findViewById(R.id.mTV_wallet_LTR);
        mTV_wallet_ETH = findViewById(R.id.mTV_wallet_ETH);
        mTV_Ty_Gia = findViewById(R.id.mTV_Ty_Gia);
        mTV_ETH_Change = findViewById(R.id.mTV_ETH_Change);
        mEDT_Buy_LTR = findViewById(R.id.mEDT_Buy_LTR);
        mButton_BuyLTR = findViewById(R.id.mButton_BuyLTR);
        mButton_BuyLTR = findViewById(R.id.mButton_BuyLTR);
        mButton_BuyLTR.setText("Mua Ngay");
        mButton_BuyLTR.setBackgroundResource(R.drawable.bogoc_background_cam);
        mButton_BuyLTR.setOnClickListener(this);
        settings =getSharedPreferences("88", MODE_PRIVATE);
        username = settings.getString("username", "");
        passwords = settings.getString("passwords", "");
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
    private void show_dialog(final String message, final String Txhash, final String message2, final String Txhash2) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
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
                    Toast.makeText(this, "MUa ít nhất 1000 LTR", Toast.LENGTH_SHORT).show();
                }else {
                    double t = Double.parseDouble(mEDT_Buy_LTR.getText().toString());
                    if (t<1000.0){
                        Toast.makeText(this, "Vui Lòng Mua trên 1000 LTR", Toast.LENGTH_SHORT).show();
                    }else {
                        double d = Double.parseDouble(mEDT_Buy_LTR.getText().toString());
                        if ((d / (mySingleTon.getPrice_usd() / 0.001)) > Double.parseDouble(mySingleTon.getETH_Balance())){
                            Toast.makeText(this, "Không đủ Tiền Vui Lòng Thử Lại", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Activity_BuyToken.this, "Lỗi mua bán xin vui lòng thử lại", Toast.LENGTH_SHORT).show();
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
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

}
