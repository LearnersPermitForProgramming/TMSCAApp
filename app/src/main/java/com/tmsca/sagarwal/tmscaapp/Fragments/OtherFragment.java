package com.tmsca.sagarwal.tmscaapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmsca.sagarwal.tmscaapp.R;
import com.tmsca.sagarwal.tmscaapp.Tabs.SectionsPageAdapter;

/**
 * Created by Shobhit on 7/27/2015.
 */
public class OtherFragment extends Fragment {


    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    //onCreateView inflates the layout
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.other, container, false);
        getActivity().setTitle("Other");
        mSectionsPageAdapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());
        mViewPager = rootView.findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        tabLayout = rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        return rootView;
    }


    public void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new FeedbackFramgent(), "Feedback");
        adapter.addFragment(new AboutFragment(), "About");
        adapter.addFragment(new ContactFragment(), "Contact");
        viewPager.setAdapter(adapter);
    }


}
