package com.Udaicoders.wawbstatussaver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ironsource.mediationsdk.IronSource;
import com.Udaicoders.wawbstatussaver.fragment.MyPhotos;
import com.Udaicoders.wawbstatussaver.fragment.MyVideos;
import com.Udaicoders.wawbstatussaver.util.AdController;
import com.Udaicoders.wawbstatussaver.util.SharedPrefs;
import com.Udaicoders.wawbstatussaver.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class MyStatusActivity extends AppCompatActivity {

    ImageView backIV;

    TabLayout tabLayout;
    ViewPager viewPager;
    String[] tabs;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_status);

        Utils.setLanguage(MyStatusActivity.this, SharedPrefs.getLang(MyStatusActivity.this));

        backIV = findViewById(R.id.backIV);
        backIV.setOnClickListener(v -> onBackPressed());

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabs = new String[2];
        tabs[0] = getResources().getString(R.string.photos);
        tabs[1] = getResources().getString(R.string.videos);
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(getTabViewUn(i));
        }
        setupTabIcons();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                TabLayout.Tab tabs = tabLayout.getTabAt(tab.getPosition());
                tabs.setCustomView(null);
                tabs.setCustomView(getTabView(tab.getPosition()));

//                AdController.adCounter++;
//                if (AdController.isLoadIronSourceAd){
//                    AdController.ironShowInterstitial(MyStatusActivity.this, null, 0);
//                }else {
//                    AdController.showInterAd(MyStatusActivity.this, null, 0);
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TabLayout.Tab tabs = tabLayout.getTabAt(tab.getPosition());
                tabs.setCustomView(null);
                tabs.setCustomView(getTabViewUn(tab.getPosition()));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /*admob*/
        container = findViewById(R.id.banner_container);
        if (!AdController.isLoadIronSourceAd) {
            AdController.loadBannerAd(MyStatusActivity.this, container);
            AdController.loadInterAd(MyStatusActivity.this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AdController.isLoadIronSourceAd) {
            AdController.destroyIron();
            AdController.ironBanner(MyStatusActivity.this, container);
            // call the IronSource onResume method
            IronSource.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (AdController.isLoadIronSourceAd) {
            // call the IronSource onPause method
            IronSource.onPause(this);
        }
    }


    private void setupTabIcons() {
        View v = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView txt = v.findViewById(R.id.tab);
        txt.setText(tabs[0]);
        txt.setTextColor(getResources().getColor(R.color.tab_txt_press));
        txt.setBackgroundResource(R.drawable.press_tab);
        FrameLayout.LayoutParams tabp = new FrameLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels * 438 / 1080,
                getResources().getDisplayMetrics().heightPixels * 140 / 1920);
        txt.setLayoutParams(tabp);
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.setCustomView(null);
        tab.setCustomView(v);
    }

    public View getTabView(int pos) {
        View v = LayoutInflater.from(MyStatusActivity.this).inflate(R.layout.custom_tab, null);
        TextView txt = v.findViewById(R.id.tab);
        txt.setText(tabs[pos]);
        txt.setTextColor(getResources().getColor(R.color.tab_txt_press));
        txt.setBackgroundResource(R.drawable.press_tab);
        FrameLayout.LayoutParams tab = new FrameLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels * 438 / 1080,
                getResources().getDisplayMetrics().heightPixels * 140 / 1920);
        txt.setLayoutParams(tab);
        return v;
    }

    public View getTabViewUn(int pos) {
        View v = LayoutInflater.from(MyStatusActivity.this).inflate(R.layout.custom_tab, null);
        TextView txt = v.findViewById(R.id.tab);
        txt.setText(tabs[pos]);
        txt.setTextColor(getResources().getColor(R.color.tab_txt_unpress));
        txt.setBackgroundResource(R.drawable.unpress_tab);
        FrameLayout.LayoutParams tab = new FrameLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels * 438 / 1080,
                getResources().getDisplayMetrics().heightPixels * 140 / 1920);
        txt.setLayoutParams(tab);
        return v;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(
                getSupportFragmentManager());

        adapter.addFragment(new MyPhotos(), "Photos");
        adapter.addFragment(new MyVideos(), "Videos");

        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            return this.mFragmentList.get(arg0);
        }

        @Override
        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return this.mFragmentTitleList.get(position);
        }
    }
}
