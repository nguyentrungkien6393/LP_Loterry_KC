package com.kien.lp.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.Signin_Activity;
import com.kien.lp.myapplication.base.Create_Class;
import com.kien.lp.myapplication.library.CheckTablet;
import com.kien.lp.myapplication.mysingleton.MySingleTon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class Signin_Fragment_Account extends Fragment implements View.OnClickListener {
    public static String DATA_RECEIVE = "name";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public EditText mEmail, mPasswords, mPhone;
    public Button mNext, mBTN_Back;
    public LinearLayout mFragment_Account;
    private FragmentTransaction fragTransaction;
    private FragmentManager fragmentManager;
    MySingleTon mSingleTon;
    Create_Class mCreate;

    public Signin_Fragment_Account() {
        // Required empty public constructor
    }

    public static Signin_Fragment_Account newInstance(String param1, String param2) {
        Signin_Fragment_Account mFragment_1 = new Signin_Fragment_Account();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        mFragment_1.setArguments(args);

        return mFragment_1;
    }

    // project đây ông
    // Chuy^ển từ fragmnet nao sang cai naog
    // cái này sang cái fragmnet n`ao chứ
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin_fragment_account, container, false);
        mCreate = new Create_Class();
        mSingleTon = MySingleTon.getInstance();
        Log.e("aaaaa", mSingleTon.getEmail_Account() + "");
        mEmail = view.findViewById(R.id.mEDT_Signin_Email);
        mPasswords = view.findViewById(R.id.mEDT_Signin_Pasword);
        mPhone = view.findViewById(R.id.mEDT_Signin_Phone);
        mFragment_Account = view.findViewById(R.id.mFragment);
        mNext = view.findViewById(R.id.mBTN_Next_Account);
        mNext.setOnClickListener(this);
        mBTN_Back = view.findViewById(R.id.mBT_Back_Account);
        mBTN_Back.setOnClickListener(this);
        if (mSingleTon.getEmail_Account().length() > 0 || mSingleTon.getPassword_Account().length() > 0 || mSingleTon.getPassword_Account().length() > 0) {
            mEmail.setText(mSingleTon.getEmail_Account());
            mPasswords.setText(mSingleTon.getPassword_Account());
            mPhone.setText(mSingleTon.getPhone_Account());
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBTN_Next_Account:
                if (mEmail.getText().toString().length() == 0 || mPasswords.getText().toString().length() == 0 || mPhone.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Không đưuọc để trống ", Toast.LENGTH_SHORT).show();
                } else {
                    if (isEmailValid(mEmail.getText().toString()) == true) {
                        mSingleTon.setEmail_Account(mEmail.getText().toString());
                        mSingleTon.setPassword_Account(mPasswords.getText().toString());
                        mSingleTon.setPhone_Account(mPhone.getText().toString());
                        mCreate.PushDataCHeck_Validate_Checker(getActivity());
                        fragmentManager = getActivity().getSupportFragmentManager();
                        fragTransaction = fragmentManager.beginTransaction();
                        Signin_Fragment_Peronal Fragment_2 = Signin_Fragment_Peronal.newInstance("", "");
                        Bundle bundle = new Bundle();
                        Fragment_2.setArguments(bundle);
                        fragTransaction.replace(R.id.ll_Acount_Fragment_1, Fragment_2);
                        fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        fragTransaction.commit();
//                        check();
                    } else {
                        Toast.makeText(getActivity(), "Email Không Đúng Định Dạng", Toast.LENGTH_SHORT).show();
                    }


                }
//                Toast.makeText(getActivity(), "AAAAAAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
//                mEmail.setText("HHHHHHHH");
//                Log.e("NaME", "Kien    ");
//               Log.e("TAG_TAG",isEmailValid(mEmail.getText().toString())+"");

                break;
            case R.id.mBT_Back_Account:
                Log.e("NaME", "Kien    ");
                getActivity().finish();
                mSingleTon.setEmail_Account("");
                mSingleTon.setPassword_Account("");
                mSingleTon.setPhone_Account("");
                mSingleTon.setAdress_Finish("");
                mSingleTon.setFull_Name_Personal("");
                mSingleTon.setDate_of_Birth_Personal("");
                mSingleTon.setGender_Personal("");
                mSingleTon.setCountry_Personal("");
                mSingleTon.setWallet_Finish("");
                mSingleTon.setAdress_Finish("");
                mSingleTon.setImage_Pasport("");
                mSingleTon.setImage_Portrait("");
                break;
        }
    }

    public boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void check() {
        if (mCreate.PushDataCHeck_Validate_Checker(getActivity()).equals("200")) {
            mSingleTon.setEmail_Account(mEmail.getText().toString());
            mSingleTon.setPassword_Account(mPasswords.getText().toString());
            mSingleTon.setPhone_Account(mPhone.getText().toString());
            Log.e("Show_TEXT", mEmail.getText().toString() + "\n" +
                    mPasswords.getText().toString()
                    + "\n" + mPhone.getText().toString()
            );

        }
    }
}
