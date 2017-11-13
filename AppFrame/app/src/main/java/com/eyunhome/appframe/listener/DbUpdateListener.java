package com.eyunhome.appframe.listener;

import android.database.sqlite.SQLiteDatabase;

import com.eyunhome.appframe.db.QkDb;

/**
 * 数据库升级监听器
 */
public interface DbUpdateListener {
    public void onUpgrade(QkDb qkDb, SQLiteDatabase db, int oldVersion, int newVersion);
}
