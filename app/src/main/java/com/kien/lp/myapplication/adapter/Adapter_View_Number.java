package com.kien.lp.myapplication.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.fragment.View_Number_Choose;
import com.kien.lp.myapplication.model.Item_View_Number;

import java.util.List;

public class Adapter_View_Number extends  RecyclerView.Adapter<Adapter_View_Number.UserViewHolder> {
    List<Item_View_Number> mList;
    Activity mActivity;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private int visibleThreshold = 5;
    private int lastVisibleItem = 0, totalItemCount = 0, firstVisibleItem = 0, visibleItemCount = 0;
    private int previousTotal = 0;

    public Adapter_View_Number(List<Item_View_Number> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_view_number, viewGroup, false);
            return new UserViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        Item_View_Number itemNumber = mList.get(i);
            userViewHolder.stt.setText("#"+(i+1)+"");
            userViewHolder.mTV_Number.setText(itemNumber.getChuoi());
            userViewHolder.mTV_Number_special.setText(itemNumber.getThu_tu());
    }

    @Override
    public int getItemCount() {
        return   mList == null ? 0 : mList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView stt,mTV_Number,mTV_Number_special;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            stt = itemView.findViewById(R.id.stt);
            mTV_Number = itemView.findViewById(R.id.mTV_Number);
            mTV_Number_special = itemView.findViewById(R.id.mTV_Number_special);


        }
    }
}
