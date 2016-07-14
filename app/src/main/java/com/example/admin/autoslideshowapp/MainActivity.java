package com.example.admin.autoslideshowapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
//import java.util.logging.Handler;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int PERMISSIONS_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Android 6.0以降の場合
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        //    //パーミッションの許可状態を確認する
        //    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        //        //許可されている
        //        getContentsInfo();
        //    } else {
        //        //許可されていないので許可ダイアログを表示する
        //        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_CODE);
        //    }
        //    //Android5.0系以下の場合
        //} else {
            getContentsInfo();
        //}

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);

    }

    //画像の情報を取得する
    ContentResolver resolver = getContentResolver();
    Cursor cursor = resolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,   //データの種類
            null,   //項目
            null,   // フィルタ条件
            null,   //フィルタ用パラメータ
            null    //ソート
    );

    @Override
    public void onClick(View v){

        if (v.getId() == R.id.button1) {
            //戻るボタンの処理
            getPreviousContentsInfo();

        } else if(v.getId() == R.id.button2) {
            //進むボタンの処理
            getNextContentsInfo();

        //} else if(v.getId() == R.id.button3) {
            //再生／停止ボタンの処理

        }
    }


    //初期表示
    public void getContentsInfo() {

        if (cursor.moveToFirst()) {
            //do {
            // indexからIDを取得し、そのIDから画像のURIを取得する
            int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = cursor.getLong(fieldIndex);
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);

            Log.d("ANDROID", "URI : " + id.toString());
            Log.d("ANDROID", "URI : " + imageUri.toString());
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageURI(imageUri);
            //
            //}while(cursor.moveToNext());
        }
        //cursor.close();
    }


    //戻る処理
    private void getPreviousContentsInfo() {

        //画像の情報を取得する
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,   //データの種類
                null,   //項目
                null,   // フィルタ条件
                null,   //フィルタ用パラメータ
                null    //ソート
        );

        if (cursor.moveToNext()) {

            // indexからIDを取得し、そのIDから画像のURIを取得する
            int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = cursor.getLong(fieldIndex) - 1;
            //id = id -1;
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);

            Log.d("ANDROID", "URI : " + id.toString());
            Log.d("ANDROID", "URI : " + imageUri.toString());
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageURI(imageUri);

        }
        cursor.close();
    }


    //進む処理
    private void getNextContentsInfo() {

        //画像の情報を取得する
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,   //データの種類
                null,   //項目
                null,   // フィルタ条件
                null,   //フィルタ用パラメータ
                null    //ソート
        );

        if (cursor.moveToNext()) {

            // indexからIDを取得し、そのIDから画像のURIを取得する
            int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = cursor.getLong(fieldIndex) + 1;
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);

            Log.d("ANDROID", "URI : " + id.toString());
            Log.d("ANDROID", "URI : " + imageUri.toString());
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageURI(imageUri);

        }
        cursor.close();
    }


    //スライドショー表示
    private void getRepeatContentsInfo() {

        //画像の情報を取得する
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,   //データの種類
                null,   //項目
                null,   // フィルタ条件
                null,   //フィルタ用パラメータ
                null    //ソート
        );

        Timer timer = null;
        Handler handle = new Handler();

        if (timer == null) {
            timer = new Timer();
            //timer.schedule(new MyTimer(), 2000); // ミリ秒でセット
        }
        class MyTimer extends TimerTask {
            @Override
            public void run() {
                //handle.post(new Runnable() {


            }
        }


        if (cursor.moveToFirst()) {
            do {
                // indexからIDを取得し、そのIDから画像のURIを取得する
                int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                Long id = cursor.getLong(fieldIndex);
                Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);

                Log.d("ANDROID", "URI : " + id.toString());
                Log.d("ANDROID", "URI : " + imageUri.toString());
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageURI(imageUri);
                //
            }while(cursor.moveToNext());
        }
        cursor.close();

        timer.cancel();
        timer = null;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContentsInfo();
                }
                break;
            default:
                break;
        }
    }


}
