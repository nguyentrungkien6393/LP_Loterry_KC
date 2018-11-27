package com.kien.lp.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kien.lp.myapplication.base.BaseActivity;
import com.kien.lp.myapplication.mysingleton.MySingleTon;
import com.kien.lp.myapplication.singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends BaseActivity implements View.OnClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {
    Button mBTN_Signin, mBTN_Login, mBTN_Authenticate_Success;
    TextView mTV_Error_Messenger, mTV_Error_Authorticate;
    LinearLayout mLinear_login, mLinear_Authenticator;
    EditText mEDT_Email_Login, mEDT_Password_Login;
    ImageView ic_action_bar_back;
    MySingleTon mySingleTon = MySingleTon.getInstance();
    private static final int REQUEST_WRITE_PERMISSION = 786;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        hideStatusBar();
        mBTN_Login = findViewById(R.id.mBTN_Login);
        mBTN_Signin = findViewById(R.id.mBTN_Signin);
        mBTN_Authenticate_Success = findViewById(R.id.mBTN_Authenticate_Success);
        mTV_Error_Messenger = findViewById(R.id.mTV_Error_Messenger);
        mTV_Error_Authorticate = findViewById(R.id.mTV_Error_Authorticate);
        mLinear_Authenticator = findViewById(R.id.mLinear_Authenticate);
        mLinear_login = findViewById(R.id.mLinear_Login);
        mEDT_Email_Login = findViewById(R.id.mEDT_Email_Login);
        mEDT_Password_Login = findViewById(R.id.mEDT_Password_Login);
        ic_action_bar_back = findViewById(R.id.ic_action_bar_back);
        ic_action_bar_back.setOnClickListener(this);
        mBTN_Authenticate_Success.setOnClickListener(this);
        mBTN_Login.setOnClickListener(this);
        mBTN_Signin.setOnClickListener(this);
        adđEvent();

    }

    private void adđEvent() {
        Intent intent = getIntent();
        String Email = intent.getStringExtra("email");
        String Passwords = intent.getStringExtra("password");
        mEDT_Email_Login.setText(Email);
        mEDT_Password_Login.setText(Passwords);
        mySingleTon.setPhone_Account("");
        mySingleTon.setAdress_Finish("");
        mySingleTon.setWallet_Finish("");
        mySingleTon.setFull_Name_Personal("");
        mySingleTon.setDate_of_Birth_Personal("");
        mySingleTon.setGender_Personal("");
        mySingleTon.setUrl_images_passport("");
    }

    @Override
    protected void initData() {

        addEventSpinner();

    }

    private void hideStatusBar() {
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
//        ((AppCompatActivity) this).getSupportActionBar().hide();
//        ic_action_bar_back
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setCustomView(R.layout.action_bar_log_in);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FECC45")));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }
    }

    private void addEventSpinner() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_action_bar_back:
                this.finish();
                break;
            case R.id.mBTN_Login:
//                mTV_Error_Messenger.setVisibility(View.VISIBLE);
//                mLinear_Authenticator.setVisibility(View.VISIBLE);
//                mLinear_login.setVisibility(View.GONE);
                Log.e("XXXXXXXXXXX","XXXXXXXXXX");
                CheckUserName();
                break;
            case R.id.mBTN_Signin:
                Intent intent = new Intent(this, Signin_Activity.class);
                startActivity(intent);
                break;
            case R.id.mBTN_Authenticate_Success:
                mTV_Error_Authorticate.setVisibility(View.VISIBLE);
                show_Alert_Dialog();
                break;

        }
    }

    private void CheckUserName() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.URL_LogIn), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("CHECK_JSON_BLOCK_CHAIN", s + "");
                try {

                    JSONObject jsonObject = new JSONObject(s);
//                    JSONObject obj = jsonObject.getJSONObject("status");
                    String status = jsonObject.getString("status");
                    String msg = jsonObject.getString("msg");
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    if (status.equals("200")) {
//                        Intent intent = new Intent(MainActivity.this, Navigation_Bar.class);
//                        startActivity(intent);
                        SharedPreferences.Editor editor = getSharedPreferences("88", MODE_PRIVATE).edit();
                        editor.putString("username", mEDT_Email_Login.getText().toString());
                        editor.putString("passwords", mEDT_Password_Login.getText().toString());
//                        Toast.makeText(this, "Save Successfully", Toast.LENGTH_SHORT).show();
                        editor.apply();
                      MainActivity.this.finish();
                      Intent intent = new Intent(MainActivity.this,Navigation_Bar.class);
                      startActivity(intent);
                    }
                    Log.e("CHECK_JSON_BLOCK_CHAIN", status.toString() + "");
                    Log.e("CHECK_JSON_BLOCK_CHAIN", msg.toString() + "");
                    Log.e("CHECK_JSON_BLOCK_CHAIN", Locale.getDefault().getDisplayLanguage() + "");


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

//                params.put("email", "kiennguyentrung6393@gmail.com");
                params.put("email", mEDT_Email_Login.getText().toString());
//                params.put("email", "kiennguyentrung6393@gmail.com");
                params.put("password", mEDT_Password_Login.getText().toString());
//                params.put("password","longphuong123");
                params.put("apikey", "NzE0MjM4ZGEtODZiMy00MDQ2LTliOTctYzgxN2RhYTU2N2Iw ");
//                Log.e("PAGE", "respones_" + mysingleton.getPageVideoCate());

                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.layout_progressbar, null);
//        dialogBuilder.setView(dialogView);
//        AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();


    }

    private void show_Alert_Dialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_authentication_success, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
