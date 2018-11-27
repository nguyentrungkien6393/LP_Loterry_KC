package com.kien.lp.myapplication.adapter.buyticket;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kien.lp.myapplication.R;

import java.util.List;

public class CellAdapter extends RecyclerView.Adapter<CellAdapter.CellHolder> {
    List<CellTicket> cellTickets;
    Context context;
    int type_Click_Ticket;

    public CellAdapter(Context context, List<CellTicket> cellTickets, int type_Click_Ticket) {
        this.cellTickets = cellTickets;
        this.context = context;
        this.type_Click_Ticket = type_Click_Ticket;
    }

    OnItemClickLitien litien;

    public CellAdapter(Context context, List<CellTicket> cellTickets) {
        this.context = context;
        this.cellTickets = cellTickets;
    }


    public void setOnItemClickLitien(OnItemClickLitien litien) {
        this.litien = litien;
    }

    @NonNull
    @Override
    public CellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_choose_item_number, parent, false);
        return new CellHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CellHolder holder, int position) {
        CellTicket cellTicket = cellTickets.get(position);
        holder.txtNumber.setText((cellTicket.number + 1) + "");
        if (cellTicket.seleted ) {
            if (type_Click_Ticket==0){
                holder.txtNumber.setBackgroundColor(Color.parseColor("#1F4286"));
                holder.txtNumber.setTextColor(Color.parseColor("#FFFFFF"));
            }else {
                holder.txtNumber.setBackgroundColor(Color.parseColor("#D74D26"));
                holder.txtNumber.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
        else {
            if (type_Click_Ticket==0){
                holder.txtNumber.setBackgroundColor(Color.parseColor("#FFFFFF"));
                holder.txtNumber.setTextColor(Color.parseColor("#000000"));
            }else {
                holder.txtNumber.setBackgroundColor(Color.parseColor("#F0854D"));
                holder.txtNumber.setTextColor(Color.parseColor("#FFFFFF"));
            }
//            holder.txtNumber.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            holder.txtNumber.setTextColor(Color.parseColor("#000000"));


        }
    }

    @Override
    public int getItemCount() {
        return cellTickets.size();
    }


    class CellHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtNumber;
        View item;

        public CellHolder(View itemView) {
            super(itemView);
            txtNumber = itemView.findViewById(R.id.mTV_Choose_Number);
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
