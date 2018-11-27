package com.kien.lp.myapplication.fragment;


import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.Adapter_Table_Choose;
import com.kien.lp.myapplication.adapter.OnItemClickListenner;
import com.kien.lp.myapplication.adapter.buyticket.CellAdapter;
import com.kien.lp.myapplication.adapter.buyticket.CellTicket;
import com.kien.lp.myapplication.adapter.buyticket.Ticket;
import com.kien.lp.myapplication.adapter.buyticket.TiketApdater;
import com.kien.lp.myapplication.model.Choose_Number;
import com.kien.lp.myapplication.model.Number_1;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Choose_2 extends Fragment {
    RecyclerView mReyclerview;
    //    Adapter_Table_Choose mAdapter;
    TiketApdater mAdapter;
    public int mRow = 0;
    public int position = 0;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //    private List<String> mList;
//    private List<Choose_Number>mListChoose = new ArrayList<>();
    private List<Ticket> mListChoose = new ArrayList<>();
    //    private List<Number_1>mListChoose_Table_1 = new ArrayList<>();
//    private List<Number_1>mListChoose_Table_2 = new ArrayList<>();
    private FragmentTransaction fragTransaction;
    private FragmentManager fragmentManager;
    public Fragment_Choose_2() {
    }

    public static Fragment_Choose_2 newInstance(int param1, int param2) {
        Fragment_Choose_2 mFragment = new Fragment_Choose_2();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        mFragment.setArguments(args);
        return mFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__choose_2, container, false);
        mRow = getArguments().getInt(ARG_PARAM2);
        position = getArguments().getInt(ARG_PARAM1);
        Log.e("=======================", mRow + "");
        addControll(view);
        for (int i = 0; i < mRow; i++) {
            mListChoose.add(new Ticket(1, false));
            Log.e("=======================", i + "");
        }
        return view;
    }

    private void addControll(View view) {
        mReyclerview = view.findViewById(R.id.mRecyclerview_Choose_Parent);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mReyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        layoutManager.scrollToPositionWithOffset(position, width);
        mReyclerview.setLayoutManager(layoutManager);
        mAdapter = new TiketApdater(getActivity(), mListChoose);
        mReyclerview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
