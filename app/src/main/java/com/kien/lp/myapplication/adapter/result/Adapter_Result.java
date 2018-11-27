package com.kien.lp.myapplication.adapter.result;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.buyticket.CellAdapter;
import com.kien.lp.myapplication.adapter.buyticket.CellTicket;

import java.util.List;

public class Adapter_Result extends RecyclerView.Adapter<Adapter_Result.CellHolder>{
    List<String> cellResult;
    Context context;
    int type_Click_Ticket;

    public Adapter_Result(List<String> cellResult, Context context) {
        this.cellResult = cellResult;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_Result.CellHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_result, viewGroup, false);
        return new Adapter_Result.CellHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Result.CellHolder cellHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return cellResult.size() ;
    }

    public class CellHolder extends RecyclerView.ViewHolder {
        public CellHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
