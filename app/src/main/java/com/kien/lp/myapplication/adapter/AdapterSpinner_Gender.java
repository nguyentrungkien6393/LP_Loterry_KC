package com.kien.lp.myapplication.adapter;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.model.Gender;

import java.util.ArrayList;
import java.util.List;

public class AdapterSpinner_Gender extends   RecyclerView.Adapter<AdapterSpinner_Gender.UserViewHolder> implements Filterable {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private int visibleThreshold = 5;
    private int lastVisibleItem=0, totalItemCount=0, firstVisibleItem=0, visibleItemCount=0;
    private int previousTotal = 0;
    public OnItemClickListenner mOnItemClickListenner;
    Activity mActivity;
    Context mContext;
    List<Gender> mList;
    List<Gender> GenderListFiltered;

    public AdapterSpinner_Gender(Activity mActivity, List<Gender> mList) {
        this.mActivity = mActivity;
        this.mList = mList;
        this.GenderListFiltered = mList;
    }

    public AdapterSpinner_Gender(Activity mActivity, Context mContext) {
        this.mActivity = mActivity;
        this.mContext = mContext;
    }

    public void setOnItemClickListener(OnItemClickListenner mOnItemClickListenner) {
        this.mOnItemClickListenner = mOnItemClickListenner;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_recyclerview_choose, viewGroup, false);
            return new UserViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder myViewHolder, final int i) {
        if (myViewHolder instanceof UserViewHolder){
            final UserViewHolder userViewHolder = (UserViewHolder) myViewHolder;
            Gender mGender = mList.get(i);
            userViewHolder.mTV_Gender_And_Country.setText("-- "+mGender.getGender()+" --");
            userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListenner.OnClickItem(userViewHolder.itemView,i);
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return GenderListFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    GenderListFiltered = mList;
                } else {
                    List<Gender> filteredList = new ArrayList<>();
                    for (Gender row : mList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getGender().toLowerCase().contains(charString.toLowerCase()) || row.getGenderCode().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    GenderListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = GenderListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                GenderListFiltered = (ArrayList<Gender>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView mTV_Gender_And_Country;
        public UserViewHolder(@NonNull final View itemView) {
            super(itemView);
            mTV_Gender_And_Country = itemView.findViewById(R.id.mTV_Gender_And_Country);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickListenner.OnClickItem(itemView,);
//                }
//            });
        }
    }
    @Override
    public int getItemViewType(int position) {
        return mList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
}
