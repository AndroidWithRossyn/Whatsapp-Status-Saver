package com.Udaicoders.wawbstatussaver.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class FileUtils {

    private static FileUtils fileUtils;

    public static FileUtils getInstance() {
        if (fileUtils == null) {
            fileUtils = new FileUtils();
        }
        return fileUtils;
    }

    private FileUtils() {
    }

    public static void createFolder(String folderName) {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + folderName);
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            // Do something on success
        } else {
            // Do something else on failure
        }
    }

    /**
     * Delete recursive content from folder
     *
     * @param path directory path
     */
    public void deleteRecursiveFiles(String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File dir = new File(Environment.getExternalStorageDirectory() + File.separator + path);
                    if (dir.isDirectory()) {
                        recursiveRemove(dir);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Delete recursive content from folder
     *
     * @param dir directory path
     */
    public void deleteRecursiveFiles(File dir) {
        try {
            if (dir.isDirectory()) {
                recursiveRemove(dir);
            } else if (dir.isFile()) {
                recursiveRemove(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean recursiveRemove(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] list = file.listFiles();

            if (list != null) {

                for (File item : list) {
                    recursiveRemove(item);
                }
            }
        }
        if (file.exists()) {
            file.delete();
        }
        return !file.exists();
    }


    /**
     * byte to kb and mb
     *
     * @param totalBytes
     * @param downloadedBytes
     * @return
     */
    public String byteToKbAndMb(long totalBytes, long downloadedBytes) {
        String totalSize = getMbKbFormat(totalBytes);
        String downloadSize = getMbKbFormat(downloadedBytes);
        return downloadSize + "/" + totalSize + " MB";
    }

    /**
     * get mb kb format
     *
     * @param bytes
     * @return
     */
    private String getMbKbFormat(long bytes) {
        String format = "";
        double convertMb = bytes * 0.000001;
//        if (convertMb > 1) {
        format = String.format("%.2f", convertMb);
//        } else {
//            double convertKb = bytes * 0.001;
//            format = String.format("%.2f", convertKb) + " Kb";
//        }
        return format;
    }

    /**
     * copy file
     *
     * @param src
     * @param dst
     * @throws IOException
     */
    public final synchronized void copyFile(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
                out = null;
            }
            Log.e("copy_file", "from : " + src.getAbsolutePath() + " to : " + dst.getAbsolutePath());
        } finally {
            in.close();
            in = null;
        }
    }

}
