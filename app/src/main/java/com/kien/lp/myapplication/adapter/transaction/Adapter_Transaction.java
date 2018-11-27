package com.kien.lp.myapplication.adapter.transaction;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.OnItemClickListenner;
import com.kien.lp.myapplication.adapter.myaccount.AdapterAccount;
import com.kien.lp.myapplication.model.Gender;
import com.kien.lp.myapplication.model.Model_Transaction;

import java.util.List;

public class Adapter_Transaction extends  RecyclerView.Adapter<Adapter_Transaction.UserViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private int visibleThreshold = 5;
    private int lastVisibleItem=0, totalItemCount=0, firstVisibleItem=0, visibleItemCount=0;
    private int previousTotal = 0;
    public OnItemClickListenner mOnItemClickListenner;
//    Activity mActivity;
    Context mContext;
    List<Model_Transaction> mList;

    public Adapter_Transaction(Context mContext, List<Model_Transaction> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

//    public Adapter_Transaction(Activity mActivity, Context mContext) {
//        this.mActivity = mActivity;
//        this.mContext = mContext;
//    }

    public void setOnItemClickListener(OnItemClickListenner mOnItemClickListenner) {
        this.mOnItemClickListenner = mOnItemClickListenner;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_transaction, viewGroup, false);
            return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Transaction.UserViewHolder myViewHolder, final int i) {
        String[] number =    mList.get(i).getNumbers().split(" ");
        String[] separated =    mList.get(i).getSpecial_numbers().split(" ");
        myViewHolder.mTV_Name_Game.setText("LTR JackPot");
        myViewHolder.mTV_Number1.setText(number[0]);
        myViewHolder.mTV_Number2.setText(number[1]);
        myViewHolder.mTV_Number3.setText(number[2]);
        myViewHolder.mTV_Number4.setText(number[3]);
        myViewHolder.mTV_Number5.setText(number[4]);
        myViewHolder.mTV_Number_Special1.setText(separated[0]);
        if (separated.length>1){
            myViewHolder.mTV_Number_Special2.setText(separated[1]);
        }else {
            myViewHolder.mTV_Number_Special2.setVisibility(View.GONE);
        }
        myViewHolder.mTV_TXHash.setText("HaskMap: "+mList.get(i).getTxhash());
        myViewHolder.mTV_Price.setText("Price: "+mList.get(i).getPrice());
        myViewHolder.mTime_Date.setText(mList.get(i).getCreated_at());

    }



    @Override
    public int getItemCount() {
        return mList.size();
    }



    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView mTV_Name_Game,mTV_Number1,mTV_Number2,mTV_Number3,mTV_Number4,mTV_Number5,mTV_Number_Special1,mTV_Number_Special2,mTV_TXHash,mTV_Price,mTime_Date;


                public UserViewHolder( View itemView) {
            super(itemView);
                    mTV_Name_Game = itemView.findViewById(R.id.mTV_Name_Game);
                    mTV_Number1 = itemView.findViewById(R.id.mTV_Number1);
                    mTV_Number2 = itemView.findViewById(R.id.mTV_Number2);
                    mTV_Number3 = itemView.findViewById(R.id.mTV_Number3);
                    mTV_Number4 = itemView.findViewById(R.id.mTV_Number4);
                    mTV_Number5 = itemView.findViewById(R.id.mTV_Number5);
                    mTV_Number_Special1 = itemView.findViewById(R.id.mTV_Number_Special1);
                    mTV_Number_Special2 = itemView.findViewById(R.id.mTV_Number_Special2);
                    mTV_TXHash = itemView.findViewById(R.id.mTV_TXHash);
                    mTV_Price = itemView.findViewById(R.id.mTV_Price);
                    mTime_Date = itemView.findViewById(R.id.mTime_Date);
        }
    }
}
