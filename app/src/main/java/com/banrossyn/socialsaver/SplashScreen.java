package com.banrossyn.socialsaver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

//import com.fotoricdownloader.kvy.R;
//import com.fotoricdownloader.kvy.util.AppLangSessionManager;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;

public class SplashScreen extends AppCompatActivity {
    SplashScreen activity;
    Context context;
    AppUpdateManager appUpdateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        context = activity = this;
        appUpdateManager = AppUpdateManagerFactory.create(context);
        UpdateApp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;


        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                try {
                    appUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo, IMMEDIATE, activity, 101);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void HomeScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, WhatsappActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);

    }

    public void UpdateApp() {
        try {
            Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
            appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(IMMEDIATE)) {
                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                appUpdateInfo, IMMEDIATE, activity, 101);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                } else {
                    HomeScreen();
                }
            }).addOnFailureListener(e -> {
                e.printStackTrace();
                HomeScreen();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode != RESULT_OK) {
                HomeScreen();
            } else {
                HomeScreen();
            }
        }
    }

}
