package com.kien.lp.myapplication.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.AdapterSpinner_Gender;
import com.kien.lp.myapplication.adapter.OnItemClickListenner;
import com.kien.lp.myapplication.model.Gender;
import com.kien.lp.myapplication.mysingleton.MySingleTon;
import com.kien.lp.myapplication.string.String_CounTry;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Signin_Fragment_Peronal extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String DATA_RECEIVE = "name";
    private Button mButton_Next_Skip, mButton_Back_Skip;
    private FragmentTransaction fragTransaction;
    private FragmentManager fragmentManager;
    private EditText mEDT_Signin_Fullname, mEDT_Signin_Date_of_Birth;
    private TextView mEDT_Signin_Gender, mEDT_Signin_Country ,mTV_Title_Dialog;
    private MySingleTon mSingleTon;
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;
    private List<Gender> mListGender;
    private AdapterSpinner_Gender mAdapter;
    private LinearLayoutManager layoutManager;
    public Signin_Fragment_Peronal() {
        // Required empty public constructor
    }

    public static Signin_Fragment_Peronal newInstance(String param1, String param2) {
        Signin_Fragment_Peronal fragment = new Signin_Fragment_Peronal();
        Bundle args = new Bundle();
        args.putString(DATA_RECEIVE, param1);

        Log.e("CHECK__3", "" + param1);
//        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin_fragment_peronal, container, false);
        mSingleTon = MySingleTon.getInstance();
        mEDT_Signin_Fullname = view.findViewById(R.id.mEDT_Signin_Fullname);
        mEDT_Signin_Date_of_Birth = view.findViewById(R.id.mEDT_Signin_Date_of_Birth);
        mEDT_Signin_Gender = view.findViewById(R.id.mEDT_Signin_Gender);
        mEDT_Signin_Country = view.findViewById(R.id.mEDT_Signin_Country);
        mButton_Next_Skip = view.findViewById(R.id.mButton_Next_Skip);
        mButton_Back_Skip = view.findViewById(R.id.mButton_Back_Skip);
        mButton_Next_Skip.setOnClickListener(this);
        mButton_Back_Skip.setOnClickListener(this);
        mEDT_Signin_Gender.setOnClickListener(this);
        mEDT_Signin_Country.setOnClickListener(this);
        Showtext();
        return view;
    }

    private void Showtext() {
        if (mSingleTon.getFull_Name_Personal().length() > 0 || mSingleTon.getDate_of_Birth_Personal().length() > 0 || mSingleTon.getGender_Personal().length() > 0 || mSingleTon.getCountry_Personal().length() > 0) {
            mEDT_Signin_Fullname.setText(mSingleTon.getFull_Name_Personal());
            mEDT_Signin_Date_of_Birth.setText(mSingleTon.getDate_of_Birth_Personal());
            mEDT_Signin_Gender.setText(mSingleTon.getGender_Personal());
            mEDT_Signin_Country.setText(mSingleTon.getCountry_Personal());

        }


    }


    private void show_Alert_Dialog(String title, final String search) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_choose, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        mSearchView = dialogView.findViewById(R.id.mSV_Dialog);
        mTV_Title_Dialog = dialogView.findViewById(R.id.mTV_Title_Dialog);
        mRecyclerView = dialogView.findViewById(R.id.mRecyclerView_Dialog);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        if (search.equals("0")) {
            mSearchView.setVisibility(View.GONE);
            mListGender = new ArrayList<>();
//        mListGender.add(new Gender("","1"));
//       mListGender.add(new Gender("","1"));
            mListGender.add(new Gender(getString(R.string.male) + "", "1"));
            mListGender.add(new Gender(getString(R.string.female) + "", "2"));
            mListGender.add(new Gender(getString(R.string.orther) + "", "3"));
        } else {
            mSearchView.setVisibility(View.VISIBLE);
            mListGender = new ArrayList<>();
            getSizeCountryArray(mListGender);
        }

        mAdapter = new AdapterSpinner_Gender(getActivity(), mListGender);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListenner() {
            @Override
            public void OnClickItem(View view, int position) {
                Log.e("AAAAAAAAAAA", mListGender.get(position).getGender());
                if (search.equals("0")) {
                    mEDT_Signin_Gender.setText(mListGender.get(position).getGender());
                } else {
                    mEDT_Signin_Country.setText(mListGender.get(position).getGender());
                }

                alertDialog.dismiss();


            }
        });
        mAdapter.notifyDataSetChanged();
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        mSearchView.setSearchableInfo(searchManager
//                .getSearchableInfo(getComponentName()));
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // filter recycler view when query submitted
////                mAdapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                // filter recycler view when text is changed
//                mAdapter.getFilter().filter(query);
//                return false;
//            }
//        });
//        mTV_Title_Dialog.setText(title);


    }

    private void getSizeCountryArray(List<Gender>mListGender) {
        String_CounTry mCountry = new String_CounTry();
        for (int i = 0; i<(mCountry.size_list)/2;i++){
            int x = 1+(2*i);
            Log.e("HHHHH","\n"+x+"-------"+mCountry.separated[x]+"");
            mCountry.mCountry.add(mCountry.separated[x]);
            mListGender.add(new Gender(mCountry.separated[x],i+1+""));
        }
        Log.e("SIZE__SIZE",mCountry.size_list+"");
        Log.e("SIZE__SIZE",mCountry.mCountry.size()+"");

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("Data" + getParentFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mButton_Next_Skip:

                mSingleTon.setFull_Name_Personal(mEDT_Signin_Fullname.getText().toString());
                mSingleTon.setDate_of_Birth_Personal(mEDT_Signin_Date_of_Birth.getText().toString());
                mSingleTon.setGender_Personal(mEDT_Signin_Gender.getText().toString());
                mSingleTon.setCountry_Personal(mEDT_Signin_Country.getText().toString());

                fragmentManager = getActivity().getSupportFragmentManager();
                fragTransaction = fragmentManager.beginTransaction();
                Signin_Fragment_Finish Fragment_3 = Signin_Fragment_Finish.newInstance("", "xxx");
                fragTransaction.replace(R.id.ll_Acount_Fragment_1, Fragment_3);
                fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTransaction.commit();
                break;
            case R.id.mButton_Back_Skip:
                fragmentManager = getActivity().getSupportFragmentManager();
                fragTransaction = fragmentManager.beginTransaction();
                Signin_Fragment_Account Fragment_1 = Signin_Fragment_Account.newInstance("", "");
                Bundle bundle1 = new Bundle();
                Fragment_1.setArguments(bundle1);
                fragTransaction.replace(R.id.ll_Acount_Fragment_1, Fragment_1);
                fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTransaction.commit();
                break;
            case R.id.mEDT_Signin_Country:
                show_Alert_Dialog(getString(R.string.gender),"1");
                break;
            case R.id.mEDT_Signin_Gender:
                show_Alert_Dialog(getString(R.string.country),"0");
                break;
            default:
                break;
        }
    }
}
