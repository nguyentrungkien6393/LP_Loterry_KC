package com.kien.lp.myapplication.fragment.wallet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.base.Create_Class;
import com.kien.lp.myapplication.fragment.result.Fragment_Result;
import com.kien.lp.myapplication.mysingleton.MySingleTon;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Wallet extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MySingleTon mySingleTon = MySingleTon.getInstance();
    private TextView mTV_wallet,mTV_LTR_Result,mTV_ETH_Result;


    public Fragment_Wallet() {
        // Required empty public constructor
    }
    public static Fragment_Wallet newInstance(String param1, String param2) {
        Fragment_Wallet mFragment = new Fragment_Wallet();
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
        View view  = inflater.inflate(R.layout.fragment_wallet, container, false);
        // Inflate the layout for this fragment
        mTV_wallet = view.findViewById(R.id.mTV_wallet);
        mTV_LTR_Result = view.findViewById(R.id.mTV_LTR_Result);
        mTV_ETH_Result = view.findViewById(R.id.mTV_ETH_Result);
        mTV_wallet.setText(mySingleTon.getWallet_Finish());
        mTV_LTR_Result.setText(mySingleTon.getLTR_Balance());
        mTV_ETH_Result.setText(mySingleTon.getETH_Balance());
        return view;
    }

}
