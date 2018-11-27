package com.kien.lp.myapplication.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kien.lp.myapplication.LoaddingActivity;
import com.kien.lp.myapplication.MainActivity;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.fragment.Signin_Fragment_Account;
import com.kien.lp.myapplication.model.Gallery_Model;
import com.kien.lp.myapplication.mysingleton.MySingleTon;
import com.kien.lp.myapplication.singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Create_Class {
    public double price_usd ;
    public MySingleTon mySingleTon = MySingleTon.getInstance();
    public String getBoolean = "";
    public String messenger = "";
    public String data = "";
    public String ETHBalance = "";
    public String LTRBalance = "";
    public String getboolean = "";
    public Signin_Fragment_Account mFragment_Account;
    public ArrayList<Gallery_Model> getAllShownImagesPath(Activity mActivity) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<Gallery_Model> listOfAllImages = new ArrayList<Gallery_Model>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = mActivity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            listOfAllImages.add(new Gallery_Model(absolutePathOfImage, false));
        }
        return listOfAllImages;
    }

    public String PushDataCHeck_Validate_Checker(final Activity mActivity) {
        mFragment_Account = new Signin_Fragment_Account();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, mActivity.getString(R.string.URL__Check_Validate_Register), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("CHECK_JSON_BLOCK_CHAIN", s + "");
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    getBoolean = jsonObject.getString("status");
                    messenger = jsonObject.getString("msg");
                    if (getBoolean.equals("200")) {
                        Toast.makeText(mActivity, messenger, Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("CHECK_LOG", messenger);
                        JSONObject oj = new JSONObject(messenger);
                        String email = oj.getString("email");
                        Toast.makeText(mActivity, email, Toast.LENGTH_SHORT).show();
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

//                params.put("email", "kiennguyentrung6393@gmail.com");
                params.put("email", mySingleTon.getEmail_Account());
                params.put("password", mySingleTon.getPassword_Account());
                params.put("apikey", "NzE0MjM4ZGEtODZiMy00MDQ2LTliOTctYzgxN2RhYTU2N2Iw");
                params.put("tel", mySingleTon.getPhone_Account());

//                Log.e("PAGE", "respones_" + mysingleton.getPageVideoCate());

                return params;
            }
        };
        VolleySingleton.getInstance(mActivity).addToRequestQueue(stringRequest);

//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.layout_progressbar, null);
//        dialogBuilder.setView(dialogView);
//        AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();


        return getBoolean;
    }
    private void ShowDialog(final Activity mActivity) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_authentication_success, null);
        ImageView mImageView = dialogView.findViewById(R.id.mImageView_Exit);

        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, MainActivity.class);
                intent.putExtra("email", mySingleTon.getEmail_Account());
                intent.putExtra("password", mySingleTon.getPassword_Account());
                mActivity.startActivity(intent);
                mActivity.finish();
                alertDialog.dismiss();
                mySingleTon.setCountry_Personal("");
                mySingleTon.setGender_Personal("");
                mySingleTon.setDate_of_Birth_Personal("");
                mySingleTon.setFull_Name_Personal("");
                mySingleTon.setWallet_Finish("");
                mySingleTon.setAdress_Finish("");
                mySingleTon.setPhone_Account("");
            }
        });
    }



    public void PushDataSgnin(final Activity mActivity) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, mActivity.getString(R.string.URL_Register), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("CHECK_JSON_BLOCK_CHAIN", s + "");
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String status = jsonObject.getString("status");
                    getboolean = jsonObject.getString("msg");
                    data = jsonObject.getString("data");
                    if (status.equals("200")){
                        ShowDialog(mActivity);
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
                params.put("fullname", mySingleTon.getFull_Name_Personal());
                params.put("email", mySingleTon.getEmail_Account());
                params.put("password", mySingleTon.getPassword_Account());
                params.put("tel", mySingleTon.getPhone_Account());
                params.put("wallet_btc", mySingleTon.getPhone_Account());
                params.put("dob", mySingleTon.getDate_of_Birth_Personal());
                params.put("sex", mySingleTon.getGender_Personal());
                params.put("country", mySingleTon.getCountry_Personal());
                params.put("address", mySingleTon.getAdress_Finish());
                params.put("apikey", "NzE0MjM4ZGEtODZiMy00MDQ2LTliOTctYzgxN2RhYTU2N2Iw");
                params.put("portraitimage", mySingleTon.getUrl_images_portrait());
                params.put("passportimage", mySingleTon.getUrl_images_passport());
                return params;
            }
        };
        VolleySingleton.getInstance(mActivity).addToRequestQueue(stringRequest);

    }

    public double getETH(final Activity mActivity) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.coinmarketcap.com/v1/ticker/ethereum/", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("CHECK_JSON_BLOCK_CHAIN", s + "");
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    JSONObject object = jsonArray.getJSONObject(0);
//                    Log.e("CHECK_API_ETH",object.toString());
                    mySingleTon.setPrice_usd(object.getDouble("price_usd"));
//                    tgia[0] = object.getString("price_usd");
                    Log.e("CHECK_API_ETH", price_usd+"");
//                    Log.e("CHECK_API_ETH", tgia[0].toString());
                    String price_btc = object.getString("price_btc");
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
                return params;
            }
        };
        VolleySingleton.getInstance(mActivity).addToRequestQueue(stringRequest);

        return price_usd;
    }
}
