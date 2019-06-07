package com.example.administrator.note;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private FloatingActionButton button;
    private MyAdapter adapter;
    private Intent intent;
    private NotePadDB notePadDB;
    private SQLiteDatabase database;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryAll();
    }

    private void initView(){
        listView = findViewById(R.id.list_view);
        button = findViewById(R.id.fab);
        button.setOnClickListener(this);
        notePadDB = new NotePadDB(this);
        database = notePadDB.getReadableDatabase();
    }

    @Override
    public void onClick(View v) {
        intent = new Intent(MainActivity.this, ActionActivity.class);
        intent.putExtra("action", 1);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void queryAll(){
        Cursor cursor = database.query(NotePadDB.NAME, null,null,null,null,null, null);
        adapter = new MyAdapter(this, cursor);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, LookNoteActivity.class);
                intent.putExtra("i", position);
                Cursor cursor = database.query(NotePadDB.NAME, null,null,null,null,null, "_id");
                cursor.moveToPosition(position);
                intent.putExtra(NotePadDB.ID, cursor.getString(cursor.getColumnIndex(NotePadDB.ID)));
                intent.putExtra(NotePadDB.CONTENT, cursor.getString(cursor.getColumnIndex(NotePadDB.CONTENT)));
                intent.putExtra(NotePadDB.TIME, cursor.getString(cursor.getColumnIndex(NotePadDB.TIME)));
                startActivity(intent);
            }
        });
    }
}
