package com.example.administrator.note;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.awt.font.TextAttribute;

public class LookNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private Button delete, back, edit;
    private NotePadDB notePadDB;
    private SQLiteDatabase database;
    private String pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_note);
        initView();
        delete.setOnClickListener(this);
        back.setOnClickListener(this);
        edit.setOnClickListener(this);
        notePadDB = new NotePadDB(this);
        database = notePadDB.getWritableDatabase();
        pos = getIntent().getStringExtra(NotePadDB.ID);
        String content = getIntent().getStringExtra(NotePadDB.CONTENT);
        textView.setText(content);
    }

    private void initView() {
        textView = findViewById(R.id.look_text);
        delete = findViewById(R.id.delete);
        back = findViewById(R.id.back);
        edit = findViewById(R.id.edit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete:
                //deleteByPos();
                Intent intent1 = new Intent(LookNoteActivity.this, MyService.class);
                intent1.putExtra("pos", pos);
                startService(intent1);
                finish();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.edit:
                Intent intent = new Intent(LookNoteActivity.this, EditTextActivity.class);
                intent.putExtra(NotePadDB.ID, getIntent().getStringExtra(NotePadDB.ID));
                intent.putExtra(NotePadDB.CONTENT, getIntent().getStringExtra(NotePadDB.CONTENT));
                startActivity(intent);
                break;
        }
    }

    private void deleteByPos() {
        database.delete(NotePadDB.NAME, "_id=" + pos, null);
    }
}
