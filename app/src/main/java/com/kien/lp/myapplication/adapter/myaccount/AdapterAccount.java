package com.kien.lp.myapplication.adapter.myaccount;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kien.lp.myapplication.MainActivity;
import com.kien.lp.myapplication.Navigation_Bar;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.OnItemClickListenner;
import com.kien.lp.myapplication.fragment.buyltr_token.Buy_LTR_Token;
import com.kien.lp.myapplication.layout_activity.Activity_BuyToken;
import com.kien.lp.myapplication.layout_activity.Activity_Wallet;
import com.kien.lp.myapplication.model.Gender;

import java.util.List;

public class AdapterAccount extends  RecyclerView.Adapter<AdapterAccount.UserViewHolder> {
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
    private FragmentTransaction fragTransaction;
    private FragmentManager fragmentManager;
    private SharedPreferences settings;
    public AdapterAccount(Activity mActivity, List<Gender> mList) {
        this.mActivity = mActivity;
        this.mList = mList;
        this.GenderListFiltered = mList;
    }

    public AdapterAccount(Activity mActivity, Context mContext) {
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
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_list_myaccount, viewGroup, false);
            return new UserViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder myViewHolder, final int i) {
                myViewHolder.mTV_Account.setText(mList.get(i).getGender());
                myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;
                        switch (i){
                            case 0:
                                SharedPreferences editor1 = mActivity.getSharedPreferences("88", mActivity.MODE_PRIVATE);
                                String username = editor1.getString("username", "");
                                String passwords = editor1.getString("passwords", "");
                                if (username.length()>0){
                                   Intent intent1 = new Intent (mActivity, Activity_Wallet.class);
                                    mActivity.startActivity(intent1);
//                                    fragmentManager = ((FragmentActivity)mActivity).getSupportFragmentManager();
//                                    fragTransaction = fragmentManager.beginTransaction();
//                                    Fragment_Wallet fragment2 = Fragment_Wallet.newInstance("", "");
//                                    Bundle bundle = new Bundle();
//                                    fragment2.setArguments(bundle);
//                                    fragTransaction.replace(R.id.ln_fr, fragment2);
//                                    fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                                    fragTransaction.commit();
                                }else{
                                    Toast.makeText(mActivity, i+"", Toast.LENGTH_SHORT).show();
                                }

                                break;
                            case 1:
                                settings = mActivity.getSharedPreferences("88", mActivity.MODE_PRIVATE);
                                 username = settings.getString("username", "");
                                 passwords = settings.getString("passwords", "");
                                if (username.length() > 0) {
                                    intent = new Intent(mActivity, Activity_BuyToken.class);
                                    mActivity.startActivity(intent);
                                } else {
                                    intent = new Intent(mActivity, MainActivity.class);
                                    mActivity.startActivity(intent);
                                }
                                break;
                            case 2:
                                Toast.makeText(mActivity, i+"", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(mActivity, i+"", Toast.LENGTH_SHORT).show();

                                break;
                            case 4:
                                Toast.makeText(mActivity, i+"", Toast.LENGTH_SHORT).show();
                                SharedPreferences settings = mActivity.getSharedPreferences("88", Context.MODE_PRIVATE);
                                settings.edit().clear().commit();
                                mActivity.finish();
                                intent = new Intent(mActivity, Navigation_Bar.class);
                                mActivity.startActivity(intent);
                                break;

                        }
                    }
                });
    }



    @Override
    public int getItemCount() {
        return GenderListFiltered.size();
    }



    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView mTV_Account;
        public UserViewHolder(@NonNull final View itemView) {
            super(itemView);
            mTV_Account = itemView.findViewById(R.id.mTV_Account);
        }
    }
    @Override
    public int getItemViewType(int position) {
        return mList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
}
