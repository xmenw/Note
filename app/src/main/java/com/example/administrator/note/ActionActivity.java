package com.example.administrator.note;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionActivity extends AppCompatActivity implements View.OnClickListener {
    private int action;
    private Button btnAdd, btnBack;
    private EditText editText;
    private NotePadDB notePadDB;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        action = getIntent().getIntExtra("action", 1);
        notePadDB = new NotePadDB(this);
        database = notePadDB.getWritableDatabase();
        initView();
        doAction();
    }

    private void initView() {
        editText = findViewById(R.id.edit_text);
        btnAdd = findViewById(R.id.btn_add);
        btnBack = findViewById(R.id.btn_back);
        btnAdd.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void doAction() {
        switch (action){
            case 1:

                break;
            default:
                return;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                insertDB();
                finish();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void insertDB() {
        ContentValues value = new ContentValues();
        value.put(NotePadDB.CONTENT, editText.getText().toString());
        value.put(NotePadDB.TIME, formatTime());
        database.insert(NotePadDB.NAME, null, value);
    }

    private String formatTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

}


