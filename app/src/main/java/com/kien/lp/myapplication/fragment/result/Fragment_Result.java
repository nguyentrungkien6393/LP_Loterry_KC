package com.kien.lp.myapplication.fragment.result;


import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.result.Adapter_Result;
import com.kien.lp.myapplication.fragment.Fragment_Choose_2;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Result extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView mRecyclerviewResult;
    List<String> mList_String = new ArrayList<>();
    public Fragment_Result() {
        // Required empty public constructor
    }
    public static Fragment_Result newInstance(String param1, String param2) {
        Fragment_Result mFragment = new Fragment_Result();
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
        View view = inflater.inflate(R.layout.fragment_fragment_result, container, false);
        addControll(view);
        return view;


    }

    private void addControll(View view) {
        mRecyclerviewResult = view.findViewById(R.id.mRecyclerview_Result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerviewResult.setLayoutManager(linearLayoutManager);
        mList_String.add("1");
        mList_String.add("1");
        mList_String.add("1");
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerviewResult);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        layoutManager.scrollToPositionWithOffset(0, width / 8);
        Adapter_Result mAdapter_result = new Adapter_Result(mList_String,getActivity());
        mRecyclerviewResult.setAdapter(mAdapter_result);
        mAdapter_result.notifyDataSetChanged();

    }

}
