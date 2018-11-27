package com.kien.lp.myapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.buyticket.Ticket;
import com.kien.lp.myapplication.fragment.Fragment_Choose_2;
import com.kien.lp.myapplication.fragment.Fragment_Choose_HowtoPlay;
import com.kien.lp.myapplication.fragment.Signin_Fragment_Peronal;
import com.kien.lp.myapplication.layout_activity.Activity_Choose2;
import com.kien.lp.myapplication.model.Number_Ticket;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Table_How_To_Play extends RecyclerView.Adapter<Adapter_Table_How_To_Play.UserViewHolder> {
    List<Ticket> mList;
    Activity mActivity;
    int row;
    int animation_ramdom;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private int visibleThreshold = 5;
    private int lastVisibleItem = 0, totalItemCount = 0, firstVisibleItem = 0, visibleItemCount = 0;
    private int previousTotal = 0;
    public OnItemClickListenner mOnItemClickListenner;
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    private boolean isCheck;
    private int mPosition;
    FragmentTransaction fragTransaction;
    FragmentManager fragmentManager;

    public Adapter_Table_How_To_Play(List<Ticket> mList, Activity mActivity, int mRow) {
        this.mList = mList;
        this.mActivity = mActivity;
        this.row = mRow;
    }

    public Adapter_Table_How_To_Play(List<Ticket> mList, Activity mActivity, int mRow, int animation_ramdom) {
        this.mList = mList;
        this.mActivity = mActivity;
        this.row = mRow;
        this.animation_ramdom = animation_ramdom;
    }
//    public Adapter_Table_How_To_Play(List<String> mList, Activity mActivity, int mRow, int animation_ramdom,int type_ticket) {
//        this.mList = mList;
//        this.mActivity = mActivity;
//        this.row = mRow;
//        this.animation_ramdom = animation_ramdom;
//    }

    public void setOnItemClickListener(OnItemClickListenner mOnItemClickListenner) {
        this.mOnItemClickListenner = mOnItemClickListenner;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_picks_your_number, viewGroup, false);
            return new UserViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder userViewHolder, final int i) {
        Log.e("CHECK_SIZE_TRUE_FALSE",mList.get(i).isSelted()+"");
            if (mList.get(i).isSelted()==true){
                userViewHolder.mBackGround_Table_Choose.setBackgroundResource(R.drawable.table_choose11);
                animation(userViewHolder,i);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 80);
                params.setMargins(5,5,5,0);
                userViewHolder.mBackGround_Table_Choose.setLayoutParams(params);
            }else{
                userViewHolder.mBackGround_Table_Choose.setBackgroundResource(R.drawable.cap2);
                animation(userViewHolder,i);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50   , 80);
                userViewHolder.mBackGround_Table_Choose.setLayoutParams(layoutParams);
                layoutParams.setMargins(5,5,5,0);
                userViewHolder.mBackGround_Table_Choose.setLayoutParams(layoutParams);
//                userViewHolder.mBackGround_Table_Choose.setPadding(0,0,0,10);
//                userViewHolder.mBackGround_Table_Choose.(0,0,0,10);


            }


    }

    private void animation(final UserViewHolder userViewHolder,final int i) {
        if (animation_ramdom == 0) {
//            userViewHolder.mBackGround_Table_Choose.setBackgroundResource(R.drawable.table_choose22);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //here call the second method
                    userViewHolder.mBackGround_Table_Choose.setBackgroundResource(R.drawable.table_choose33);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //here call the second method
                            userViewHolder.mBackGround_Table_Choose.setBackgroundResource(R.drawable.table_choose44);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //here call the second method
                                    userViewHolder.mBackGround_Table_Choose.setBackgroundResource(R.drawable.table_choose55);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //here call the second method
                                            userViewHolder.mBackGround_Table_Choose.setBackgroundResource(R.drawable.cap1);
                                            mList.get(i).setSelted(true);

                                        }
                                    }, 100);
                                }
                            }, 100);
                        }
                    }, 100);
                }
            }, 100);
        }
//        notifyDataSetChanged();
        userViewHolder.mTextview.setText(mList.get(i).getNumber()+"");
        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Log.e("ABCXYZ_FRAGMENT","TRUYỂN DỮ LIỆU " + row);
                System.out.println("______________________________________________ " + mList.get(i).isSelted());
//                Fragment_Choose_2 mFragment = Fragment_Choose_2.newInstance((i), mList.size());
//                FragmentTransaction transaction = ((FragmentActivity) mActivity).getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.ln_fr, mFragment);
//                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                transaction.commit();
                Intent intent = new Intent(mActivity, Activity_Choose2.class);
                intent.putExtra("position",(i));
                intent.putExtra("sizelist",mList.size());
                mActivity.startActivity(intent);
//                mList.get(i).setSelted(true);
//                userViewHolder.mBackGround_Table_Choose.setBackgroundResource(R.drawable.table_choose11);
//                notifyDataSetChanged();


            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView mTextview;
        LinearLayout mBackGround_Table_Choose;

        public UserViewHolder(@NonNull final View itemView) {
            super(itemView);
            mTextview = itemView.findViewById(R.id.mTextview);
            mBackGround_Table_Choose = itemView.findViewById(R.id.mBackGround_Table_Choose);
        }
    }
}
