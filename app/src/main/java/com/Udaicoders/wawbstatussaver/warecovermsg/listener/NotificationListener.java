package com.Udaicoders.wawbstatussaver.warecovermsg.listener;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.Udaicoders.wawbstatussaver.MyApp;
import com.Udaicoders.wawbstatussaver.warecovermsg.db.DeletedMsgDatabaseClient;
import com.Udaicoders.wawbstatussaver.warecovermsg.db.DeletedMsgTable;

import java.text.DateFormat;
import java.util.Date;

public class NotificationListener extends NotificationListenerService {

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
//        Log.e( "onNotificationPosted: ", "start");
        if (sbn.getPackageName().equalsIgnoreCase("com.whatsapp")) {
            Bundle bundle = sbn.getNotification().extras;
//            Log.v("notify_data_json", pojoToJson(bundle));
//            Log.v("notify_data", bundleToString(bundle));

            String date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date());
            String sender = bundle.getString("android.title");
//            Log.v("sender msg data: ", sender);
            String msg = bundle.getString("android.text");
//            Log.v("msg msg data: ", msg);

            long lastRowId = bundle.getLong("last_row_id", -1);
//            boolean isGroupConversation = bundle.getBoolean("android.isGroupConversation", false);

            new Thread(() -> {
                if (!TextUtils.isEmpty(sender) && !TextUtils.isEmpty(msg) && lastRowId != -1) {
                    DeletedMsgTable deletedMsgTable = new DeletedMsgTable(sender, msg, date, null, true);
                    DeletedMsgDatabaseClient.getInstance(MyApp.getAppContext()).getAppDatabase().daoDeletedMsgAccess().
                            insertRecord(deletedMsgTable);
//                    Log.v("notify_data", "insert msg data : " + pojoToJson(deletedMsgTable));
                }
            }).start();
        }
    }


    /**
     * Pojo to Json
     *
     * @param pojoObject
     * @return
     */
    private String pojoToJson(Object pojoObject) {
        return new Gson().toJson(pojoObject);
    }

    private String bundleToString(Bundle bundle) {
        String data = "";
        for (String key : bundle.keySet()) {
            try {
                Object obj = bundle.get(key);
                if (TextUtils.isEmpty(data)) {
                    data = "key : " + key + " value :" + obj;
                } else {
                    data = "\nkey : " + key + " value :" + obj;
                }
            } catch (Exception | Error e) {
                e.printStackTrace();
            }
        }
        return data;
    }

}
