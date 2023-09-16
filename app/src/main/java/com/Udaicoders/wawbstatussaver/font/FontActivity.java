package com.Udaicoders.wawbstatussaver.font;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.ironsource.mediationsdk.IronSource;
import com.Udaicoders.wawbstatussaver.R;
import com.Udaicoders.wawbstatussaver.font.fragment.PrettifyFragment;
import com.Udaicoders.wawbstatussaver.font.fragment.EmojiFragment;
import com.Udaicoders.wawbstatussaver.font.fragment.FontFragment;
import com.Udaicoders.wawbstatussaver.util.AdController;
import com.Udaicoders.wawbstatussaver.util.SharedPrefs;
import com.Udaicoders.wawbstatussaver.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class FontActivity extends AppCompatActivity {

    ImageView navIV;
    TabLayout tabLayout;
    ViewPager viewPager;
    String[] tabs;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setLanguage(FontActivity.this, SharedPrefs.getLang(FontActivity.this));
        setContentView(R.layout.activity_font);

        navIV = findViewById(R.id.navIV);
        navIV.setOnClickListener(view -> {onBackPressed();});

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabs = new String[3];
        tabs[0] = getResources().getString(R.string.fancy);
        tabs[1] = getResources().getString(R.string.prettify);
        tabs[2] = getResources().getString(R.string.emoji);
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

        container = findViewById(R.id.banner_container);
        if (AdController.isLoadIronSourceAd){
            AdController.inItIron(FontActivity.this);
        }else {
            /*admob*/
            AdController.loadBannerAd(FontActivity.this, container);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AdController.isLoadIronSourceAd) {
            AdController.destroyIron();
            AdController.ironBanner(FontActivity.this, container);
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

    ViewPagerAdapter adapter;

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(
                getSupportFragmentManager());

        adapter.addFragment(new FontFragment(), getResources().getString(R.string.fancy));
        adapter.addFragment(new PrettifyFragment(), getResources().getString(R.string.prettify));
        adapter.addFragment(new EmojiFragment(), getResources().getString(R.string.emoji));

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

    private void setupTabIcons() {
        View v = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView txt = v.findViewById(R.id.tab);
        txt.setText(tabs[0]);
        txt.setTextColor(getResources().getColor(R.color.tab_txt_press));
        txt.setBackgroundResource(R.drawable.press_tab);
        FrameLayout.LayoutParams tabp = new FrameLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels * 340 / 1080,
                getResources().getDisplayMetrics().heightPixels * 140 / 1920);
        txt.setLayoutParams(tabp);
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.setCustomView(null);
        tab.setCustomView(v);
    }

    public View getTabView(int pos) {
        View v = LayoutInflater.from(FontActivity.this).inflate(R.layout.custom_tab, null);
        TextView txt = v.findViewById(R.id.tab);
        txt.setText(tabs[pos]);
        txt.setTextColor(getResources().getColor(R.color.tab_txt_press));
        txt.setBackgroundResource(R.drawable.press_tab);
        FrameLayout.LayoutParams tab = new FrameLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels * 340 / 1080,
                getResources().getDisplayMetrics().heightPixels * 140 / 1920);
        txt.setLayoutParams(tab);
        return v;
    }

    public View getTabViewUn(int pos) {
        View v = LayoutInflater.from(FontActivity.this).inflate(R.layout.custom_tab, null);
        TextView txt = v.findViewById(R.id.tab);
        txt.setText(tabs[pos]);
        txt.setTextColor(getResources().getColor(R.color.tab_txt_unpress));
        txt.setBackgroundResource(R.drawable.unpress_tab);
        FrameLayout.LayoutParams tab = new FrameLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels * 340 / 1080,
                getResources().getDisplayMetrics().heightPixels * 140 / 1920);
        txt.setLayoutParams(tab);
        return v;
    }

}