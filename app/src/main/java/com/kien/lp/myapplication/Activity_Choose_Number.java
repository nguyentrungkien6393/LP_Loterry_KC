//package com.kien.lp.myapplication;
//
//import android.app.Activity;
//import android.support.annotation.Nullable;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//
//import android.widget.TextView;
//
//import com.kien.lp.myapplication.mysingleton.MySingleTon;
//
//public class Activity_Choose_Number extends AppCompatActivity {
//    MySingleTon mySingleTon = MySingleTon.getInstance();
//    private SectionsPagerAdapter mSectionsPagerAdapter;
//
//    private ViewPager mViewPager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity__choose__number);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        // Create the adapter that will return a fragment for each of the three
//        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//
//        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//
//
//    }
//
//
//
////    public static class PlaceholderFragment extends Fragment {
////
////        private static final String ARG_SECTION_NUMBER = "section_number";
////
////        public PlaceholderFragment() {
////        }
////        public static PlaceholderFragment newInstance(int sectionNumber) {
////            PlaceholderFragment fragment = new PlaceholderFragment();
////            Bundle args = new Bundle();
////            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
////            fragment.setArguments(args);
////            return fragment;
////        }
////
////        @Override
////        public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                                 Bundle savedInstanceState) {
////            View rootView = inflater.inflate(R.layout.fragment_activity__choose__number, container, false);
////            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
////            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
////            return rootView;
////        }
////    }
//
////    public class SectionsPagerAdapter extends FragmentPagerAdapter {
////
////        public SectionsPagerAdapter(FragmentManager fm) {
////            super(fm);
////        }
////
////        @Override
////        public Fragment getItem(int position) {
////            return PlaceholderFragment.newInstance(position + 1);
////        }
////
////        @Override
////        public int getCount() {
////            // Show 3 total pages.
////            return mySingleTon.getChoose_number();
////        }
////
////
////    }
//}
