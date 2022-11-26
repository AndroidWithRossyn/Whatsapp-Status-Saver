package com.banrossyn.socialsaver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.banrossyn.socialsaver.databinding.ActivityGalleryBinding;
import com.banrossyn.socialsaver.fragments.WhatsAppDowndlededFragment;
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.interstitial.InterstitialAd;
//import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;
import static com.banrossyn.socialsaver.util_items.Utils.createFileFolder;

public class GalleryActivity  extends AppCompatActivity {
    GalleryActivity activity;
    ActivityGalleryBinding binding;
//    private AdView adView;
//    private InterstitialAd interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery);
        activity = this;


//        loadAd();
//        BannerAd();
        initViews();
    }

    public void initViews() {
        setupViewPager(binding.viewpager);
        binding.tabs.setupWithViewPager(binding.viewpager);

        binding.imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        for (int i = 0; i < binding.tabs.getTabCount(); i++) {
            TextView tv=(TextView) LayoutInflater.from(activity).inflate(R.layout.custom_tab,null);
            binding.tabs.getTabAt(i).setCustomView(tv);
        }

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        createFileFolder();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(activity.getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new WhatsAppDowndlededFragment(), "Whatsapp");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



//    private void BannerAd() {
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView = findViewById(R.id.adView);
//        adView.loadAd(adRequest);
//        adView.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//            }
//
//            @Override
//            public void onAdFailedToLoad(LoadAdError adError) {
//                super.onAdFailedToLoad(adError);
//                adView.loadAd(adRequest);
//            }
//
//            @Override
//            public void onAdClosed() {
//
//                super.onAdClosed();
//            }
//        });
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (interstitialAd != null) {
//            interstitialAd.show(this);
//        }  else {
//            super.onBackPressed();
//        }
//
//    }
//
//    public void loadAd() {
//        AdRequest adRequest = new AdRequest.Builder().build();
//        InterstitialAd.load(
//                this,
//                getString(R.string.InterstitialAd),
//                adRequest,
//                new InterstitialAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//                        GalleryActivity.this.interstitialAd = interstitialAd;
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        interstitialAd = null;
//                    }
//                });
//    }

}
