package com.kien.lp.myapplication.layout_activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kien.lp.myapplication.MainActivity;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.mysingleton.MySingleTon;

import org.json.JSONException;
import org.json.JSONObject;

public class Buy_Ticket extends AppCompatActivity implements View.OnClickListener{
    TextView mTV_LTR,mTV_Total,mTV_ETH;
    EditText mEDT_password;
    Button mBTN_BuyTicket;
    LinearLayout exit_Activity;
    MySingleTon mySingleTon = MySingleTon.getInstance();
    int ticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy__ticket);
        getSupportActionBar().hide();
        addControll();
        addEvent();
    }

    private void addEvent() {
        mBTN_BuyTicket.setOnClickListener(this);
        exit_Activity.setOnClickListener(this);
        mTV_Total.setText((mySingleTon.getmList_View_Number().size()*2000)+" LTR");
        mTV_LTR.setText(mySingleTon.getLTR_Balance());
        mTV_ETH.setText(mySingleTon.getETH_Balance());
    }

    private void addControll() {

        mTV_LTR = findViewById(R.id.mTV_LTR);
        exit_Activity = findViewById(R.id.exit_Activity);
        mTV_Total = findViewById(R.id.mTV_Total);
        mTV_ETH = findViewById(R.id.mTV_ETH);
        mEDT_password = findViewById(R.id.mEDT_password);
        mBTN_BuyTicket = findViewById(R.id.mBTN_BuyTicket);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mBTN_BuyTicket:
                SharedPreferences settings;
                settings = getSharedPreferences("88", MODE_PRIVATE);
                String username = settings.getString("username", "");
                String passwords = settings.getString("passwords", "");
                if (mEDT_password.getText().toString().equals(passwords)){
                    showDialogBuy_Ticket();
                }else {
                    Toast.makeText(this, "Sai Passưords", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.exit_Activity:
                this.finish();
                break;
        }
    }

    private void showDialogBuy_Ticket() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_mua_ve, null);
        Button mButton_Yes = dialogView.findViewById(R.id.mButton_Yes);
        Button mButton_No = dialogView.findViewById(R.id.mButton_No);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        mButton_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        mButton_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                pushJSON();
            }
        });
    }




    private void pushJSON(){
        final String json = "[{\n" +
                "        \"email\":\"kiennguyentrung6393@gmail.com\",\n" +
                "        \"password\":\"longphuong123\",\n" +
                "        \"apikey\":\"NzE0MjM4ZGEtODZiMy00MDQ2LTliOTctYzgxN2RhYTU2N2Iw\"\n" +
                "},{\n" +
                "        \"game_id\":1,\n" +
                "        \"user_id\":26,\n" +
                "        \"numbers\":\"1 2 3 4 5\",\n" +
                "        \"special_numbers\":\"1 2\",\n" +
                "        \"price\":2000,\n" +
                "        \"status\":1,\n" +
                "        \"txhash\":\"\"\n" +
                "        }]";
        StringRequest myReq = new StringRequest(Request.Method.POST,
                getString(R.string.URL_Buy_Tickets),
                createMyReqSuccessListener(),
                createMyReqErrorListener()) {

            @Override
            public byte[] getBody() throws com.android.volley.AuthFailureError {
                return json.getBytes();
            };
            public String getBodyContentType()
            {
                return "application/json; charset=utf-8";
            }

        };
        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(myReq);
    }
    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("____________","Error data from server - "+error);
                Toast.makeText(getApplicationContext(), "Buy Error", Toast.LENGTH_SHORT).show();
            }
        };
    }
    private Response.Listener<String> createMyReqSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("+++++++++++","Parse data from server - "+response);
                Toast.makeText(getApplicationContext(), "Buy Successfull", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject object = new JSONObject(response);
                    String status = object.getString("status");
                    String msg = object.getString("msg");
                    String data = object.getString("data");
                    JSONObject obj = new JSONObject(data);
                    String txHash = obj.getString("TxHash");
                    if (status.equals("200")){
                        showDialog_Checkout(txHash);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void showDialog_Checkout(String data){
        TextView mTV_TxHash;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_checkout, null);
        mTV_TxHash = dialogView.findViewById(R.id.mTV_TxHash);
        mTV_TxHash.setText(data);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
