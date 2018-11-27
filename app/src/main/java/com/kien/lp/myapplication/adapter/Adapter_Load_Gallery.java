
package com.kien.lp.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.fragment.Fragment_Choose_2;
import com.kien.lp.myapplication.fragment.Signin_Fragment_Finish;
import com.kien.lp.myapplication.fragment.gallery.Fragment_Gallery;
import com.kien.lp.myapplication.model.Gallery_Image_Library;
import com.kien.lp.myapplication.model.Gallery_Model;
import com.kien.lp.myapplication.model.Gender;
import com.kien.lp.myapplication.mysingleton.MySingleTon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Adapter_Load_Gallery extends RecyclerView.Adapter<Adapter_Load_Gallery.UserViewHolder> {
    Activity mActivity;
    List<Gallery_Model> mList_Gallery;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private int visibleThreshold = 5;
    private int lastVisibleItem = 0, totalItemCount = 0, firstVisibleItem = 0, visibleItemCount = 0;
    private int previousTotal = 0;
    public OnItemClickListenner mOnItemClickListenner;
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    private String mPathImage;
    private int check_Fragment;
    private boolean isCheck;
    private MySingleTon mySingleTon = MySingleTon.getInstance();

    public Adapter_Load_Gallery(Activity mActivity, List<Gallery_Model> mList_Gallery, int check) {
        this.mActivity = mActivity;
        this.mList_Gallery = mList_Gallery;
        this.check_Fragment = check;
    }

    public Adapter_Load_Gallery(Activity mActivity, List<Gallery_Model> mList_Gallery) {
        this.mActivity = mActivity;
        this.mList_Gallery = mList_Gallery;
    }

    public void setOnItemClickListener(OnItemClickListenner mOnItemClickListenner) {
        this.mOnItemClickListenner = mOnItemClickListenner;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_photo_gallery, viewGroup, false);
            return new UserViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder userViewHolder, final int position) {

        if (userViewHolder instanceof UserViewHolder) {
            isCheck = false;
            final UserViewHolder myuserViewHolder = (UserViewHolder) userViewHolder;
            Log.e("SIZE_PHOTO",mList_Gallery.size()+"");
//            if (position == 0) {
//                userViewHolder.mImage_Choosse.setImageResource(R.drawable.ic_take_photo);
//                userViewHolder.mImage_Choosse.setBackgroundColor(Color.parseColor("#AAAAAA"));
//                userViewHolder.mImage_Choosse.setPadding(40, 40, 40, 40);
////                userViewHolder.mImage_Choosse.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        mOnItemClickListenner.OnClickItem(userViewHolder.itemView, position);
////                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
////                        intent.putExtra(MediaStore.EXTRA_OUTPUT,
////                                Uri.fromFile(photo));
////                        imageUri = Uri.fromFile(photo);
////                        Log.e("66666666666666",imageUri.toString());
////                        if (check_Fragment==0){
////                            mySingleTon.setUrl_images_portrait(imageUri.toString());
////                        }else {
////                            mySingleTon.setUrl_images_passport(imageUri.toString());
////                        }
////                        mActivity.startActivityForResult(intent, TAKE_PICTURE);
////                    }
////                });
//            } else {
                Glide.with(mActivity).load(mList_Gallery.get(position).getImages())
                        .placeholder(R.drawable.image_take_photo).centerCrop()
                        .into(myuserViewHolder.mImage_Choosse);
//                userViewHolder.mImage_Choosse.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        MySingleTon mSingleTon = MySingleTon.getInstance();
//                        if (check_Fragment == 0) {
//                            mSingleTon.setUrl_images_passport(mList_Gallery.get(position).getImages());
//                        } else {
//                            mSingleTon.setUrl_images_portrait(mList_Gallery.get(position).getImages());
//                        }
//                        Log.e("GET_URI_IMAGE", mList_Gallery.get(position).getImages());
//                        Signin_Fragment_Finish mFragment = Signin_Fragment_Finish.newInstance("", "");
//                        FragmentTransaction transaction = ((FragmentActivity) mActivity).getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.ll_Acount_Fragment_1, mFragment);
//                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                        transaction.commit();
//                        mOnItemClickListenner.OnClickItem(userViewHolder.itemView, position);
//                    }
//                });

//            }
            userViewHolder.mImage_Choosse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListenner.OnClickItem(userViewHolder.itemView, position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mList_Gallery == null ? 0 : mList_Gallery.size();


    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage_Choosse;

        public UserViewHolder(@NonNull final View itemView) {
            super(itemView);
            mImage_Choosse = itemView.findViewById(R.id.mIMG_choose_image);

        }

    }


    @Override
    public int getItemViewType(int position) {
        return mList_Gallery.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
}
