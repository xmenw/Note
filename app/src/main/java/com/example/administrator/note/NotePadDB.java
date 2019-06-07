package com.example.administrator.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2019/5/19
 */
public class NotePadDB extends SQLiteOpenHelper {
    public final static String NAME = "notes";  //数据库名字
    public final static String CONTENT = "content"; //内容
    public final static String ID = "_id"; //id
    public final static String TIME = "time"; //时间


    public NotePadDB(Context context) {
        super(context, NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE " + NAME + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CONTENT + " CONTENT,"
            + TIME + " TIME"
            + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
