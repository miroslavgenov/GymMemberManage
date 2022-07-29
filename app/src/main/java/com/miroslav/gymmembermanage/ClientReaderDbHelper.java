package com.miroslav.gymmembermanage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClientReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="gymMemberManage.db";

    public ClientReaderDbHelper(Context appContext){
        super(appContext,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dataBase) {
        dataBase.execSQL(ClientContract.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dataBase, int i, int i1) {
        dataBase.execSQL(ClientContract.SQL_DELETE_ENTRIES);
        this.onCreate(dataBase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase dataBase, int oldVersion, int newVersion) {
        this.onUpgrade(dataBase,oldVersion,newVersion);
    }

}





































