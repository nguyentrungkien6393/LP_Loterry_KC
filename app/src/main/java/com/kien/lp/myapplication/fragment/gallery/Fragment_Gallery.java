package com.kien.lp.myapplication.fragment.gallery;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.adapter.Adapter_Load_Gallery;
import com.kien.lp.myapplication.adapter.OnItemClickListenner;
import com.kien.lp.myapplication.fragment.Fragment_Choose_2;
import com.kien.lp.myapplication.fragment.Signin_Fragment_Finish;
import com.kien.lp.myapplication.fragment.Signin_Fragment_Peronal;
import com.kien.lp.myapplication.model.Gallery_Model;
import com.kien.lp.myapplication.mysingleton.MySingleTon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Fragment_Gallery extends Fragment {
//    private static final int REQUEST_IMAGE_CAPTURE = 999;
    RecyclerView mRecyclerview_Gallery;
    List<Gallery_Model>modelList;
    Adapter_Load_Gallery mAdapter;
    private MySingleTon mSingeton = MySingleTon.getInstance();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    private ImageView mImageView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragTransaction;
    int check_Fragment;
    ArrayList<Gallery_Model> listOfAllImages = new ArrayList<Gallery_Model>();
    public Fragment_Gallery() {
        // Required empty public constructor
    }

    public static Fragment_Gallery newInstance(String param1, int param2) {
        Fragment_Gallery mFragment = new Fragment_Gallery();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        mFragment.setArguments(args);

        return mFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        check_Fragment = getArguments().getInt(ARG_PARAM2);
        Log.e("CHECK_ACCOUNT",check_Fragment+"");
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        addControll(view,check_Fragment);


        return view ;
    }


    private void addControll(View view,final int Check) {
        modelList = new ArrayList<>();
        modelList.add(new Gallery_Model("",false));
        modelList.addAll(getAllShownImagesPath());
        mRecyclerview_Gallery = view.findViewById(R.id.mReyclerview_Gallery);
            mImageView = view.findViewById(R.id.mImageView);
        mRecyclerview_Gallery.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mAdapter = new Adapter_Load_Gallery(getActivity(),modelList,Check);
        mRecyclerview_Gallery.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(new OnItemClickListenner() {
            @Override
            public void OnClickItem(View view, int position) {
                Log.e("_________","____________"+position);
                XulyView();
            }
        });
    }

    public ArrayList<Gallery_Model> getAllShownImagesPath() {
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
        listOfAllImages.add(new Gallery_Model("",false));
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            listOfAllImages.add(new Gallery_Model(absolutePathOfImage,false));
        }
        return listOfAllImages;
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
                    Log.e("__________","+++++++++++++");
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);
                        mImageView.setImageBitmap(bitmap);
                        Log.e("TAG_CHECK_IMAMGE",selectedImage.toString());
                        Toast.makeText(getActivity(), imageUri.toString(),
                                Toast.LENGTH_LONG).show();
                      addFragment(selectedImage.toString());
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }

                }
        }
        Toast.makeText(getActivity(), "DONE_TAKE_PHOTO", Toast.LENGTH_SHORT).show();

    }



    private void addFragment(String uri) {
        System.out.println("addFragment :" + uri);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragTransaction = fragmentManager.beginTransaction();
        Signin_Fragment_Finish Fragment_2 = Signin_Fragment_Finish.newInstance("", uri);
        Bundle bundle1 = new Bundle();
        Fragment_2.setArguments(bundle1);
        fragTransaction.replace(R.id.ll_Acount_Fragment_1, Fragment_2);
        fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragTransaction.commit();

    }
    //dc chua a???

    private void XulyView() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        Log.e("66666666666666",imageUri.toString());
        if (check_Fragment==0){
            mSingeton.setUrl_images_portrait(imageUri.toString());
        }else {
            mSingeton.setUrl_images_passport(imageUri.toString());
        }
        startActivityForResult(intent, TAKE_PICTURE);
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
//        intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                Uri.fromFile(photo));
//        imageUri = Uri.fromFile(photo);d
//       getActivity().startActivityForResult(intent, TAKE_PICTURE);

    }
}
