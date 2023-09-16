package com.Udaicoders.wawbstatussaver.intro;

import static com.Udaicoders.wawbstatussaver.util.Utils.hasPermissions;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.Udaicoders.wawbstatussaver.MainActivity;
import com.Udaicoders.wawbstatussaver.R;
import com.Udaicoders.wawbstatussaver.util.Utils;


public class IntroActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private TextView btnNext;//btnSkip
    //    private PrefManager prefManager;
    TextView storage, noti;
//    ImageView goIV;

    String[] permissionsList = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};


    String[] permissionsList13 = new String[]{Manifest.permission.READ_MEDIA_IMAGES,
             Manifest.permission.READ_MEDIA_VIDEO};
    private final int REQUEST_CODE_NOTIFICATION_LISTENER = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        prefManager = new PrefManager(this);
//        if (!prefManager.isFirstTimeLaunch()) {
//            launchHomeScreen();
//            finish();
//        }


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_intro);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
//        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (TextView) findViewById(R.id.btn_next);


        layouts = new int[]{
                R.layout.slide1,
                R.layout.slide2,
                R.layout.slide3,
                R.layout.slide4,};


        addBottomDots(0);


        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

//        btnSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchHomeScreen();
//            }
//        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                {
                    if (current == 4 && !hasPermissions(IntroActivity.this, permissionsList13) && !Utils.isNotificationServiceRunning(IntroActivity.this)) {
                        Toast.makeText(IntroActivity.this, "Allow both permissions", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(IntroActivity.this, permissionsList13, 211);
                        return;
                    }
                }
                else
                {
                    if (current == 4 && !hasPermissions(IntroActivity.this, permissionsList) && !Utils.isNotificationServiceRunning(IntroActivity.this)) {
                        Toast.makeText(IntroActivity.this, "Allow both permissions", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(IntroActivity.this, permissionsList, 21);
                        return;
                    }
                }

                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 21) {
            if (hasPermissions(this, permissionsList)) {
                Toast.makeText(this, "Allow permission to storage access11", Toast.LENGTH_SHORT).show();
            } else {
                storage.setText("Granted");
                storage.setClickable(false);
            }
        }
        if (requestCode == 211) {

            if (hasPermissions(this, permissionsList13)) {
                Toast.makeText(this, "Allow permission to storage access13", Toast.LENGTH_SHORT).show();
            } else {
                storage.setText("Granted");
                storage.setClickable(false);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_NOTIFICATION_LISTENER) {
            if (Utils.isNotificationServiceRunning(this)) {
                noti.setText("Granted");
                noti.setClickable(false);
            } else {
                Toast.makeText(this, "Allow permission to read messages", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
//            dots[i].setWidth(35);
//            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) dots[i].getLayoutParams();
//            params.height = 70;
//            dots[i].setLayoutParams(params);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
//        prefManager.setFirstTimeLaunch(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && hasPermissions(this, permissionsList13)) {
            Toast.makeText(this, "Allow both permissions", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, permissionsList13, 211);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU && hasPermissions(this, permissionsList)) {
            Toast.makeText(this, "Allow both permissions", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, permissionsList, 21);
        } else {
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
            finish();
        }

    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // mengubah button lanjut 'NEXT' / 'GOT IT'
//            if (position == layouts.length - 1) {
            // last page. make button text to GOT IT
//                btnNext.setText(getResources().getString(R.string.start));
//                btnSkip.setVisibility(View.GONE);
//            } else {
            // still pages are left
//                btnNext.setText(getString(R.string.next));
//                btnSkip.setVisibility(View.VISIBLE);
//            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = null;
            switch (position) {
                case 0:
                    view = layoutInflater.inflate(R.layout.slide1, container, false);
//                    goIV = view.findViewById(R.id.goIV);
//                    goIV.setOnClickListener(v -> {
//                        int current = getItem(+1);
//                        if (current < layouts.length) {
//                            // move to next screen
//                            viewPager.setCurrentItem(current);
//                        }
//                    });
                    break;

                case 1:
                    view = layoutInflater.inflate(R.layout.slide2, container, false);
                    break;

                case 2:
                    view = layoutInflater.inflate(R.layout.slide3, container, false);
                    break;

                case 3:
                    view = layoutInflater.inflate(R.layout.slide4, container, false);
                    storage = view.findViewById(R.id.storage);
                    storage.setOnClickListener(v -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                        {
                            ActivityCompat.requestPermissions(IntroActivity.this, permissionsList13, 211);
                        }
                        else
                        {
                            ActivityCompat.requestPermissions(IntroActivity.this, permissionsList, 21);
                        }

                    });

                    noti = view.findViewById(R.id.noti);
                    noti.setOnClickListener(v -> {
                        startActivityForResult(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS), REQUEST_CODE_NOTIFICATION_LISTENER);
                    });

                    break;

//                case 4:
//                    view = layoutInflater.inflate(R.layout.slide5, container, false);
//
//                    break;
            }
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}