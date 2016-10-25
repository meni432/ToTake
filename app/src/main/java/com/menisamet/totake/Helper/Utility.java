package com.menisamet.totake.Helper;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.menisamet.totake.MainActivity;
import com.menisamet.totake.SignInActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by meni on 04/09/16.
 */
public class Utility {

    public static final String EXTRA_NEXT_ACTIVITY = "extra_next_activity";
    public static final String EXTRA_LOG_OUT = "extra_log_out";

    public static void checkAuthAndGoToActivity(Context context, Class nextActivity) {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.putExtra(EXTRA_NEXT_ACTIVITY, nextActivity);
        context.startActivity(intent);
//        Intent intent = new Intent(context, nextActivity);
//        context.startActivity(intent);
    }

    public static void logOutUser(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.putExtra(EXTRA_NEXT_ACTIVITY, MainActivity.class);
        intent.putExtra(EXTRA_LOG_OUT, true);
        context.startActivity(intent);
    }


    public static void showToast(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }


    public static String saveToInternalStorage(Context context, Bitmap bitmapImage, String imageName) {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, imageName+".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }


    public static void loadImageFromStorage(ImageView imageView, String path, String imageName) {

        try {
            File f = new File(path, imageName+".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img = imageView;
            img.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static boolean saveImageToInternalStorage(Context context, Bitmap image) {

        try {
            // Use the compress method on the Bitmap object to write image to
            // the OutputStream
            FileOutputStream fos = context.openFileOutput("desiredFilename.png", Context.MODE_PRIVATE);

            // Writing the bitmap to the output stream
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

            return true;
        } catch (Exception e) {
            Log.e("saveToInternalStorage()", e.getMessage());
            return false;
        }
    }


}
