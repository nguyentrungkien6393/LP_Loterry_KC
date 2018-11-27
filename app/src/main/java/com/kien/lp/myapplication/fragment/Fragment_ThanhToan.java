package com.kien.lp.myapplication.fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kien.lp.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ThanhToan extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button mButton_Checkout;

    public Fragment_ThanhToan() {

    }

    public static Fragment_ThanhToan newInstance(int param1, int param2) {
        Fragment_ThanhToan mFragment = new Fragment_ThanhToan();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__thanh_toan, container, false);
        mButton_Checkout = view.findViewById(R.id.mButton_Checkout);
        mButton_Checkout.setOnClickListener(this);
        return view;
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
                "        \"price\":1000,\n" +
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
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        queue.add(myReq);
    }
    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("____________","Error data from server - "+error);
                Toast.makeText(getActivity(), "Buy Error", Toast.LENGTH_SHORT).show();
            }
        };
    }
    private Response.Listener<String> createMyReqSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("+++++++++++","Parse data from server - "+response);
                Toast.makeText(getActivity(), "Buy Successfull", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject object = new JSONObject(response);
                    String status = object.getString("status");
                    String msg = object.getString("msg");
                    if (status.equals("200")){
                        showDialog_Checkout();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mButton_Checkout:
                pushJSON();
                break;
        }
    }
    private void showDialog_Checkout(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_checkout, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
