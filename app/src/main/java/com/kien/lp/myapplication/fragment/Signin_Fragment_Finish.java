package com.kien.lp.myapplication.fragment;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kien.lp.myapplication.MainActivity;
import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.Adapter_Load_Gallery;
import com.kien.lp.myapplication.adapter.OnItemClickListenner;
import com.kien.lp.myapplication.base.Create_Class;
import com.kien.lp.myapplication.fragment.gallery.Fragment_Gallery;
import com.kien.lp.myapplication.model.Gallery_Model;
import com.kien.lp.myapplication.mysingleton.MySingleTon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Signin_Fragment_Finish extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String DATA_RECEIVE = "name";
    public Button mButton_Back_Finish, mButton_Submit_Finish;
    public ImageView mImageView_Passport, mImageView_Portrait;
    private FragmentTransaction fragTransaction;
    private FragmentManager fragmentManager;
    private ArrayList<Gallery_Model> listOfAllImages = new ArrayList<Gallery_Model>();
    private String url_images;
    String getParam1;
    String getParam2 = "";
    MySingleTon mySingleTon = MySingleTon.getInstance();
    Create_Class mCreate_Class;
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    int checkcheck;
    // show dialog Gallery
    RecyclerView mRecyclerview_Gallery;
    List<Gallery_Model> modelList;
    Adapter_Load_Gallery mAdapter;
    private MySingleTon mSingeton = MySingleTon.getInstance();
    private Button mDismiss;

    public Signin_Fragment_Finish() {
        // Required empty public constructor
    }

    public static Signin_Fragment_Finish newInstance(String param1, String param2) {
        Signin_Fragment_Finish fragment = new Signin_Fragment_Finish();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        Log.e("CHECK__3", "" + param1);
        Log.e("CHECK__3", "" + param2);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signin_fragment_finish, container, false);
        getParam1 = getArguments().getString(ARG_PARAM1);
        getParam2 = getArguments().getString(ARG_PARAM2);

        addControll(view);
        addEvent();
        return view;
    }

    private void addEvent() {
        mButton_Back_Finish.setOnClickListener(this);
        mButton_Submit_Finish.setOnClickListener(this);
        mImageView_Passport.setOnClickListener(this);
        mImageView_Portrait.setOnClickListener(this);

//        Glide.with(getActivity()).load(  "file:///storage/emulated/0/Pic.jpg")
//                .placeholder(R.drawable.ic_image_photo).centerCrop()
//                .into(mImageView_Portrait);
//        Glide.with(getActivity()).load(mySingleTon.getUrl_images_portrait())
//                .placeholder(R.drawable.ic_image_photo).centerCrop()
//                .into(mImageView_Portrait);
//        Glide.with(getActivity()).load(mySingleTon.getUrl_images_passport())
//                .placeholder(R.drawable.ic_image_photo).centerCrop()
//                .into(mImageView_Passport);


    }


    private void addControll(View view) {
        mButton_Back_Finish = view.findViewById(R.id.mButton_Back_Finish);
        mButton_Submit_Finish = view.findViewById(R.id.mButton_Submit_Finish);
        mImageView_Passport = view.findViewById(R.id.mImageView_Passport);
        mImageView_Portrait = view.findViewById(R.id.mImageView_Portrait);
        mCreate_Class = new Create_Class();
        modelList = new ArrayList<>();
        modelList.add(new Gallery_Model("", false));
        modelList.addAll(getAllShownImagesPath());
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mButton_Back_Finish:
                fragmentManager = getActivity().getSupportFragmentManager();
                fragTransaction = fragmentManager.beginTransaction();
                Signin_Fragment_Peronal Fragment_2 = Signin_Fragment_Peronal.newInstance("", "");
                Bundle bundle1 = new Bundle();
                Fragment_2.setArguments(bundle1);
                fragTransaction.replace(R.id.ll_Acount_Fragment_1, Fragment_2);
                fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTransaction.commit();
                break;
            case R.id.mButton_Submit_Finish:
//                ShowDialog();
                mCreate_Class.PushDataSgnin(getActivity());
                break;
            case R.id.mImageView_Passport:
//                showFragmentGallery(0);
                showDialog_Gallery(0);
                checkcheck = 0;
                break;
            case R.id.mImageView_Portrait:
//                showFragmentGallery(1);
                showDialog_Gallery(1);
                checkcheck = 1;
                break;

        }
    }

    private void ShowDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_authentication_success, null);
        ImageView mImageView = dialogView.findViewById(R.id.mImageView_Exit);

        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("email", mySingleTon.getEmail_Account());
                intent.putExtra("password", mySingleTon.getPassword_Account());
                startActivity(intent);
                getActivity().finish();
                alertDialog.dismiss();
                mySingleTon.setCountry_Personal("");
                mySingleTon.setGender_Personal("");
                mySingleTon.setDate_of_Birth_Personal("");
                mySingleTon.setFull_Name_Personal("");
                mySingleTon.setWallet_Finish("");
                mySingleTon.setAdress_Finish("");
                mySingleTon.setPhone_Account("");
            }
        });
    }


    private void showDialog_Gallery(final int check) {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_gallery, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        mRecyclerview_Gallery = dialogView.findViewById(R.id.mRecyclerview_Gallery);
//        mDismiss = dialogView.findViewById(R.id.mDismiss);
        mRecyclerview_Gallery.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mAdapter = new Adapter_Load_Gallery(getActivity(), modelList, check);
        mRecyclerview_Gallery.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        alertDialog.show();
        mAdapter.setOnItemClickListener(new OnItemClickListenner() {
            @Override
            public void OnClickItem(View view, int position) {
                if (position == 0) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photo));
                    imageUri = Uri.fromFile(photo);
                    startActivityForResult(intent, TAKE_PICTURE);
                    alertDialog.dismiss();
                } else {
                    if (check == 0) {
                        Glide.with(getActivity()).load(modelList.get(position).getImages())
                                .placeholder(R.drawable.ic_image_photo).centerCrop()
                                .into(mImageView_Passport);
                    } else {
                        Glide.with(getActivity()).load(modelList.get(position).getImages())
                                .placeholder(R.drawable.ic_image_photo).centerCrop()
                                .into(mImageView_Portrait);
                    }
                    alertDialog.dismiss();
                }

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getActivity().getContentResolver().notifyChange(selectedImage, null);
                    ContentResolver cr = getActivity().getContentResolver();
                    Bitmap bitmap;
                    Log.e("__________", "+++++++++++++");
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);
                        if (checkcheck == 0) {
                            mImageView_Passport.setImageBitmap(bitmap);
                        } else {
                            mImageView_Portrait.setImageBitmap(bitmap);
                        }
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }

                }
        }
//        Toast.makeText(getActivity(), "DONE_TAKE_PHOTO", Toast.LENGTH_SHORT).show();

    }


    public ArrayList<Gallery_Model> getAllShownImagesPath() {
        listOfAllImages = new ArrayList<>();
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = getActivity().getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
//        listOfAllImages.add(new Gallery_Model("", false));
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            listOfAllImages.add(new Gallery_Model(absolutePathOfImage, false));
        }
        return listOfAllImages;
    }


    private void showFragmentGallery(final int showGallery) {
        Log.e("SHOW_GALLERY", showGallery + "");
        if (showGallery == 0) {
            Log.e("PASSPORT ", "TRUE");
            Toast.makeText(getActivity(), "Fragment_Passport", Toast.LENGTH_SHORT).show();
            Fragment_Gallery mFragment_Gallery = Fragment_Gallery.newInstance("", showGallery);
            fragmentManager = getActivity().getSupportFragmentManager();
            fragTransaction = fragmentManager.beginTransaction();
            fragTransaction.replace(R.id.ll_Acount_Fragment_1, mFragment_Gallery);
            fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragTransaction.commit();
        } else {
            Log.e("PORTRAIT ", "TRUE");
            Toast.makeText(getActivity(), "Fragment_PoitTrait", Toast.LENGTH_SHORT).show();
            Fragment_Gallery mFragment_Galleryd = Fragment_Gallery.newInstance("", showGallery);
            fragmentManager = getActivity().getSupportFragmentManager();
            fragTransaction = fragmentManager.beginTransaction();
            fragTransaction.replace(R.id.ll_Acount_Fragment_1, mFragment_Galleryd);
            fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragTransaction.commit();
        }

    }


}
