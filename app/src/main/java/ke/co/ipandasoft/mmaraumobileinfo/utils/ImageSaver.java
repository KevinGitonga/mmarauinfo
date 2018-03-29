package ke.co.ipandasoft.mmaraumobileinfo.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Kevin Gitonga on 3/12/2018.
 */

public class ImageSaver implements Target {

    private final String fileName;
    private ImageView imageView;

    public ImageSaver(String name, ImageView imageView) {
        this.fileName = name;
        this.imageView = imageView;
    }

    @Override
    public void onPrepareLoad(Drawable arg0) {
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom arg1) {
        File myDir = null;
        if (isExternalStorageWritable()){

            myDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                    "/Download/" + "Mmarau" + "/");

            if (!myDir.exists()) {
                myDir.mkdir();

                Log.e("ERROR", "Cannot create a directory!");
            }else {
                    myDir.mkdir();
                }


            File file=new File(myDir,fileName);

            try {
                file.createNewFile();
                FileOutputStream ostream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, ostream);
                ostream.close();
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }}
            else{
            Log.e("errorrrr_in_disk","cant_get_disk");
            }

        }


    @Override
    public void onBitmapFailed(Drawable arg0) {
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

}
