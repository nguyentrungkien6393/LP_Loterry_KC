package com.kien.lp.myapplication.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.model.Number_1;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Number_Choose extends RecyclerView.Adapter<Adapter_Number_Choose.UserViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private int visibleThreshold = 5;
    private int lastVisibleItem = 0, totalItemCount = 0, firstVisibleItem = 0, visibleItemCount = 0;
    private int previousTotal = 0;
    public OnItemClickListenner mOnItemClickListenner;
    List<Number_1> mListString;
    Activity mActivity;
    List<Number_1> mList;
    public int type_item;


    public Adapter_Number_Choose(List<Number_1> mListString, Activity mActivity, List<Number_1> mList, int type_item) {
        this.mListString = mListString;
        this.mActivity = mActivity;
        this.mList = mList;
        this.type_item = type_item;
    }

    public Adapter_Number_Choose(List<Number_1> mListString, Activity mActivity, List<Number_1> mList) {
        this.mListString = mListString;
        this.mActivity = mActivity;
        this.mList = mList;
    }


    public Adapter_Number_Choose(Activity mActivity, List<Number_1> mList) {
        this.mActivity = mActivity;
        this.mList = mList;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_recyclerview_choose_item_number, viewGroup, false);
            return new UserViewHolder(view);
        }
        return null;
    }

    public void setOnItemClickListener(OnItemClickListenner mOnItemClickListenner) {
        this.mOnItemClickListenner = mOnItemClickListenner;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder userViewHolder, final int i) {
//        if (userViewHolder instanceof UserViewHolder) {
//            final UserViewHolder myuserViewHolder = (UserViewHolder) userViewHolder;
            final Number_1 mNumber = mList.get(i);
        userViewHolder.mTV_Choose_Number.setText(mNumber.getNumber());
//            mListString = new ArrayList<>();
            if (type_item == 1) {
                if (mNumber.getType_number().equals("0")) {
                    userViewHolder.mTV_Choose_Number.setBackgroundColor(Color.parseColor("#ffffff"));
//                myuserViewHolder.mTV_Choose_Number.setBackgroundResource(R.drawable.bogoc1);
////                myuserViewHolder.mTV_Choose_Number.setTextColor(Color.parseColor("#000000"));
                } else {
//                myuserViewHolder.mTV_Choose_Number.setBackgroundResource(R.drawable.bogoc_danhso);
                    userViewHolder.mTV_Choose_Number.setBackgroundColor(Color.parseColor("#4677C4"));
                    userViewHolder.mTV_Choose_Number.setTextColor(Color.parseColor("#FFFFFF"));
//                myuserViewHolder.mTV_Choose_Number.setC
                }
            } else {
                if (mNumber.getType_number().equals("0")) {
                    userViewHolder.mTV_Choose_Number.setBackgroundColor(Color.parseColor("#ffffff"));
//                myuserViewHolder.mTV_Choose_Number.setBackgroundResource(R.drawable.bogoc1);
////                myuserViewHolder.mTV_Choose_Number.setTextColor(Color.parseColor("#000000"));
                } else {

//                myuserViewHolder.mTV_Choose_Number.setBackgroundResource(R.drawable.bogoc_danhso);
                    userViewHolder.mTV_Choose_Number.setBackgroundColor(Color.parseColor("#EE3300"));
                    userViewHolder.mTV_Choose_Number.setTextColor(Color.parseColor("#FFFFFF"));
//                myuserViewHolder.mTV_Choose_Number.setC
                }
            }


        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mList.get(i).getType_number().equals("0")) {
                        if (mListString.size() < 5) {
//                            mListString.add(i+"");
                            Log.e("POSITION_ADD", (i + 1) + "");
                            Log.e("POSITION_ADD", mListString.size() + "");
                            mOnItemClickListenner.OnClickItem(userViewHolder.itemView, i);
                            mList.get(i).setType_number("1");
                            notifyDataSetChanged();
                            Gson gson = new Gson();
                            // Convert numbers array into JSON string.
                            String numbersJson = gson.toJson(mListString);
                            Log.e("JSON_GAME_INFO", numbersJson);
                        }
                    } else {
                        mListString.remove(mList.get(i).getNumber());
                        Gson gson = new Gson();
                        String numbersJson = gson.toJson(mListString);
                        Log.e("JSON_GAME_INFO", numbersJson);
                        Log.e("POSITION_ADD", mListString.size() + "");
                        mList.get(i).setType_number("0");
                        notifyDataSetChanged();
                    }
                }
            });
//        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView mTV_Choose_Number;

        public List<String> mList_Choose;

        public UserViewHolder(@NonNull final View itemView) {
            super(itemView);
            mTV_Choose_Number = itemView.findViewById(R.id.mTV_Choose_Number);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
//    @Override
//    public int getItemViewType(int position) {
//        return mList.get(position) ;
//    }
}
