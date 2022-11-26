package com.banrossyn.socialsaver.util_items;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.widget.Toast;


import com.banrossyn.socialsaver.R;

import java.io.File;

public class Utils {
    public static Dialog customDialog;
    private static Context context;



    public static File RootDirectoryWhatsappShow = new File(Environment.getExternalStorageDirectory() + "/Download/Social Saver/Whatsapp");




    public Utils(Context _mContext) {
        context = _mContext;
    }

    public static void setToast(Context _mContext, String str) {
        Toast toast = Toast.makeText(_mContext, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void createFileFolder() {
        if (!RootDirectoryWhatsappShow.exists()) {
            RootDirectoryWhatsappShow.mkdirs();
        }


    }

//    public static void showProgressDialog(Activity activity) {
//        System.out.println("Show");
//        if (customDialog != null) {
//            customDialog.dismiss();
//            customDialog = null;
//        }
//        customDialog = new Dialog(activity);
//        LayoutInflater inflater = LayoutInflater.from(activity);
//        View mView = inflater.inflate(R.layout.progress_dialog, null);
//        customDialog.setCancelable(false);
//        customDialog.setContentView(mView);
//        if (!customDialog.isShowing() && !activity.isFinishing()) {
//            customDialog.show();
//        }
//    }
//
//    public static void hideProgressDialog(Activity activity) {
//        System.out.println("Hide");
//        if (customDialog != null && customDialog.isShowing()) {
//            customDialog.dismiss();
//        }
//    }
//
//    public boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//    }
//
//    public static void startDownload(String downloadPath, String destinationPath, Context context, String FileName) {
//        setToast(context, context.getResources().getString(R.string.download_started));
//        Uri uri = Uri.parse(downloadPath); // Path where you want to download file.
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);  // Tell on which network you want to download file.
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);  // This will show notification on top when downloading the file.
//        request.setTitle(FileName+""); // Title for notification.
//        request.setVisibleInDownloadsUi(true);
//        request.setDestinationInExternalPublicDir(DIRECTORY_DOWNLOADS,destinationPath+FileName);  // Storage directory path
//        ((DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request); // This will start downloading
//
//        try {
//            if (Build.VERSION.SDK_INT >= 20) {
//                MediaScannerConnection.scanFile(context, new String[]{new File(DIRECTORY_DOWNLOADS + "/" + destinationPath + FileName).getAbsolutePath()},
//                        null, new MediaScannerConnection.OnScanCompletedListener() {
//                            public void onScanCompleted(String path, Uri uri) {
//                            }
//                        });
//            } else {
//                context.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.fromFile(new File(DIRECTORY_DOWNLOADS + "/" + destinationPath + FileName))));
//            }
//
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//

    public static void shareImage(Context context, String filePath) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, context.getResources().getString(R.string.share_txt));
            String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), filePath, "", null);
            Uri screenshotUri = Uri.parse(path);
            intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
            intent.setType("image/*");
            context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.share_image_via)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void shareImageVideoOnWhatsapp(Context context, String filePath, boolean isVideo) {
        Uri imageUri = Uri.parse(filePath);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setPackage("com.whatsapp");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "");
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        if (isVideo) {
            shareIntent.setType("video/*");
        }else {
            shareIntent.setType("image/*");
        }
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            context.startActivity(shareIntent);
        } catch (Exception e) {
            Utils.setToast(context,context.getResources().getString(R.string.whatsapp_not_installed));
        }
    }

    public static void shareVideo(Context context, String filePath) {
        Uri mainUri = Uri.parse(filePath);
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("video/mp4");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, mainUri);
        sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            context.startActivity(Intent.createChooser(sharingIntent, "Share Video using"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, context.getResources().getString(R.string.no_app_installed), Toast.LENGTH_LONG).show();
        }
    }


    public static void OpenApp(Context context,String Package) {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(Package);
        if (launchIntent != null) {
            context.startActivity(launchIntent);
        } else {
            setToast(context,context.getResources().getString(R.string.app_not_available));
        }
    }
//
//    public static boolean isNullOrEmpty(String s) {
//        return (s == null) || (s.length() == 0) || (s.equalsIgnoreCase("null"))||(s.equalsIgnoreCase("0"));
//    }
//
//    public static List<String> extractUrls(String text) {
//        List<String> containedUrls = new ArrayList<String>();
//        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
//        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
//        Matcher urlMatcher = pattern.matcher(text);
//
//        while (urlMatcher.find()) {
//            containedUrls.add(text.substring(urlMatcher.start(0),
//                    urlMatcher.end(0)));
//        }
//
//        return containedUrls;
//    }

    public static void infoDialog(Context context, String title, String msg){
        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(msg)
                .setPositiveButton(context.getResources().getString(R.string.ok),
                        (dialog, which) -> dialog.dismiss()).create().show();
    }

}