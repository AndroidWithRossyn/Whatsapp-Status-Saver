package com.banrossyn.socialsaver.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.banrossyn.socialsaver.R;
import com.banrossyn.socialsaver.VideoPlayerActivity;
import com.banrossyn.socialsaver.databinding.ItemsWhatsappViewBinding;
import com.banrossyn.socialsaver.all_interfaces.FileListWhatsappClickInterface;
import com.banrossyn.socialsaver.wa_model.WhatsappStatusModel;
import com.banrossyn.socialsaver.util_items.Utils;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static com.banrossyn.socialsaver.util_items.Utils.RootDirectoryWhatsappShow;
import static com.banrossyn.socialsaver.util_items.Utils.createFileFolder;


public class WhatsappStatusAdapter extends RecyclerView.Adapter<WhatsappStatusAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WhatsappStatusModel> fileArrayList;
    private LayoutInflater layoutInflater;
    ProgressDialog dialogProgress;
    String fileName = "";
    public String saveFilePath = RootDirectoryWhatsappShow+ File.separator;
    private FileListWhatsappClickInterface fileListClickInterface;
    public WhatsappStatusAdapter(Context context, ArrayList<WhatsappStatusModel> files) {
        this.context = context;
        this.fileArrayList = files;
        initProgress();
    }

    public WhatsappStatusAdapter(Context context, ArrayList<WhatsappStatusModel> files, FileListWhatsappClickInterface fileListClickInterface) {
        this.context = context;
        this.fileArrayList = files;
        this.fileListClickInterface=fileListClickInterface;
        initProgress();
    }


    @NonNull
    @Override
    public WhatsappStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new ViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.items_whatsapp_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsappStatusAdapter.ViewHolder viewHolder, int i) {
        WhatsappStatusModel fileItem = fileArrayList.get(i);
        if (fileItem.getUri().toString().endsWith(".mp4")){
            viewHolder.binding.ivPlay.setVisibility(View.VISIBLE);
        }else {
            viewHolder.binding.ivPlay.setVisibility(View.GONE);
        }
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.Q){
            Glide.with(context)
                    .load(fileItem.getUri())
                    .into(viewHolder.binding.pcw);
        }else {
            Glide.with(context)
                    .load(fileItem.getPath())
                    .into(viewHolder.binding.pcw);
        }


        viewHolder.binding.tvDownload.setOnClickListener(view -> {
            createFileFolder();

            if (Build.VERSION.SDK_INT>Build.VERSION_CODES.Q){
                try {
                    if (fileItem.getUri().toString().endsWith(".mp4")) {
                        fileName= "status_Saver_"+System.currentTimeMillis()+".mp4";
                        new DownloadFileTask().execute(fileItem.getUri().toString());
                    }else {
                        fileName = "status_Saver_"+System.currentTimeMillis()+".png";
                        new DownloadFileTask().execute(fileItem.getUri().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                final String path = fileItem.getPath();
                String filename = path.substring(path.lastIndexOf("/") + 1);
                final File file = new File(path);
                File destFile = new File(saveFilePath);
                try {
                    FileUtils.copyFileToDirectory(file, destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String fileNameChange = filename.substring(12);
                File newFile = new File(saveFilePath + fileNameChange);
                String contentType = "image/*";
                if (fileItem.getUri().toString().endsWith(".mp4")) {
                    contentType = "video/*";
                } else {
                    contentType = "image/*";
                }
                MediaScannerConnection.scanFile(context, new String[]{newFile.getAbsolutePath()}, new String[]{contentType},
                        new MediaScannerConnection.MediaScannerConnectionClient() {
                            public void onMediaScannerConnected() {
                                //NA
                            }

                            public void onScanCompleted(String path, Uri uri) {
                                //NA
                            }
                        });

                File from = new File(saveFilePath, filename);
                File to = new File(saveFilePath, fileNameChange);
                from.renameTo(to);
                Toast.makeText(context, context.getResources().getString(R.string.saved_to) + saveFilePath + fileNameChange, Toast.LENGTH_LONG).show();
            }

        });

        viewHolder.binding.ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("PathVideo",fileItem.getUri().toString());
                context.startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return fileArrayList == null ? 0 : fileArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemsWhatsappViewBinding binding;

        public ViewHolder(ItemsWhatsappViewBinding mbinding) {
            super(mbinding.getRoot());
            this.binding = mbinding;
        }
    }

    public void initProgress(){
        dialogProgress = new ProgressDialog(context);
        dialogProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialogProgress.setTitle("Saving");
        dialogProgress.setMessage("Saving. Please wait...");
        dialogProgress.setIndeterminate(true);
        dialogProgress.setCanceledOnTouchOutside(false);
    }

    class DownloadFileTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... furl) {
            try{
                InputStream in = context.getContentResolver().openInputStream(Uri.parse(furl[0]));
                File f =  null;
                f = new File(RootDirectoryWhatsappShow + File.separator + fileName);
                f.setWritable(true, false);
                OutputStream outputStream = new FileOutputStream(f);
                byte buffer[] = new byte[1024];
                int length = 0;

                while((length=in.read(buffer)) > 0) {
                    outputStream.write(buffer,0,length);
                }
                outputStream.close();
                in.close();
            } catch (IOException e) {
                System.out.println("error in creating a file");
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(String... progress) {

        }
        @Override
        protected void onPostExecute(String fileUrl) {
            Utils.setToast(context,context.getResources().getString(R.string.download_complete));
            try {
                if (Build.VERSION.SDK_INT >= 19) {
                    MediaScannerConnection.scanFile(context, new String[]
                                    {new File(RootDirectoryWhatsappShow+File.separator+fileName).getAbsolutePath()},
                            null, (path, uri) -> {
                                //no action
                            });
                } else {
                    context.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED",
                            Uri.fromFile(new File(RootDirectoryWhatsappShow+File.separator+fileName))));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}