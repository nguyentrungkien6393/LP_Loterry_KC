package com.kien.lp.myapplication.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.Adapter_View_Number;
import com.kien.lp.myapplication.model.Item_View_Number;

import java.util.ArrayList;
import java.util.List;

public class View_Number_Choose extends Fragment {
    RecyclerView mRecyclerview_View_Number;
    Adapter_View_Number mAdapter;
    List<Item_View_Number> mList_ViewNumber;
    public View_Number_Choose() {
        // Required empty public constructor
    }
    public static View_Number_Choose newInstance(String param1, String param2) {
        View_Number_Choose mFragment_1 = new View_Number_Choose();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        mFragment_1.setArguments(args);

        return mFragment_1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view__number__choose, container, false);
        addControll(view);
        return view;
    }

    private void addControll(View view) {
        mRecyclerview_View_Number = view.findViewById(R.id.mRecyclerview_View_Number);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerview_View_Number.setLayoutManager(mLayoutManager);
        mList_ViewNumber = new ArrayList<>();
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mList_ViewNumber.add(new Item_View_Number("1 2 3 4 5","6"));
        mAdapter = new Adapter_View_Number(mList_ViewNumber,getActivity());
        mRecyclerview_View_Number.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

}
