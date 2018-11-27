package com.kien.lp.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.model.Choose_Number;
import com.kien.lp.myapplication.model.Number_1;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Table_Choose extends RecyclerView.Adapter<Adapter_Table_Choose.UserViewHolder> implements Filterable {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private int visibleThreshold = 5;
    private int lastVisibleItem = 0, totalItemCount = 0, firstVisibleItem = 0, visibleItemCount = 0;
    private int previousTotal = 0;
    public OnItemClickListenner mOnItemClickListenner;
    Activity mActivity;
    List<Choose_Number> mList;
    List<Number_1> mList_Chooose;

    public void setOnItemClickListener(OnItemClickListenner mOnItemClickListenner) {
        this.mOnItemClickListenner = mOnItemClickListenner;
    }
    public Adapter_Table_Choose(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public Adapter_Table_Choose(Activity mActivity, List<Choose_Number> mList) {
        this.mActivity = mActivity;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Adapter_Table_Choose.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_table_choose, viewGroup, false);
            return new UserViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder userViewHolder, final int i) {
//        if (userViewHolder instanceof UserViewHolder) {
//            final UserViewHolder myuserViewHolder = (UserViewHolder) userViewHolder;
            Choose_Number mChoose_number = mList.get(i);
//            myuserViewHolder.mList_Choose_Table_1 = new ArrayList<>();
//            myuserViewHolder.mList_Choose_Table_2 = new ArrayList<>();
        userViewHolder.mImageView_Exit_Chooose_Number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListenner.OnClickItem(userViewHolder.itemView,i);
                }
            });
            mList_Chooose = new ArrayList<>();
        userViewHolder.mList_Choose_Table_1 = mChoose_number.getmList1();
        userViewHolder.mList_Choose_Table_2 = mChoose_number.getmList2();


        userViewHolder.mAdapter1 = new Adapter_Number_Choose(mList_Chooose, mActivity, userViewHolder.mList_Choose_Table_1,1);
        userViewHolder.mRecyclerView1.setAdapter(userViewHolder.mAdapter1);
        userViewHolder.mTV_Line_Choose_Play.setText("Line " + (i + 1) + " /" + mList.size());
            Log.e("SIZE_ADAPTER", userViewHolder.mList_Choose_Table_1.size() + "");
        userViewHolder.mAdapter1.notifyDataSetChanged();
        userViewHolder.mAdapter1.setOnItemClickListener(new OnItemClickListenner() {
                @Override
                public void OnClickItem(View view, int position) {
                    if (mList_Chooose.size()<5){
                        mList_Chooose.add(userViewHolder.mList_Choose_Table_1.get(i));
                    }
                }
            });
        userViewHolder.mAdapter2 = new Adapter_Number_Choose(mList_Chooose, mActivity, userViewHolder.mList_Choose_Table_2,2);
        userViewHolder.mRecyclerView2.setAdapter(userViewHolder.mAdapter2);
        userViewHolder.mAdapter2.notifyDataSetChanged();
        userViewHolder.mAdapter2.setOnItemClickListener(new OnItemClickListenner() {
                @Override
                public void OnClickItem(View view, int position) {
//                         if (mList_Chooose.size()<1){
//                        mList_Chooose.add(myuserViewHolder.mList_Choose_Table_1.get(i));
//                    }
                }
            });
//        }
    }

//    private void addList_Table_1(UserViewHolder myuserViewHolder) {
//        for (int i = 0; i < 70; i++) {
//            myuserViewHolder.mList_Choose_Table_1.add(new Number(i + 1 + "", "0","1"));
//        }
//    }

//    private void addList_Table_2(UserViewHolder myuserViewHolder) {
//        for (int i = 0; i < 10; i++) {
//            myuserViewHolder.mList_Choose_Table_2.add(new Number(i + 1 + "", "0","2"));
//        }
//    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public Filter getFilter() {
        return null;
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView1;
        RecyclerView mRecyclerView2;
        Button mQuick_Choose, Access;
        List<Number_1> mList_Choose_Table_1;
        List<Number_1> mList_Choose_Table_2;
        Adapter_Number_Choose mAdapter1;
        Adapter_Number_Choose mAdapter2;
        TextView mTV_Line_Choose_Play;
        ImageView mImageView_Exit_Chooose_Number;

        public UserViewHolder(@NonNull final View itemView) {
            super(itemView);
            mRecyclerView1 = itemView.findViewById(R.id.mRecyclerview1);
            mRecyclerView2 = itemView.findViewById(R.id.mRecyclerview2);
            mTV_Line_Choose_Play = itemView.findViewById(R.id.mTV_Line_Choose_Play);
            mImageView_Exit_Chooose_Number = itemView.findViewById(R.id.mImageView_Exit_Chooose_Number);
//            mList_Choose_Table_1 = new ArrayList<>();
//            mList_Choose_Table_2 = new ArrayList<>();
            GridLayoutManager gridlayout;
            gridlayout = new GridLayoutManager(mActivity, 9);
            mRecyclerView1.setLayoutManager(gridlayout);
            mRecyclerView1.setNestedScrollingEnabled(false);
//            mAdapter1 = new Adapter_Number_Choose(mActivity,mList_Choose_Table_1);
//            mAdapter2 = new Adapter_Number_Choose(mActivity,mList_Choose_Table_2);
            GridLayoutManager gridlayout1;
            gridlayout1 = new GridLayoutManager(mActivity, 9);
            mRecyclerView2.setLayoutManager(gridlayout1);
            mRecyclerView2.setNestedScrollingEnabled(false);
        }
    }
//    @Override
//    public int getItemViewType(int position) {
//        return mList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
//    }
}
