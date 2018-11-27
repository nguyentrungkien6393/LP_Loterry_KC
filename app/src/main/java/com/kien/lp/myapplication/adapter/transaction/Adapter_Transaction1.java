package com.kien.lp.myapplication.adapter.transaction;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.buyticket.CellAdapter;
import com.kien.lp.myapplication.adapter.buyticket.CellTicket;
import com.kien.lp.myapplication.model.Model_Transaction;

import java.util.List;

public class Adapter_Transaction1 extends RecyclerView.Adapter<Adapter_Transaction1.CellHolder> {
    List<Model_Transaction> mList;
    Context context;
    int type_Click_Ticket;

    public Adapter_Transaction1(Context context, List<Model_Transaction> cellTickets, int type_Click_Ticket) {
        this.mList = cellTickets;
        this.context = context;
        this.type_Click_Ticket = type_Click_Ticket;
    }

    OnItemClickLitien litien;

    public Adapter_Transaction1(Context context, List<Model_Transaction> cellTickets) {
        this.context = context;
        this.mList = cellTickets;
    }


//    public void setOnItemClickLitien(CellAdapter.OnItemClickLitien litien) {
//        this.litien = litien;
//    }

    @NonNull
    @Override
    public Adapter_Transaction1.CellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_transaction, parent, false);
        return new CellHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CellHolder holder, int position) {
        Model_Transaction cellTicket = mList.get(position);
        String[] number =   cellTicket.getNumbers().split(" ");
        String[] separated =   cellTicket.getSpecial_numbers().split(" ");
        holder.mTV_Name_Game.setText("LTR JackPot");
        holder.mTV_Number1.setText(number[0]);
        holder.mTV_Number2.setText(number[1]);
        holder.mTV_Number3.setText(number[2]);
        holder.mTV_Number4.setText(number[3]);
        holder.mTV_Number5.setText(number[4]);
        holder.mTV_Number_Special1.setText(separated[0]);
        if (separated.length>1){
            holder.mTV_Number_Special2.setText(separated[1]);
        }else {
            holder.mTV_Number_Special2.setVisibility(View.GONE);
        }
        holder.mTV_TXHash.setText("HaskMap: "+cellTicket.getTxhash());
        holder.mTV_Price.setText("Price: "+cellTicket.getPrice());
        holder.mTime_Date.setText(cellTicket.getCreated_at());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class CellHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View item;
        TextView mTV_Name_Game,mTV_Number1,mTV_Number2,mTV_Number3,mTV_Number4,mTV_Number5,mTV_Number_Special1,mTV_Number_Special2,mTV_TXHash,mTV_Price,mTime_Date;
        public CellHolder(View itemView) {
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
            item = itemView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            litien.onClickIn(v, getLayoutPosition());
        }
    }

    public interface OnItemClickLitien {
        void onClickIn(View v, int pos);
    }
}
